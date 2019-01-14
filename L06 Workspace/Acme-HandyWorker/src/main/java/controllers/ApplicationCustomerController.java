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
import java.util.HashSet;

import javax.validation.Valid;

import org.joda.time.LocalDate;
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
import services.ApplicationService;
import services.MailBoxService;
import services.MessageService;
import services.WelcomeService;
import domain.Actor;
import domain.Application;
import domain.MailBox;
import domain.Message;

@Controller
@RequestMapping("/application/customer")
public class ApplicationCustomerController extends AbstractController {

	@Autowired
	private ApplicationService	applicationService;
	@Autowired
	private MessageService		messageService;
	@Autowired
	private MailBoxService		mailBoxService;
	@Autowired
	private WelcomeService		welcomeService;


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
		final String system = this.welcomeService.getSystem();
		result.addObject("system", system);
		final String logo = this.welcomeService.getLogo();
		result.addObject("logo", logo);
		result.addObject("requestURI", "application/customer/list.do");
		//		result.addObject("color", color);

		return result;
	}

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(@RequestParam(value = "applicationId", defaultValue = "-1") final int applicationId) {
		ModelAndView result;

		//		final FixUp fixUp = this.fixUpService.findOne(463);
		final Application application = this.applicationService.findOne(applicationId);
		if (application == null || application.getFixUp().getCustomer().getUserAccount().getId() != LoginService.getPrincipal().getId()) {
			final Collection<Application> applications = this.applicationService.findAllByCustomerLogger();
			result = new ModelAndView("application/customer/list");
			result.addObject("applications", applications);
			final String system = this.welcomeService.getSystem();
			result.addObject("system", system);
			final String logo = this.welcomeService.getLogo();
			result.addObject("logo", logo);

			result.addObject("requestURI", "application/customer/list.do");
		} else {
			Assert.notNull(application);
			final Double iva = this.applicationService.iva(application);
			final String color = this.chooseColor(application);
			System.out.println("Color: " + color);

			result = new ModelAndView("application/customer/show");
			final String system = this.welcomeService.getSystem();
			result.addObject("system", system);
			final String logo = this.welcomeService.getLogo();
			result.addObject("logo", logo);

			result.addObject("color", color);
			result.addObject("application", application);
			result.addObject("iva", iva);
			result.addObject("requestURI", "application/customer/show.do");
		}
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
	public ModelAndView edit(@RequestParam(value = "applicationId", defaultValue = "-1") final int applicationId) {
		ModelAndView result;
		Application application;

		application = this.applicationService.findOne(applicationId);
		if (application == null || (application.getState() != null && application.getState() == true) || application.getFixUp().getCustomer().getUserAccount().getId() != LoginService.getPrincipal().getId()) {
			final Collection<Application> applications = this.applicationService.findAllByCustomerLogger();
			result = new ModelAndView("application/customer/list");
			final String system = this.welcomeService.getSystem();
			result.addObject("system", system);
			final String logo = this.welcomeService.getLogo();
			result.addObject("logo", logo);

			result.addObject("applications", applications);
			result.addObject("requestURI", "application/customer/list.do");
		} else {
			Assert.notNull(application);
			result = this.createEditModelAndView(application);
			final HashSet<String> brand = this.applicationService.listBrands();
			result.addObject("brand", brand);
		}
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Application application, final BindingResult binding) {
		ModelAndView result;
		System.out.println(application);
		System.out.println(application.getComments());
		System.out.println("Entro en el save");

		final Actor applier = application.getApplier();
		final Actor custom = application.getFixUp().getCustomer();

		final MailBox inBoxApplier = this.mailBoxService.getInBoxActor(applier.getId());
		final MailBox inBoxCustom = this.mailBoxService.getInBoxActor(custom.getId());

		System.out.println("EL REQ 19");
		System.out.println(inBoxApplier);
		System.out.println(inBoxCustom);
		//		if (application.getOffered() != null || application.getOffered().getQuantity() != null || (application.getOffered().getQuantity() < 100 || application.getOffered().getQuantity() > 999)) {
		//			final ObjectError error = new ObjectError("offered.quantity", "An account already exists for this email.");
		//			binding.addError(error);
		//			binding.rejectValue("offered.quantity", "error.offered.quantity");
		//		}
		if (application.getState() == true && (application.getCreditCard().getName() == null || application.getCreditCard().getName().trim().isEmpty())) {
			final ObjectError error = new ObjectError("creditCard.name", "Illegal name");
			binding.addError(error);
			binding.rejectValue("creditCard.name", "error.creditCard.name");
		}
		if (application.getState() == true && (application.getCreditCard().getNumber() == null || application.getCreditCard().getNumber() <= 0)) {
			final ObjectError error = new ObjectError("creditCard.number", "Illegal number");
			binding.addError(error);
			binding.rejectValue("creditCard.number", "error.creditCard.number");
		}

		if (application.getState() == true && (application.getCreditCard().getCvv() == null || application.getCreditCard().getCvv() < 100 || application.getCreditCard().getCvv() > 999)) {
			final ObjectError error = new ObjectError("creditCard.year", "Illegal year");
			binding.addError(error);
			binding.rejectValue("creditCard.cvv", "error.creditCard.cvv");
		}

		if (application.getState() == true && (application.getCreditCard().getYear() == null || application.getCreditCard().getYear() < 1900 || application.getCreditCard().getYear() > 2100)) {
			final ObjectError error = new ObjectError("creditCard.year", "Illegal year");
			binding.addError(error);
			binding.rejectValue("creditCard.year", "error.creditCard.year");
		}
		if (binding.hasErrors()) {
			System.out.println("Entro en el binding");
			System.out.println(binding);
			result = this.createEditModelAndView(application);
			final HashSet<String> brand = this.applicationService.listBrands();
			result.addObject("brand", brand);
		} else
			try {
				this.applicationService.updateCustomer(application);
				if (application.getState() != null) {
					final Message m = this.messageService.create();
					m.setSubject("Application");
					if (application.getState() == true)
						m.setBody("ES: La aplicación " + application.getId() + " ha cambiado su estado a aceptada ||EN: The application" + application.getId() + " have change her state to accepted");
					else
						m.setBody("ES: La aplicación " + application.getId() + " ha cambiado su estado a rechazada ||EN: The application" + application.getId() + " have change her state to rejected");

					m.getMailBoxes().add(inBoxApplier);
					m.getMailBoxes().add(inBoxCustom);

					final Message saved = this.messageService.save(m);

					inBoxApplier.getMessages().add(m);
					inBoxCustom.getMessages().add(m);

					System.out.println(m.getMailBoxes());
					System.out.println(inBoxApplier.getMessages());
					System.out.println(inBoxCustom.getMessages());

					System.out.println("sE GUARDA EL MENSAJE");
				}
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
		final String system = this.welcomeService.getSystem();
		result.addObject("system", system);
		final String logo = this.welcomeService.getLogo();
		result.addObject("logo", logo);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Application application, final String messageCode) {
		ModelAndView result;

		result = new ModelAndView("application/customer/edit");
		final String system = this.welcomeService.getSystem();
		result.addObject("system", system);
		final String logo = this.welcomeService.getLogo();
		result.addObject("logo", logo);

		result.addObject("application", application);
		result.addObject("message", messageCode);

		return result;
	}
}
