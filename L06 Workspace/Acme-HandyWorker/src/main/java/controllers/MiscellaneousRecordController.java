/*
 * refereeController.java
 * 
 * Copyright (C) 2018 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import security.LoginService;
import services.HandyWorkerService;
import services.MiscellaneousRecordService;
import services.SocialProfilelService;
import domain.Curriculum;
import domain.HandyWorker;
import domain.MiscellaneousRecord;

@Controller
@RequestMapping("/miscellaneousRecord/handyWorker")
public class MiscellaneousRecordController extends AbstractController {

	@Autowired
	private SocialProfilelService		socialProfileService;
	@Autowired
	private HandyWorkerService			handyWorkerService;
	@Autowired
	private MiscellaneousRecordService	miscellaneousRecordService;


	//	@Autowired
	//	private UserAccount			userAccountService;

	// Constructors -----------------------------------------------------------

	public MiscellaneousRecordController() {
		super();
	}
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;

		MiscellaneousRecord miscellaneousRecord;

		miscellaneousRecord = this.miscellaneousRecordService.create();

		result = new ModelAndView("miscellaneousRecord/handyWorker/create");

		result.addObject("miscellaneousRecord", miscellaneousRecord);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam("miscellaneousRecordId") final int miscellaneousRecordId) {
		ModelAndView result;

		final MiscellaneousRecord miscellaneousRecord;
		Assert.isTrue(this.miscellaneousRecordService.findOne(miscellaneousRecordId) != null);
		miscellaneousRecord = this.miscellaneousRecordService.findOne(miscellaneousRecordId);

		result = new ModelAndView("miscellaneousRecord/handyWorker/edit");

		result.addObject("miscellaneousRecord", miscellaneousRecord);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView saveEdit(@Valid final MiscellaneousRecord miscellaneousRecord, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			System.out.println("El error pasa por aqu� alvaro (IF de save())");
			System.out.println(binding);
			result = new ModelAndView("miscellaneousRecord/handyWorker/edit");
		} else
			try {
				Assert.isTrue(miscellaneousRecord != null);
				final MiscellaneousRecord savedMiscellaneousRecord = this.miscellaneousRecordService.save(miscellaneousRecord);
				final int userLoggin = LoginService.getPrincipal().getId();
				final HandyWorker handyWorker = this.handyWorkerService.findByUserAccountId(userLoggin);
				Assert.isTrue(handyWorker != null);
				if (handyWorker.getCurriculum().getMisrec().contains(savedMiscellaneousRecord)) {
					handyWorker.getCurriculum().getMisrec().remove(savedMiscellaneousRecord);
					handyWorker.getCurriculum().getMisrec().add(savedMiscellaneousRecord);
				} else
					handyWorker.getCurriculum().getMisrec().add(savedMiscellaneousRecord);
				final HandyWorker savedHandyWorker = this.handyWorkerService.save(handyWorker);

				result = new ModelAndView("curriculum/handyWorker/show");
				result.addObject("handyWorker", savedHandyWorker);
				result.addObject("curriculum", savedHandyWorker.getCurriculum());
				result.addObject("requestURI", "curriculum/handyWorker/show.do");
			} catch (final Throwable oops) {
				System.out.println("El error es en MiscellaneousRecordController: ");
				System.out.println(oops);
				System.out.println(binding);
				result = new ModelAndView("miscellaneousRecord/handyWorker/edit");
			}
		return result;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam("miscellaneousRecordId") final int miscellaneousRecordId) {
		ModelAndView result;

		final MiscellaneousRecord miscellaneousRecord = this.miscellaneousRecordService.findOne(miscellaneousRecordId);
		System.out.println("miscellaneousRecordId encontrado: " + miscellaneousRecordId);
		Assert.notNull(miscellaneousRecordId, "socialProfile.null");

		try {
			final int userLoggin = LoginService.getPrincipal().getId();
			final HandyWorker handyWorker = this.handyWorkerService.findByUserAccountId(userLoggin);
			Assert.isTrue(handyWorker != null);
			final Curriculum curriculum = handyWorker.getCurriculum();
			Assert.notNull(curriculum, "curriculum.null");
			final HandyWorker savedHandyWorker = this.handyWorkerService.save(handyWorker);
			this.miscellaneousRecordService.delete(miscellaneousRecord);
			result = new ModelAndView("curriculum/handyWorker/show");
			result.addObject("handyWorker", savedHandyWorker);
			result.addObject("curriculum", savedHandyWorker.getCurriculum());
			result.addObject("requestURI", "curriculum/handyWorker/show.do");
		} catch (final Throwable oops) {
			System.out.println("Error al borrar miscellaneousRecord desde hw: ");
			System.out.println(oops);
			result = new ModelAndView("curriculum/handyWorker/show");
		}
		return result;
	}
}
