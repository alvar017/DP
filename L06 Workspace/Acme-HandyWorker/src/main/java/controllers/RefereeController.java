/*
 * refereeController.java
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
import org.springframework.web.servlet.ModelAndView;

import security.LoginService;
import security.UserAccountRepository;
import services.ActorService;
import services.RefereeService;
import services.WelcomeService;
import domain.Referee;

@Controller
@RequestMapping("/referee")
public class RefereeController extends AbstractController {

	@Autowired
	private RefereeService			refereeService;
	@Autowired
	private UserAccountRepository	userAccountService;
	@Autowired
	WelcomeService					welcomeService;
	@Autowired
	ActorService					actorService;


	//	@Autowired
	//	private UserAccount			userAccountService;

	// Constructors -----------------------------------------------------------

	public RefereeController() {
		super();
	}
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;

		Referee referee;

		referee = this.refereeService.create();

		result = new ModelAndView("referee/create");

		result.addObject("referee", referee);
		final String phone = this.welcomeService.getPhone();
		result.addObject("phone", phone);

		return result;
	}

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		final int userLoggin = LoginService.getPrincipal().getId();
		final Referee referee = this.refereeService.findByUserAccountId(userLoggin);
		Assert.isTrue(referee != null);

		result = new ModelAndView("referee/show");
		result.addObject("referee", referee);
		result.addObject("socialProfiles", referee.getSocialProfiles());
		result.addObject("requestURI", "referee/show.do");
		final String phone = this.welcomeService.getPhone();
		result.addObject("phone", phone);

		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Referee referee, final BindingResult binding) {
		ModelAndView result;
		if (this.actorService.getActorByEmail(referee.getEmail()) != null) {
			final ObjectError error = new ObjectError("actor.email", "An account already exists for this email.");
			binding.addError(error);
			binding.rejectValue("email", "error.actor.email.exits");
		}

		if (binding.hasErrors()) {
			System.out.println("El error pasa por aquí alvaro (IF de save())");
			System.out.println(binding);
			result = new ModelAndView("referee/create");
		} else
			try {
				System.out.println("El error pasa por aquí alvaro (TRY de save())");
				System.out.println(binding);
				//				Assert.isTrue(this.userAccountService.findByUsername(referee.getUserAccount().getUsername()) == null, "hw.usedUsername");
				final String password = referee.getUserAccount().getPassword();
				final Md5PasswordEncoder encoder = new Md5PasswordEncoder();
				final String hashPassword = encoder.encodePassword(password, null);
				referee.getUserAccount().setPassword(hashPassword);
				this.refereeService.save(referee);
				result = new ModelAndView("welcome/index");
			} catch (final Throwable oops) {
				System.out.println("El error: ");
				System.out.println(oops);
				System.out.println(binding);
				result = new ModelAndView("referee/create");
			}
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView result;

		Referee referee;

		final int idUserAccount = LoginService.getPrincipal().getId();

		referee = this.refereeService.findByUserAccountId(idUserAccount);
		Assert.notNull(referee);

		result = new ModelAndView("referee/edit");

		result.addObject("referee", referee);
		final String phone = this.welcomeService.getPhone();
		result.addObject("phone", phone);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView saveEdit(@Valid final Referee referee, final BindingResult binding) {
		ModelAndView result;
		if (referee.getUserAccount().getPassword().length() < 5 || referee.getUserAccount().getPassword().length() > 32) {
			final ObjectError error = new ObjectError("userAccount.password", "An account already exists for this email.");
			binding.addError(error);
			binding.rejectValue("userAccount.password", "error.userAccount.password");
		}

		if (referee.getUserAccount().getUsername().length() < 5 || referee.getUserAccount().getUsername().length() > 32) {
			final ObjectError error = new ObjectError("userAccount.password", "An account already exists for this email.");
			binding.addError(error);
			binding.rejectValue("userAccount.username", "error.userAccount.username");
		}

		if (!this.userAccountService.findByUsername(referee.getUserAccount().getUsername()).equals(referee.getUserAccount())) {
			final ObjectError error = new ObjectError("userAccount.username", "An account already exists for this email.");
			binding.addError(error);
			binding.rejectValue("userAccount.username", "error.userAccount.username.exits");
		}

		if (binding.hasErrors()) {
			System.out.println("El error pasa por aquí alvaro (IF de save())");
			System.out.println(binding);
			result = new ModelAndView("referee/edit");
		} else
			try {
				Assert.isTrue(this.refereeService.findOne(referee.getId()) != null);
				//				final String password = referee.getUserAccount().getPassword();
				//				final Md5PasswordEncoder encoder = new Md5PasswordEncoder();
				//				final String hashPassword = encoder.encodePassword(password, null);
				//				referee.getUserAccount().setPassword(hashPassword);
				this.refereeService.save(referee);
				result = new ModelAndView("redirect:show.do");
			} catch (final Throwable oops) {
				System.out.println("El error es en refereeController: ");
				System.out.println(oops);
				System.out.println(binding);
				result = new ModelAndView("referee/edit");
			}
		return result;
	}
}
