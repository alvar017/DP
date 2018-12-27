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

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import security.LoginService;
import services.ActorService;
import services.CustomerService;
import services.HandyWorkerService;
import domain.Actor;
import domain.Customer;
import domain.HandyWorker;

@Controller
@RequestMapping("/actor")
public class ActorController extends AbstractController {

	@Autowired
	private ActorService		actorService;
	@Autowired
	private CustomerService		customerService;
	@Autowired
	private HandyWorkerService	handyWorkerService;


	// Constructors -----------------------------------------------------------

	public ActorController() {
		super();
	}

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		final int userLoggin = LoginService.getPrincipal().getId();
		final Actor actor = this.actorService.getActorByUserId(userLoggin);
		Assert.isTrue(actor != null);

		result = new ModelAndView("actor/show");
		result.addObject("actor", actor);
		result.addObject("socialProfiles", actor.getSocialProfiles());
		result.addObject("requestURI", "actor/show.do");

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView result;

		Actor actor;

		final int idUserAccount = LoginService.getPrincipal().getId();

		actor = this.actorService.findOneByUserAccountId(idUserAccount);
		Assert.notNull(actor);

		result = new ModelAndView("actor/edit");

		result.addObject("actor", actor);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final HandyWorker handyWorker, @Valid final Actor actor, final BindingResult binding, @RequestParam(required = false) final String make) {
		ModelAndView result;

		if (binding.hasErrors()) {
			System.out.println("El error pasa por aqu� alvaro (IF de save())");
			System.out.println(binding);
			result = new ModelAndView("actor/edit");
		} else
			try {
				System.out.println("El error pasa por aqu� alvaro (TRY de save())");
				System.out.println(binding);
				//				final String password = actor.getUserAccount().getPassword();
				//				final Md5PasswordEncoder encoder = new Md5PasswordEncoder();
				//				final String hashPassword = encoder.encodePassword(password, null);
				//				actor.getUserAccount().setPassword(hashPassword);
				if (this.customerService.findOne(actor.getId()) != null) {
					final String password = actor.getUserAccount().getPassword();
					final Md5PasswordEncoder encoder = new Md5PasswordEncoder();
					final String hashPassword = encoder.encodePassword(password, null);
					actor.getUserAccount().setPassword(hashPassword);
					final Customer customer = this.customerService.findOne(actor.getId());
					customer.setName(actor.getName());
					customer.setAddress(actor.getAddress());
					customer.setSurname(actor.getSurname());
					customer.setMiddleName(actor.getMiddleName());
					customer.setEmail(actor.getEmail());
					customer.setPhoto(actor.getPhoto());
					customer.getUserAccount().setUsername(actor.getUserAccount().getUsername());
					customer.getUserAccount().setPassword(actor.getUserAccount().getPassword());
					this.customerService.save(customer);
				} else if (this.handyWorkerService.findOne(handyWorker.getId()) != null) {
					//					final HandyWorker handyWorker = this.handyWorkerService.findOne(actor.getId());
					handyWorker.setName(actor.getName());
					handyWorker.setAddress(actor.getAddress());
					handyWorker.setSurname(actor.getSurname());
					handyWorker.setMiddleName(actor.getMiddleName());
					handyWorker.setEmail(actor.getEmail());
					handyWorker.setPhoto(actor.getPhoto());
					handyWorker.getUserAccount().setUsername(actor.getUserAccount().getUsername());
					handyWorker.getUserAccount().setPassword(actor.getUserAccount().getPassword());
					handyWorker.setMake(make);
					this.handyWorkerService.save(handyWorker);
				} else
					this.actorService.save(actor);
				result = new ModelAndView("welcome/index");
			} catch (final Throwable oops) {
				System.out.println("El error: ");
				System.out.println(oops);
				System.out.println(binding);
				result = new ModelAndView("actor/edit");
			}
		return result;
	}
}
