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

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ApplicationService;
import domain.Application;

@Controller
@RequestMapping("/application/customer")
public class ApplicationCustomerController extends AbstractController {

	@Autowired
	private ApplicationService	applicationService;


	// Constructors -----------------------------------------------------------

	public ApplicationCustomerController() {
		super();
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView listApplication() {
		ModelAndView result;

		final Collection<Application> applications = this.applicationService.findAllByCustomerLogger();

		//		final String color = this.chooseColor(application);
		//		System.out.println("Color: " + color);

		result = new ModelAndView("application/customer/list");
		result.addObject("applications", applications);
		result.addObject("requestURI", "application/customer/list.do");
		//		result.addObject("color", color);

		return result;
	}

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(@RequestParam("applicationId") final int applicationId) {
		ModelAndView result;

		//		final FixUp fixUp = this.fixUpService.findOne(463);
		final Application application = this.applicationService.findOne(applicationId);
		Assert.notNull(application);

		final String color = this.chooseColor(application);
		System.out.println("Color: " + color);

		result = new ModelAndView("application/customer/show");
		result.addObject("color", color);
		result.addObject("application", application);
		result.addObject("requestURI", "application/customer/show.do");

		return result;
	}

	private String chooseColor(final Application application) {
		String res;
		if (application.getState() == null || application.getFixUp().getEndDate().before(LocalDate.now().toDate()))
			res = "ninguno";
		else if (application.getState() != null && application.getState() == true)
			res = "#00FF00";
		else
			res = "#FF0000";
		return res;
	}
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int applicationId) {
		ModelAndView result;
		Application application;

		application = this.applicationService.findOne(applicationId);
		Assert.notNull(application);
		result = this.createEditModelAndView(application);
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Application application, final BindingResult binding) {
		ModelAndView result;
		System.out.println(application);
		System.out.println(application.getComments());
		System.out.println("Entro en el save");
		if (binding.hasErrors()) {
			System.out.println("Entro en el binding");
			System.out.println(binding);
			result = this.createEditModelAndView(application);
		} else
			try {
				this.applicationService.updateCustomer(application);
				result = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				System.out.println(oops);
				if (oops.getMessage().equals("application.error.creditCard"))
					result = this.createEditModelAndView(application, "application.error.creditCard");
				else {
					System.out.println(oops);
					result = this.createEditModelAndView(application, "application.commit.error");
				}
			}

		return result;
	}
	protected ModelAndView createEditModelAndView(final Application application) {
		ModelAndView result;

		result = this.createEditModelAndView(application, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Application application, final String messageCode) {
		ModelAndView result;

		result = new ModelAndView("application/customer/edit");
		result.addObject("application", application);
		result.addObject("message", messageCode);

		return result;
	}
}
