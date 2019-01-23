/*
 * AdministratorController.java
 * 
 * Copyright (C) 2018 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package controllers;

import java.util.Collection;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.AdministratorService;
import services.CustomerService;
import services.EndorsementService;
import services.HandyWorkerService;
import services.WelcomeService;
import domain.Customer;
import domain.HandyWorker;

@Controller
@RequestMapping("/endorsement/administrator")
public class EndorsementAdministratorController extends AbstractController {

	@Autowired
	AdministratorService	administratorService;
	@Autowired
	CustomerService			customerService;
	@Autowired
	HandyWorkerService		hwService;
	@Autowired
	EndorsementService		endorsementService;
	@Autowired
	WelcomeService			welcomeService;


	//-----------------------------------------------------------

	@RequestMapping(value = "/list")
	public ModelAndView list() {

		ModelAndView res;

		Collection<Customer> customers;
		Collection<HandyWorker> hws;

		customers = this.customerService.findAll();
		hws = this.hwService.findAll();

		res = new ModelAndView("endorsement/administrator/list");
		res.addObject("requestURI", "endorsement/administrator/list.do");
		res.addObject("customers", customers);
		final String system = this.welcomeService.getSystem();
		res.addObject("system", system);
		final String logo = this.welcomeService.getLogo();
		res.addObject("logo", logo);
		res.addObject("handyWorkers", hws);

		return res;
	}

	@RequestMapping(value = "/wordList")
	public ModelAndView wordList() {

		ModelAndView res;

		final List<String> esPositives;
		final List<String> enPositives;

		final List<String> esNegatives;
		final List<String> enNegatives;

		esPositives = this.endorsementService.getEsPositive();
		enPositives = this.endorsementService.getEnPositive();

		esNegatives = this.endorsementService.getEsNegative();
		enNegatives = this.endorsementService.getEnNegative();

		res = new ModelAndView("endorsement/administrator/wordList");

		res.addObject("requestURI", "endorsement/administrator/wordList.do");

		res.addObject("esPositives", esPositives);
		res.addObject("enPositives", enPositives);

		final String system = this.welcomeService.getSystem();
		res.addObject("system", system);
		final String logo = this.welcomeService.getLogo();
		res.addObject("logo", logo);

		res.addObject("esNegatives", esNegatives);
		res.addObject("enNegatives", enNegatives);

		return res;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView editWord(@RequestParam("word") final String word) {

		ModelAndView res;

		res = new ModelAndView("endorsement/administrator/edit");
		res.addObject(word);
		this.endorsementService.deleteWord(word);

		return res;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView saveWord(@Valid final String word) {

		ModelAndView res;

		this.endorsementService.saveWord(word);
		res = new ModelAndView("redirect:list.do");

		return res;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView deleteWord(@RequestParam("word") final String word) {

		ModelAndView res;

		res = this.deleteWordPost(word);

		return res;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST, params = "delete")
	public ModelAndView deleteWordPost(@Valid final String word) {

		ModelAndView res;

		this.endorsementService.deleteWord(word);
		res = new ModelAndView("redirect:list.do");

		return res;
	}
}
