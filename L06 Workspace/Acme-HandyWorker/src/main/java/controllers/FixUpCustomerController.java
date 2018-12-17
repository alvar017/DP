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

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ApplicationService;
import services.CategoryService;
import services.ComplaintService;
import services.FixUpService;
import services.WarrantyService;
import domain.Application;
import domain.Category;
import domain.Complaint;
import domain.FixUp;
import domain.Warranty;

@Controller
@RequestMapping("/fixUp/customer")
public class FixUpCustomerController extends AbstractController {

	@Autowired
	private FixUpService		fixUpService;
	@Autowired
	private ComplaintService	complaintService;
	@Autowired
	private ApplicationService	applicationService;
	@Autowired
	private CategoryService		categoryService;
	@Autowired
	private WarrantyService		warrantyService;


	// Constructors -----------------------------------------------------------

	public FixUpCustomerController() {
		super();
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		final Collection<FixUp> fixUps = this.fixUpService.listing();
		final String language = LocaleContextHolder.getLocale().getDisplayLanguage();

		result = new ModelAndView("fixUp/customer/list");
		result.addObject("fixUps", fixUps);
		result.addObject("language", language);
		result.addObject("requestURI", "fixUp/customer/list.do");

		return result;
	}

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(@RequestParam("fixUpId") final int fixUpId) {
		ModelAndView result;

		//		final FixUp fixUp = this.fixUpService.findOne(463);
		final FixUp fixUp = this.fixUpService.findOne(fixUpId);
		final Category category = fixUp.getCategory();
		final Collection<Application> applications = this.applicationService.findAllByFixUp(fixUp);
		final Collection<Complaint> complaints = this.complaintService.getComplaintByFixUp(fixUp);
		final String language = LocaleContextHolder.getLocale().getDisplayLanguage();

		result = new ModelAndView("fixUp/customer/show");
		result.addObject("fixUp", fixUp);
		result.addObject("category", category);
		result.addObject("language", language);
		result.addObject("applications", applications);
		result.addObject("complaints", complaints);
		result.addObject("requestURI", "fixUp/customer/show.do");

		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;

		final String language = LocaleContextHolder.getLocale().getDisplayLanguage();

		FixUp fixUp;

		fixUp = this.fixUpService.create();

		result = this.createEditModelAndView(fixUp);
		result.addObject("language", language);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam("id") final int fixUpId) {
		ModelAndView result;

		final String language = LocaleContextHolder.getLocale().getDisplayLanguage();

		FixUp fixUp;

		fixUp = this.fixUpService.findOne(fixUpId);
		Assert.notNull(fixUp);

		result = this.createEditModelAndView(fixUp);
		result.addObject("language", language);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public ModelAndView save(@Valid final FixUp fixUp, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			System.out.println("El error pasa por aqu� alvaro (IF de save())");
			System.out.println(binding);
			result = this.createEditModelAndView(fixUp);
		} else
			try {
				System.out.println("El error pasa por aqu� alvaro (TRY de save())");
				System.out.println(binding);
				this.fixUpService.save(fixUp);
				result = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				System.out.println("El error pasa por aqu� alvaro (CATCH de save())");
				System.out.println(binding);
				System.out.println(binding);
				result = this.createEditModelAndView(fixUp, "fixUp.commit.error");
			}

		return result;
	}

	private ModelAndView createEditModelAndView(final FixUp fixUp) {
		ModelAndView result;
		final Collection<Warranty> warranties = this.warrantyService.findAll();
		final Collection<Category> categories = this.categoryService.findAll();

		result = new ModelAndView("fixUp/customer/edit");

		result.addObject("fixUp", fixUp);
		result.addObject("warranties", warranties);
		result.addObject("categories", categories);

		return result;
	}

	private ModelAndView createEditModelAndView(final FixUp fixUp, final String messageCode) {
		ModelAndView result;
		final Collection<Warranty> warranties = this.warrantyService.findAll();
		final Collection<Category> categories = this.categoryService.findAll();

		result = new ModelAndView("fixUp/customer/edit");

		result.addObject("fixUp", fixUp);
		result.addObject("warranties", warranties);
		result.addObject("categories", categories);
		result.addObject("message", messageCode);

		return result;
	}

	//	@RequestMapping(value = "customer/editFixUpTask", method = RequestMethod.GET)
	//	public ModelAndView editFixUpTask(@RequestParam("id") final int fixUpId) {
	//		ModelAndView result;
	//
	//		//		final FixUp fixUp = this.fixUpService.findOne(463);
	//		final FixUp fixUp = this.fixUpService.findOne(fixUpId);
	//		System.out.println("Customer del FixUp: " + fixUp.getCustomer() + " " + fixUp);
	//		final Category category = fixUp.getCategory();
	//		final Collection<Application> applications = this.applicationService.findAllByFixUp(fixUp);
	//		final Collection<Complaint> complaints = this.complaintService.getComplaintByFixUp(fixUp);
	//		final Collection<Category> categories = this.categoryService.findAll();
	//		final String language = LocaleContextHolder.getLocale().getDisplayLanguage();
	//
	//		result = new ModelAndView("fixUp/customer/editFixUpTask");
	//		result.addObject("fixUp", fixUp);
	//		result.addObject("category", category);
	//		result.addObject("language", language);
	//		result.addObject("applications", applications);
	//		result.addObject("complaints", complaints);
	//		result.addObject("categories", categories);
	//
	//		return result;
	//	}
	//
	//	@RequestMapping(value = "customer/editFixUpTask", method = RequestMethod.POST)
	//	public ModelAndView editFixUpTasksave(@Valid final FixUp fixUp, final BindingResult binding) {
	//		ModelAndView result;
	//
	//		System.out.println(binding.getFieldErrors());
	//		System.out.println(fixUp + " " + fixUp.getCustomer());
	//		this.fixUpService.save(fixUp);
	//
	//		final String language = LocaleContextHolder.getLocale().getDisplayLanguage();
	//
	//		result = new ModelAndView("fixUp/customer/listingFixUpTasks");
	//		System.out.println(binding.getFieldErrors());
	//		result.addObject("language", language);
	//
	//		return result;
	//	}
	//
	//	@RequestMapping("/customer/deleteFixUpTask")
	//	public ModelAndView delete(@RequestParam("id") final int fixUpId, @RequestParam("delete") final String delete) {
	//		ModelAndView result;
	//		if (delete != null && delete.equals("y")) {
	//			final FixUp fixUp = this.fixUpService.findOne(fixUpId);
	//			this.fixUpService.delete(fixUp);
	//		}
	//		final Collection<FixUp> fixUps = this.fixUpService.listing();
	//
	//		result = new ModelAndView("fixUp/customer/listingFixUpTasks");
	//		result.addObject("fixUps", fixUps);
	//
	//		return result;
	//	}

}
