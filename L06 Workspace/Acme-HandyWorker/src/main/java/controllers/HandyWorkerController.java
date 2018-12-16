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
import org.springframework.web.bind.annotation.RequestMapping;

import services.FinderService;
import services.FixUpService;

@Controller
@RequestMapping("/handyWorker")
public class HandyWorkerController extends AbstractController {

	@Autowired
	private FinderService	finderService;

	@Autowired
	private FixUpService	fixUpService;


	// Constructors -----------------------------------------------------------

	public HandyWorkerController() {
		super();
	}

	// Action-1 ---------------------------------------------------------------		

	// Action-2 ---------------------------------------------------------------		

}
