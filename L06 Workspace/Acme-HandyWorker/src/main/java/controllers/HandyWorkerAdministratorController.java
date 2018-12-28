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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import services.HandyWorkerService;
import domain.HandyWorker;

@Controller
@RequestMapping("/handyWorker/administrator")
public class HandyWorkerAdministratorController extends AbstractController {

	@Autowired
	private HandyWorkerService	handyWorkerService;


	// Constructors -----------------------------------------------------------

	public HandyWorkerAdministratorController() {
		super();
	}

	// list ---------------------------------------------------------------		

	@RequestMapping(value = "/showD")
	public ModelAndView list() {
		ModelAndView result;
		final Collection<HandyWorker> handyWorker = this.handyWorkerService.getTopThreeHandyWorker();
		;
		final String language = LocaleContextHolder.getLocale().getDisplayLanguage();

		result = new ModelAndView("handyWorker/administrator/showD");
		result.addObject("handyWorker", handyWorker);
		result.addObject("language", language);
		result.addObject("requestURI", "handyWorker/administrator/showD.do");

		return result;
	}

}
