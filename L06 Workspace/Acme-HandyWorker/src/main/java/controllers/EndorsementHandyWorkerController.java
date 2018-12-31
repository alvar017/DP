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
import services.EndorsableService;
import services.EndorsementService;
import services.HandyWorkerService;
import domain.Customer;
import domain.Endorsable;
import domain.Endorsement;
import domain.HandyWorker;

@Controller
@RequestMapping("/endorsement/handyWorker")
public class EndorsementHandyWorkerController extends AbstractController {

	@Autowired
	private EndorsementService	endorsementService;
	@Autowired
	private EndorsableService	endorsableService;
	@Autowired
	private CustomerService		customerService;
	@Autowired
	private HandyWorkerService	handyWorkerService;


	//	@Autowired
	//	private UserAccount			userAccountService;

	// Constructors -----------------------------------------------------------

	public EndorsementHandyWorkerController() {
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
		final HandyWorker handyWorker = this.handyWorkerService.getHandyWorkerByUserAccountId(userAccountId);
		if (customer != null) {
			final HandyWorker handyWorker2 = this.handyWorkerService.findOne(receiverId);
			Assert.isTrue(customer != null && handyWorker2 != null);
			endorsement.setendorsableReceiver(handyWorker2);
			endorsement.setEndorsableSender(customer);
			endorsement.setMoment(LocalDate.now().toDate());
			result = new ModelAndView("endorsement/handyWorker/edit");
		} else {
			final Customer customer2 = this.customerService.findOne(receiverId);
			Assert.isTrue(customer2 != null && handyWorker != null);
			endorsement.setendorsableReceiver(customer2);
			endorsement.setEndorsableSender(handyWorker);
			endorsement.setMoment(LocalDate.now().toDate());
			result = new ModelAndView("endorsement/handyWorker/edit");
		}

		result.addObject("endorsement", endorsement);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam("endorsementId") final int endorsementId) {
		ModelAndView result;

		final Endorsement endorsement;
		Assert.isTrue(this.endorsementService.findOne(endorsementId) != null);
		endorsement = this.endorsementService.findOne(endorsementId);

		result = new ModelAndView("endorsement/handyWorker/edit");

		result.addObject("endorsement", endorsement);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView saveEdit(@Valid final Endorsement endorsement, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			System.out.println("El error pasa por aqu� alvaro (IF de save())");
			System.out.println(binding);
			result = new ModelAndView("endorsement/handyWorker/edit");
		} else
			try {
				Assert.isTrue(endorsement != null, "endorsement.null");
				final Endorsement saveEndorsement = this.endorsementService.save(endorsement);
				result = new ModelAndView("redirect:show.do");
				result.addObject("requestURI", "endorsement/handyWorker/show.do");
			} catch (final Throwable oops) {
				System.out.println("El error es en endorsementController: ");
				System.out.println(oops);
				System.out.println(oops.getMessage());
				System.out.println(oops.getLocalizedMessage());
				System.out.println(binding);
				result = new ModelAndView("endorsement/handyWorker/edit");
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
			endorsement.getEndorsableSender().getEndorsementSender().remove(endorsement);
			endorsement.getendorsableReceiver().getEndorsementReceiver().remove(endorsement);
			final Endorsable saveEndorsableSender = this.endorsableService.save(endorsement.getendorsableReceiver());
			final Endorsable savaEndrosableReceiver = this.endorsableService.save(endorsement.getendorsableReceiver());
			this.endorsementService.delete(endorsement);
			result = new ModelAndView("redirect:show.do");
			result.addObject("endorsable", saveEndorsableSender);
			result.addObject("requestURI", "endorsement/handyWorker/show");
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
			final Collection<Endorsement> endorsementReceived = this.endorsementService.getEndorsementReceiver(handyWorker.getId());
			result.addObject("endorsementSend", endorsementSend);
			result.addObject("endorsementReceived", endorsementReceived);
			result.addObject("requestURI", "endorsement/handyWorker/show.do");
			result.addObject("deleteURL", "endorsement/handyWorker/delete.do?endorsementId");
			result.addObject("editURL", "endorsement/handyWorker/edit.do?endorsementId");
		} else {
			Assert.notNull(customer, "customer.null");
			result = new ModelAndView("endorsement/customer/show");
			final Collection<Endorsement> endorsementSend = this.endorsementService.getEndorsementSender(customer.getId());
			final Collection<Endorsement> endorsementReceived = this.endorsableService.getendorsableByUserAccountId(userLoggin).getEndorsementReceiver();
			result.addObject("endorsementSend", endorsementSend);
			result.addObject("endorsementReceived", endorsementReceived);
			result.addObject("requestURI", "endorsement/customer/show.do");
			result.addObject("deleteURL", "endorsement/customer/delete.do?endorsementId");
			result.addObject("editURL", "endorsement/customer/edit.do?endorsementId");
		}

		return result;
	}
}
