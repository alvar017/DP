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
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import security.UserAccountRepository;
import services.ActorService;
import services.RefereeService;
import services.WelcomeService;
import domain.Referee;

@Controller
@RequestMapping("/referee/administrator")
public class RefereeAdministratorController extends AbstractController {

	@Autowired
	private RefereeService			refereeService;
	@Autowired
	private UserAccountRepository	userAccountService;
	@Autowired
	private ActorService			actorService;
	@Autowired
	private WelcomeService			welcomeService;


	// Constructors -----------------------------------------------------------

	public RefereeAdministratorController() {
		super();
	}

	@RequestMapping(value = "/list")
	public ModelAndView list() {
		ModelAndView result;
		final Collection<Referee> referee = this.refereeService.findAll();
		final String language = LocaleContextHolder.getLocale().getDisplayLanguage();

		System.out.println("Carmen: Entro en el list");

		System.out.println(referee);

		result = new ModelAndView("referee/administrator/list");
		result.addObject("referee", referee);
		result.addObject("language", language);
		final String system = this.welcomeService.getSystem();
		result.addObject("system", system);
		final String logo = this.welcomeService.getLogo();
		result.addObject("logo", logo);
		result.addObject("requestURI", "actor/administrator/list.do");

		System.out.println("Carmen: Salgo del list");

		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;

		Referee referee;

		referee = this.refereeService.create();

		result = new ModelAndView("referee/administrator/create");
		final String system = this.welcomeService.getSystem();
		result.addObject("system", system);
		final String logo = this.welcomeService.getLogo();
		result.addObject("logo", logo);

		result.addObject("referee", referee);

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

		if (this.userAccountService.findByUsername(referee.getUserAccount().getUsername()) != null) {
			final ObjectError error = new ObjectError("userAccount.username", "An account already exists for this email.");
			binding.addError(error);
			binding.rejectValue("userAccount.username", "error.userAccount.username.exits");
		}

		if (binding.hasErrors()) {
			System.out.println("Carmen: Hay errores");
			System.out.println("Carmen: El error es este:" + binding);
			result = new ModelAndView("referee/administrator/create");
		} else
			try {
				System.out.println(binding);
				final String password = referee.getUserAccount().getPassword();
				final Md5PasswordEncoder encoder = new Md5PasswordEncoder();
				final String hashPassword = encoder.encodePassword(password, null);
				referee.getUserAccount().setPassword(hashPassword);
				this.refereeService.save(referee);
				result = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				System.out.println("El error: ");
				System.out.println(oops);
				System.out.println(binding);
				result = new ModelAndView("referee/administrator/create");
			}
		return result;
	}
}
