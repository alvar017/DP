/*
 * WelcomeController.java
 * 
 * Copyright (C) 2018 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package controllers;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import services.WelcomeService;

@Controller
@RequestMapping("/welcome")
public class WelcomeController extends AbstractController {

	@Autowired
	private WelcomeService	welcomeService;


	// Constructors -----------------------------------------------------------

	public WelcomeController() {
		super();
	}

	// Index ------------------------------------------------------------------		

	@RequestMapping(value = "/index")
	public ModelAndView index() {
		ModelAndView result;
		SimpleDateFormat formatter;
		String moment;
		final String language = LocaleContextHolder.getLocale().getDisplayLanguage();

		final String name = this.welcomeService.getE();
		final String nameS = this.welcomeService.getS();
		//System
		final String system = this.welcomeService.getSystem();
		//Logo
		final String logo = this.welcomeService.getLogo();

		formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		moment = formatter.format(new Date());

		result = new ModelAndView("welcome/index");
		result.addObject("name", name);
		result.addObject("nameS", nameS);
		result.addObject("moment", moment);
		result.addObject("language", language);
		result.addObject("system", system);
		result.addObject("logo", logo);

		return result;
	}

}
