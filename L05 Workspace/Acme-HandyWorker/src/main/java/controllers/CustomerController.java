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

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import services.CategoryService;
import services.FixUpService;
import domain.Category;
import domain.FixUp;

@Controller
@RequestMapping("/customer")
public class CustomerController extends AbstractController {

	@Autowired
	private FixUpService	fixUpService;
	private CategoryService	categoryService;


	// Constructors -----------------------------------------------------------

	public CustomerController() {
		super();
	}

	// Action-1 ---------------------------------------------------------------		

	@RequestMapping("/action-1")
	public ModelAndView action1() {
		ModelAndView result;

		final Collection<FixUp> fixUps = this.fixUpService.listing();

		result = new ModelAndView("customer/action-1");
		result.addObject("fixUps", fixUps);

		return result;
	}

	// Action-2 ---------------------------------------------------------------		

	@RequestMapping("/action-2")
	public ModelAndView action2() {
		ModelAndView result;

		final FixUp fixUp = this.fixUpService.findOne(463);
		final Category category = fixUp.getCategory();
		final String language = LocaleContextHolder.getLocale().getDisplayLanguage();

		result = new ModelAndView("customer/action-2");
		result.addObject("fixUp", fixUp);
		result.addObject("category", category);
		result.addObject("language", language);

		return result;
	}
}