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

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import services.CustomerService;
import services.WelcomeService;
import domain.Customer;

@Controller
@RequestMapping("/customer/administrator")
public class CustomerAdministratorController extends AbstractController {

	@Autowired
	private CustomerService	customerService;
	@Autowired
	private WelcomeService	welcomeService;


	// Constructors -----------------------------------------------------------

	public CustomerAdministratorController() {
		super();
	}

	// list ---------------------------------------------------------------		

	@RequestMapping(value = "/showD")
	public ModelAndView list() {
		ModelAndView result;
		final Collection<Customer> customer = this.customerService.getTopThreeCustomers();
		;
		final String language = LocaleContextHolder.getLocale().getDisplayLanguage();

		result = new ModelAndView("customer/administrator/showD");
		result.addObject("customer", customer);

		final String system = this.welcomeService.getSystem();
		result.addObject("system", system);
		final String logo = this.welcomeService.getLogo();
		result.addObject("logo", logo);
		result.addObject("language", language);
		result.addObject("requestURI", "customer/administrator/showD.do");

		return result;
	}

}
