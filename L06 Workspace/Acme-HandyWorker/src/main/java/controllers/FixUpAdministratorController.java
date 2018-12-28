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

import services.FixUpService;

@Controller
@RequestMapping("/fixUp/administrator")
public class FixUpAdministratorController extends AbstractController {

	@Autowired
	private FixUpService	fixUpService;


	// Constructors -----------------------------------------------------------

	public FixUpAdministratorController() {
		super();
	}

	@RequestMapping(value = "/showD", method = RequestMethod.GET)
	public ModelAndView action1() {
		ModelAndView result;
		Map<String, Double> statistics;

		statistics = this.fixUpService.computeStatistics();

		result = new ModelAndView("fixUp/administrator/showD");
		result.addObject("statistics", statistics);

		return result;
	}

}
