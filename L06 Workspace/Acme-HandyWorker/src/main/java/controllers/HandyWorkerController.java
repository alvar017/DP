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

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import security.LoginService;
import security.UserAccountRepository;
import services.HandyWorkerService;
import services.WelcomeService;
import domain.HandyWorker;

@Controller
@RequestMapping("/handyWorker")
public class HandyWorkerController extends AbstractController {

	@Autowired
	private HandyWorkerService		handyWorkerService;
	@Autowired
	private UserAccountRepository	userAccountService;
	@Autowired
	private WelcomeService			welcomeService;


	//	@Autowired
	//	private UserAccount			userAccountService;

	// Constructors -----------------------------------------------------------

	public HandyWorkerController() {
		super();
	}
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;

		HandyWorker handyWorker;

		handyWorker = this.handyWorkerService.create();

		result = new ModelAndView("handyWorker/create");

		result.addObject("handyWorker", handyWorker);
		final Integer phone = this.welcomeService.getPhone();
		result.addObject("phone", phone);

		return result;
	}

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		final int userLoggin = LoginService.getPrincipal().getId();
		final HandyWorker handyWorker = this.handyWorkerService.findByUserAccountId(userLoggin);
		Assert.isTrue(handyWorker != null);

		result = new ModelAndView("handyWorker/show");
		result.addObject("handyWorker", handyWorker);
		result.addObject("socialProfiles", handyWorker.getSocialProfiles());
		result.addObject("requestURI", "handyWorker/show.do");
		final Integer phone = this.welcomeService.getPhone();
		result.addObject("phone", phone);

		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final HandyWorker handyWorker, final BindingResult binding) {
		ModelAndView result;
		if (handyWorker.getUserAccount().getPassword().length() < 5 || handyWorker.getUserAccount().getPassword().length() > 32) {
			final ObjectError error = new ObjectError("userAccount.password", "An account already exists for this email.");
			binding.addError(error);
			binding.rejectValue("userAccount.password", "error.userAccount.password");
		}

		if (handyWorker.getUserAccount().getUsername().length() < 5 || handyWorker.getUserAccount().getUsername().length() > 32) {
			final ObjectError error = new ObjectError("userAccount.username", "An account already exists for this email.");
			binding.addError(error);
			binding.rejectValue("userAccount.username", "error.userAccount.username");
		}
		if (this.userAccountService.findByUsername(handyWorker.getUserAccount().getUsername()) != null) {
			final ObjectError error = new ObjectError("userAccount.username", "An account already exists for this email.");
			binding.addError(error);
			binding.rejectValue("userAccount.username", "error.userAccount.username.exits");
		}
		if (binding.hasErrors()) {
			System.out.println("El error pasa por aqu� alvaro (IF de save())");
			System.out.println(binding);
			result = new ModelAndView("handyWorker/create");
		} else
			try {
				System.out.println("El error pasa por aqu� alvaro (TRY de save())");
				System.out.println(binding);
				//				Assert.isTrue(this.userAccountService.findByUsername(handyWorker.getUserAccount().getUsername()) == null, "hw.usedUsername");
				final String password = handyWorker.getUserAccount().getPassword();
				final Md5PasswordEncoder encoder = new Md5PasswordEncoder();
				final String hashPassword = encoder.encodePassword(password, null);
				handyWorker.getUserAccount().setPassword(hashPassword);
				this.handyWorkerService.save(handyWorker);
				result = new ModelAndView("welcome/index");
			} catch (final Throwable oops) {
				System.out.println("El error: ");
				System.out.println(oops);
				System.out.println(binding);
				result = new ModelAndView("handyWorker/create");
			}
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView result;

		HandyWorker handyWorker;

		final int idUserAccount = LoginService.getPrincipal().getId();

		handyWorker = this.handyWorkerService.getHandyWorkerByUserAccountId(idUserAccount);
		Assert.notNull(handyWorker);

		result = new ModelAndView("handyWorker/edit");

		result.addObject("handyWorker", handyWorker);
		final Integer phone = this.welcomeService.getPhone();
		result.addObject("phone", phone);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView saveEdit(@Valid final HandyWorker handyWorker, final BindingResult binding) {
		ModelAndView result;
		if (handyWorker.getUserAccount().getPassword().length() < 5 || handyWorker.getUserAccount().getPassword().length() > 32) {
			final ObjectError error = new ObjectError("userAccount.password", "An account already exists for this email.");
			binding.addError(error);
			binding.rejectValue("userAccount.password", "error.userAccount.password");
		}

		if (handyWorker.getUserAccount().getUsername().length() < 5 || handyWorker.getUserAccount().getUsername().length() > 32) {
			final ObjectError error = new ObjectError("userAccount.username", "An account already exists for this email.");
			binding.addError(error);
			binding.rejectValue("userAccount.username", "error.userAccount.username");
		}
		if (!this.userAccountService.findByUsername(handyWorker.getUserAccount().getUsername()).equals(handyWorker.getUserAccount())) {
			final ObjectError error = new ObjectError("userAccount.username", "An account already exists for this email.");
			binding.addError(error);
			binding.rejectValue("userAccount.username", "error.userAccount.username.exits");
		}
		if (binding.hasErrors()) {
			System.out.println("El error pasa por aqu� alvaro (IF de save())");
			System.out.println(binding);
			result = new ModelAndView("handyWorker/edit");
		} else
			try {
				Assert.isTrue(this.handyWorkerService.findOne(handyWorker.getId()) != null);
				this.handyWorkerService.save(handyWorker);
				result = new ModelAndView("welcome/index");
			} catch (final Throwable oops) {
				System.out.println("El error es en handyWorkerController: ");
				System.out.println(oops);
				System.out.println(binding);
				result = new ModelAndView("handyWorker/edit");
			}
		return result;
	}

	//A�adidos
	@RequestMapping(value = "/showG", method = RequestMethod.GET)
	public ModelAndView show(@RequestParam(value = "handyWorkerId", defaultValue = "-1") final int handyWorkerId) {
		ModelAndView result;
		final HandyWorker handyWorker = this.handyWorkerService.findOne(handyWorkerId);
		Assert.isTrue(handyWorker != null);

		result = new ModelAndView("handyWorker/showG");
		result.addObject("handyWorker", handyWorker);
		result.addObject("tutorials", handyWorker.getTutorials());
		result.addObject("requestURI", "handyWorker/showG.do");

		return result;
	}
}
