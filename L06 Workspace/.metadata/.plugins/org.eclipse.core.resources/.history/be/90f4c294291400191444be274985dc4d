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
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import security.LoginService;
import security.UserAccount;
import services.ApplicationService;
import services.FixUpService;
import services.HandyWorkerService;
import domain.Application;
import domain.FixUp;
import domain.HandyWorker;

@Controller
@RequestMapping("/application/handyWorker")
public class ApplicationHandyWorkerController extends AbstractController {

	@Autowired
	private ApplicationService	applicationService;
	@Autowired
	private HandyWorkerService	handyWorkerService;
	@Autowired
	private FixUpService		fixUpService;


	// Constructors -----------------------------------------------------------

	public ApplicationHandyWorkerController() {
		super();
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView listApplication() {
		ModelAndView result;

		final UserAccount login = LoginService.getPrincipal();

		final HandyWorker logged = this.handyWorkerService.getHandyWorkerByUserAccountId(login.getId());

		final Collection<Application> applications = this.applicationService.findAllByHandyWorker(logged.getId());

		result = new ModelAndView("application/handyWorker/list");
		result.addObject("applications", applications);
		result.addObject("requestURI", "application/handyWorker/list.do");

		return result;
	}
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam final int id) {
		ModelAndView result;
		Application application;

		System.out.println("Entro en el create");
		application = this.applicationService.create();
		final FixUp fixUp = this.fixUpService.findOne(id);

		Assert.notNull(fixUp);
		application.setFixUp(fixUp);
		result = this.createEditModelAndView(application);

		return result;
	}
	//NUEVA
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int applicationId) {
		ModelAndView result;
		Application application;

		System.out.println("Entro en el create");
		application = this.applicationService.findOne(applicationId);

		Assert.notNull(application);
		result = this.createUpdateModelAndView(application);

		return result;
	}
	//Check that the HW didn't already applied for this FixUp

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Application application, final BindingResult binding) {
		ModelAndView result;
		System.out.println(application);
		System.out.println(application.getComments());
		System.out.println("Entro en el save");
		if (binding.hasErrors()) {
			System.out.println("Entro en el binding");
			System.out.println(binding.getAllErrors().get(0));
			result = this.createEditModelAndView(application);
		} else
			try {
				this.applicationService.save(application);
				result = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				if (oops.getMessage().equals("application.alreadyApplied"))
					result = this.createEditModelAndView(application, "application.alreadyApplied");
				else
					result = this.createEditModelAndView(application, "application.commit.error");
			}

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "update")
	public ModelAndView update(@Valid final Application application, final BindingResult binding) {
		ModelAndView result;
		System.out.println(application);
		System.out.println(application.getComments());
		System.out.println("Entro en el update");
		if (application.getOffered().getQuantity() < 0) {
			final ObjectError error = new ObjectError("maxPrice.quantity", "An account already exists for this email.");
			binding.addError(error);
			binding.rejectValue("offered.quantity", "error.maxPrice.quantity");
		}
		if (binding.hasErrors()) {
			System.out.println("Entro en el binding");
			System.out.println(binding.getAllErrors().get(0));
			result = this.createEditModelAndView(application);
		} else
			try {
				this.applicationService.update(application);
				result = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				if (oops.getMessage().equals("application.alreadyApplied"))
					result = this.createEditModelAndView(application, "application.alreadyApplied");
				else
					result = this.createEditModelAndView(application, "application.commit.error");
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

		result = new ModelAndView("application/handyWorker/edit");
		result.addObject("application", application);
		result.addObject("message", messageCode);

		return result;
	}

	//NUEVO
	protected ModelAndView createUpdateModelAndView(final Application application) {
		ModelAndView result;

		result = this.createUpdateModelAndView(application, null);

		return result;
	}

	protected ModelAndView createUpdateModelAndView(final Application application, final String messageCode) {
		ModelAndView result;

		result = new ModelAndView("application/handyWorker/update");
		result.addObject("application", application);
		result.addObject("message", messageCode);

		return result;
	}
}
