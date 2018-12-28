/*
 * handyWorkerController.java
 * 
 * Copyright (C) 2018 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package controllers;

import java.util.Collection;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import security.LoginService;
import security.UserAccount;
import services.CategoryService;
import services.FinderService;
import services.FixUpService;
import services.HandyWorkerService;
import services.WarrantyService;
import domain.Category;
import domain.Finder;
import domain.FixUp;
import domain.HandyWorker;
import domain.Warranty;

@Controller
@RequestMapping("/finder/handyWorker")
public class FinderHandyWorkerController extends AbstractController {

	@Autowired
	private FinderService		finderService;

	@Autowired
	private CategoryService		categoryService;

	@Autowired
	private WarrantyService		warrantyService;

	@Autowired
	private FixUpService		fixUpService;

	@Autowired
	private HandyWorkerService	handyWorkerService;


	// Constructors -----------------------------------------------------------

	public FinderHandyWorkerController() {
		super();
	}

	// list ---------------------------------------------------------------		

	@RequestMapping(value = "/list")
	public ModelAndView list() {
		ModelAndView result;
		final Finder finder = this.finderService.yourFinder();
		final String language = LocaleContextHolder.getLocale().getDisplayLanguage();

		result = new ModelAndView("finder/handyWorker/list");
		result.addObject("finder", finder);
		result.addObject("language", language);
		result.addObject("requestURI", "finder/handyWorker/list.do");

		return result;
	}

	//create----------------------------------------------------
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {

		ModelAndView res;

		final Collection<Category> category = this.categoryService.findAll();
		final Collection<Warranty> warranty = this.warrantyService.findAll();

		Finder finder;

		finder = this.finderService.create();

		final UserAccount user = LoginService.getPrincipal();
		final HandyWorker hw = this.handyWorkerService.getHandyWorkerByUserAccountId(user.getId());
		hw.setFinder(finder);

		res = this.createEditModelAndView(finder);

		res.addObject("category", category);
		res.addObject("warranty", warranty);

		return res;
	}

	//edit--------------------------------------------------------------

	//	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	//	public ModelAndView edit(@RequestParam("finderId") final int finderId) {
	//		ModelAndView result;
	//
	//		System.out.println("Carmen: Entro en edit");
	//
	//		final Collection<Category> category = this.categoryService.findAll();
	//		final Collection<Warranty> warranty = this.warrantyService.findAll();
	//
	//		final String language = LocaleContextHolder.getLocale().getDisplayLanguage();
	//
	//		final Finder finder = this.finderService.yourFinder();
	//
	//		result = this.createEditModelAndView(finder);
	//
	//		result.addObject("language", language);
	//		result.addObject("category", category);
	//		result.addObject("warranty", warranty);
	//
	//		System.out.println("Carmen: Salgo de edit");
	//
	//		return result;
	//	}

	//save ---------------------	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Finder finder, final BindingResult binding) {
		ModelAndView result;

		System.out.println("Carmen: Entro en el save");

		System.out.println("BindingErrors: " + binding.getFieldErrors());

		if (binding.hasErrors()) {
			System.out.println("Carmen: Hay ERRORES");
			result = this.createEditModelAndView(finder);
		} else
			try {
				System.out.println("Carmen: Comienzo el try");

				final Category category = finder.getCategory();
				final Warranty warranty = finder.getWarranty();
				final String keyword = finder.getKeyword();
				final Date endDate = finder.getEndDate();
				final Date startDate = finder.getStartDate();
				final Double minMoney = finder.getMinPrice();
				final Double maxMoney = finder.getMaxPrice();

				System.out.println("Category" + category);
				System.out.println("Warranty" + warranty);

				final Collection<FixUp> fixUps = this.fixUpService.filterFixUps(keyword, warranty, category, startDate, endDate, minMoney, maxMoney);

				System.out.println(fixUps);

				finder.setFixUps(fixUps);

				System.out.println(finder.getId());
				final Finder finderSave = this.finderService.save(finder);

				//				final UserAccount userLogger = LoginService.getPrincipal();
				//				final HandyWorker hwLogger = this.handyWorkerService.getHandyWorkerByUserAccountId(userLogger.getId());
				//				hwLogger.setFinder(finder);
				//
				//				System.out.println(hwLogger.getFinder().getFixUps());
				//
				//				System.out.println("Finder id" + hwLogger.getFinder().getId());
				//
				//				this.handyWorkerService.save(hwLogger);�

				final UserAccount user = LoginService.getPrincipal();
				final HandyWorker handyWorker = this.handyWorkerService.getHandyWorkerByUserAccountId(user.getId());
				Assert.isTrue(handyWorker != null);
				if (handyWorker.getFinder() != null) {
					handyWorker.getSocialProfiles().remove(finder);
					handyWorker.setFinder(finderSave);
				} else
					handyWorker.setFinder(finderSave);
				final HandyWorker savedHandyWorker = this.handyWorkerService.save(handyWorker);

				System.out.println("Hw" + savedHandyWorker.getFixUps());

				System.out.println("Carmen: !PUEDO GUARDAR�");
				System.out.println("Busqueda de fixUps nuevos" + finder.getFixUps());
				result = new ModelAndView("redirect:list.do");

				result.addObject("handyWorker", savedHandyWorker);
			} catch (final Throwable oops) {
				System.out.println("Carmen: !NO PUEDO GUARDAR�");
				System.out.println("finder error:" + oops);
				result = this.createEditModelAndView(finder, "finder.commit.error");
			}

		return result;
	}

	//others---------------------------
	private ModelAndView createEditModelAndView(final Finder finder) {
		ModelAndView result;

		System.out.println("Carmen:!Puedo entrar en createEditModelAndWiew de finder�");

		result = new ModelAndView("finder/handyWorker/edit");

		result.addObject("finder", finder);

		System.out.println("Carmen: Salgo en createEditModelAndWiew de finder");

		return result;
	}

	private ModelAndView createEditModelAndView(final Finder finder, final String messageCode) {
		ModelAndView result;

		result = new ModelAndView("finder/handyWorker/edit");

		result.addObject("finder", finder);
		result.addObject("message", messageCode);

		return result;
	}

}
