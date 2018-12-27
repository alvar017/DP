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
import services.SocialProfilelService;
import domain.HandyWorker;
import domain.SocialProfile;

@Controller
@RequestMapping("/socialProfile/handyWorker")
public class SocialProfileaHandyWorkerController extends AbstractController {

	@Autowired
	private SocialProfilelService	socialProfileService;
	@Autowired
	private HandyWorkerService		handyWorkerService;


	//	@Autowired
	//	private UserAccount			userAccountService;

	// Constructors -----------------------------------------------------------

	public SocialProfileaHandyWorkerController() {
		super();
	}
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;

		SocialProfile socialProfile;

		socialProfile = this.socialProfileService.create();

		result = new ModelAndView("socialProfile/handyWorker/create");

		result.addObject("socialProfile", socialProfile);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam("socialProfileId") final int socialProfileId) {
		ModelAndView result;

		final SocialProfile socialProfile;
		Assert.isTrue(this.socialProfileService.findOne(socialProfileId) != null);
		socialProfile = this.socialProfileService.findOne(socialProfileId);

		result = new ModelAndView("socialProfile/handyWorker/edit");

		result.addObject("socialProfile", socialProfile);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView saveEdit(@Valid final SocialProfile socialProfile, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			System.out.println("El error pasa por aqu� alvaro (IF de save())");
			System.out.println(binding);
			result = new ModelAndView("socialProfile/handyWorker/edit");
		} else
			try {
				Assert.isTrue(socialProfile != null);
				final SocialProfile savedSocialProfile = this.socialProfileService.save(socialProfile);
				final int userLoggin = LoginService.getPrincipal().getId();
				final HandyWorker handyWorker = this.handyWorkerService.findByUserAccountId(userLoggin);
				Assert.isTrue(handyWorker != null);
				if (handyWorker.getSocialProfiles().contains(savedSocialProfile)) {
					handyWorker.getSocialProfiles().remove(savedSocialProfile);
					handyWorker.getSocialProfiles().add(savedSocialProfile);
				} else
					handyWorker.getSocialProfiles().add(socialProfile);
				final HandyWorker savedHandyWorker = this.handyWorkerService.save(handyWorker);

				result = new ModelAndView("handyWorker/show");
				result.addObject("handyWorker", savedHandyWorker);
				result.addObject("socialProfiles", savedHandyWorker.getSocialProfiles());
				result.addObject("requestURI", "handyWorker/show.do");
			} catch (final Throwable oops) {
				System.out.println("El error es en SocialProfileController: ");
				System.out.println(oops);
				System.out.println(binding);
				result = new ModelAndView("socialProfile/handyWorker/edit");
			}
		return result;
	}
}
