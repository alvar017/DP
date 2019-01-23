/*
 * CustomerController.java
 * 
 * Copyright (C) 2018 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.NoteService;
import services.WelcomeService;

@Controller
@RequestMapping("/note/administrator")
public class NoteAdministratorController extends AbstractController {

	@Autowired
	private NoteService		noteService;
	@Autowired
	private WelcomeService	welcomeService;


	// Constructors -----------------------------------------------------------

	public NoteAdministratorController() {
		super();
	}

	@RequestMapping(value = "/showD", method = RequestMethod.GET)
	public ModelAndView action1() {
		ModelAndView result;
		Map<String, Double> statistics;

		statistics = this.noteService.computeStatistics2();

		result = new ModelAndView("note/administrator/showD");
		result.addObject("statistics", statistics);
		final String system = this.welcomeService.getSystem();
		result.addObject("system", system);
		final String logo = this.welcomeService.getLogo();
		result.addObject("logo", logo);

		return result;
	}

}
