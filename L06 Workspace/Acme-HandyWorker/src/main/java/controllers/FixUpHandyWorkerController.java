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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ApplicationService;
import services.CategoryService;
import services.ComplaintService;
import services.FixUpService;
import domain.Category;
import domain.FixUp;

@Controller
@RequestMapping("/fixUp/handyWorker")
public class FixUpHandyWorkerController extends AbstractController {

	@Autowired
	private FixUpService		fixUpService;
	@Autowired
	private ComplaintService	complaintService;
	@Autowired
	private ApplicationService	applicationService;
	@Autowired
	private CategoryService		categoryService;


	// Constructors -----------------------------------------------------------

	public FixUpHandyWorkerController() {
		super();
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		final Collection<FixUp> fixUps = this.fixUpService.findAll();
		final String language = LocaleContextHolder.getLocale().getDisplayLanguage();
		final Collection<FixUp> myFixUps = this.fixUpService.findAllByHWLogger();

		result = new ModelAndView("fixUp/handyWorker/list");
		result.addObject("fixUps", fixUps);
		result.addObject("myFixUps", myFixUps);
		result.addObject("language", language);
		result.addObject("requestURI", "fixUp/handyWorker/list.do");

		return result;
	}

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(@RequestParam("fixUpId") final int fixUpId) {
		ModelAndView result;

		final FixUp fixUp = this.fixUpService.findOne(fixUpId);
		final Category category = fixUp.getCategory();
		final String language = LocaleContextHolder.getLocale().getDisplayLanguage();

		result = new ModelAndView("fixUp/handyWorker/show");
		result.addObject("fixUp", fixUp);
		result.addObject("category", category);
		result.addObject("language", language);
		result.addObject("requestURI", "fixUp/handyWorker/show.do");

		return result;
	}
}
