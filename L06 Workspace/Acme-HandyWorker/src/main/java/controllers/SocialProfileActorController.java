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
import services.ActorService;
import services.SocialProfilelService;
import domain.Actor;
import domain.SocialProfile;

@Controller
@RequestMapping("/socialProfile/actor")
public class SocialProfileActorController extends AbstractController {

	@Autowired
	private SocialProfilelService	socialProfileService;
	@Autowired
	private ActorService			actorService;


	//	@Autowired
	//	private UserAccount			userAccountService;

	// Constructors -----------------------------------------------------------

	public SocialProfileActorController() {
		super();
	}
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;

		SocialProfile socialProfile;

		socialProfile = this.socialProfileService.create();

		result = new ModelAndView("socialProfile/actor/create");

		result.addObject("socialProfile", socialProfile);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam("socialProfileId") final int socialProfileId) {
		ModelAndView result;

		final SocialProfile socialProfile;
		Assert.isTrue(this.socialProfileService.findOne(socialProfileId) != null);
		socialProfile = this.socialProfileService.findOne(socialProfileId);

		result = new ModelAndView("socialProfile/actor/edit");

		result.addObject("socialProfile", socialProfile);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView saveEdit(@Valid final SocialProfile socialProfile, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			System.out.println("El error pasa por aqu� alvaro (IF de save())");
			System.out.println(binding);
			result = new ModelAndView("socialProfile/actor/edit");
		} else
			try {
				Assert.isTrue(socialProfile != null);
				final SocialProfile savedSocialProfile = this.socialProfileService.save(socialProfile);
				final int userLoggin = LoginService.getPrincipal().getId();
				final Actor actor = this.actorService.getActorByUserId(userLoggin);
				Assert.isTrue(actor != null);

				if (actor.getSocialProfiles().contains(savedSocialProfile)) {
					actor.getSocialProfiles().remove(savedSocialProfile);
					actor.getSocialProfiles().add(savedSocialProfile);
				} else
					actor.getSocialProfiles().add(socialProfile);
				final Actor savedActor = this.actorService.save(actor);

				result = new ModelAndView("actor/show");
				result.addObject("actor", savedActor);
				result.addObject("socialProfiles", savedActor.getSocialProfiles());
				result.addObject("requestURI", "actor/show.do");
			} catch (final Throwable oops) {
				System.out.println("El error es en SocialProfileController: ");
				System.out.println(oops);
				System.out.println(binding);
				result = new ModelAndView("socialProfile/actor/edit");
			}
		return result;
	}
}