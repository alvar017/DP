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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import security.LoginService;
import services.HandyWorkerService;
import services.WelcomeService;
import domain.HandyWorker;

@Controller
@RequestMapping("/curriculum/handyWorker")
public class CurriculumHandyWorkerController extends AbstractController {

	@Autowired
	private HandyWorkerService	handyWorkerService;
	@Autowired
	private WelcomeService		welcomeService;


	//	@Autowired
	//	private UserAccount			userAccountService;

	// Constructors -----------------------------------------------------------

	public CurriculumHandyWorkerController() {
		super();
	}

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		final int userLoggin = LoginService.getPrincipal().getId();
		final HandyWorker handyWorker = this.handyWorkerService.findByUserAccountId(userLoggin);
		Assert.isTrue(handyWorker != null);

		result = new ModelAndView("curriculum/handyWorker/show");
		final String system = this.welcomeService.getSystem();
		result.addObject("system", system);
		final String logo = this.welcomeService.getLogo();
		result.addObject("logo", logo);
		result.addObject("handyWorker", handyWorker);
		result.addObject("curriculum", handyWorker.getCurriculum());
		result.addObject("requestURI", "curriculum/handyWorker/show.do");

		return result;
	}
}
