/*
 * customerController.java
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

import security.LoginService;
import services.CustomerService;
import services.EndorsementService;
import services.HandyWorkerService;
import domain.Customer;
import domain.Endorsable;
import domain.Endorsement;
import domain.HandyWorker;

@Controller
@RequestMapping("/endorsement/customer")
public class EndorsementCustomerController extends AbstractController {

	@Autowired
	private EndorsementService	endorsementService;
	@Autowired
	private CustomerService		customerService;
	@Autowired
	private HandyWorkerService	handyWorkerService;


	//	@Autowired
	//	private UserAccount			userAccountService;

	// Constructors -----------------------------------------------------------

	public EndorsementCustomerController() {
		super();
	}
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam("receiverId") final int receiverId) {
		ModelAndView result;

		Endorsement endorsement;

		endorsement = this.endorsementService.create();

		final Integer userAccountId = LoginService.getPrincipal().getId();
		Assert.notNull(userAccountId);
		final Customer customer = this.customerService.getCustomerByUserAccountId(userAccountId);
		final HandyWorker handyWorker = this.handyWorkerService.findOne(receiverId);
		Assert.isTrue(customer != null && handyWorker != null);
		endorsement.setendorsableReceiver(handyWorker);
		endorsement.setEndorsableSender(customer);
		endorsement.setMoment(LocalDate.now().toDate());

		result = new ModelAndView("endorsement/customer/edit");

		result.addObject("endorsement", endorsement);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam("endorsementId") final int endorsementId) {
		ModelAndView result;

		final Endorsement endorsement;
		Assert.isTrue(this.endorsementService.findOne(endorsementId) != null);
		endorsement = this.endorsementService.findOne(endorsementId);

		result = new ModelAndView("endorsement/customer/edit");

		result.addObject("endorsement", endorsement);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView saveEdit(@Valid final Endorsement endorsement, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			System.out.println("El error pasa por aqu� alvaro (IF de save())");
			System.out.println(binding);
			result = new ModelAndView("endorsement/customer/edit");
		} else
			try {
				Assert.isTrue(endorsement != null, "endorsement.null");
				final Endorsement saveEndorsement = this.endorsementService.save(endorsement);
				result = new ModelAndView("redirect:show.do");
				result.addObject("requestURI", "endorsement/customer/show.do");
			} catch (final Throwable oops) {
				System.out.println("El error es en endorsementController: ");
				System.out.println(oops);
				System.out.println(oops.getMessage());
				System.out.println(oops.getLocalizedMessage());
				System.out.println(binding);
				result = new ModelAndView("endorsement/customer/edit");
			}
		return result;
	}
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam("endorsementId") final int endorsementId) {
		ModelAndView result;

		final Endorsement endorsement = this.endorsementService.findOne(endorsementId);
		System.out.println("endorsementId encontrado: " + endorsementId);
		Assert.notNull(endorsementId, "endorsement.null");

		try {
			this.endorsementService.delete(endorsement);
			final Endorsable endorsable = endorsement.getEndorsableSender();
			result = new ModelAndView("endorsement/customer/show");
			result.addObject("endorsable", endorsable);
			result.addObject("requestURI", "redirect:show.do");
		} catch (final Throwable oops) {
			System.out.println("Error al borrar endorsement desde hw: ");
			System.out.println(oops);
			result = new ModelAndView("redirect:show.do");
		}
		return result;
	}

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		final int userLoggin = LoginService.getPrincipal().getId();
		final HandyWorker handyWorker = this.handyWorkerService.findByUserAccountId(userLoggin);
		final Customer customer = this.customerService.getCustomerByUserAccountId(userLoggin);
		if (handyWorker != null) {
			result = new ModelAndView("endorsement/handyWorker/show");
			final Collection<Endorsement> endorsementSend = this.endorsementService.getEndorsementSender(handyWorker.getId());
			result.addObject("endorsementSend", endorsementSend);
			result.addObject("requestURI", "endorsement/handyWorker/show.do");
		} else {
			Assert.notNull(customer, "customer.null");
			result = new ModelAndView("endorsement/customer/show");
			final Collection<Endorsement> endorsementSend = this.endorsementService.getEndorsementSender(customer.getId());
			result.addObject("endorsementSend", endorsementSend);
			result.addObject("requestURI", "endorsement/handyWorker/show.do");
		}

		return result;
	}
}