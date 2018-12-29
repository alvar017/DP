/*
 * sponsorController.java
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
import org.springframework.web.servlet.ModelAndView;

import security.LoginService;
import services.SponsorService;
import domain.Sponsor;

@Controller
@RequestMapping("/sponsor")
public class SponsorController extends AbstractController {

	@Autowired
	private SponsorService	sponsorService;


	// Constructors -----------------------------------------------------------

	public SponsorController() {
		super();
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;

		Sponsor sponsor;

		sponsor = this.sponsorService.create();

		result = new ModelAndView("sponsor/create");

		result.addObject("sponsor", sponsor);

		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Sponsor sponsor, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			System.out.println("El error pasa por aqu� alvaro (IF de save())");
			System.out.println(binding);
			result = new ModelAndView("sponsor/create");
		} else
			try {
				System.out.println("El error pasa por aqu� alvaro (TRY de save())");
				System.out.println(binding);
				final String password = sponsor.getUserAccount().getPassword();
				final Md5PasswordEncoder encoder = new Md5PasswordEncoder();
				final String hashPassword = encoder.encodePassword(password, null);
				sponsor.getUserAccount().setPassword(hashPassword);
				this.sponsorService.save(sponsor);
				result = new ModelAndView("welcome/index");
			} catch (final Throwable oops) {
				System.out.println("El error: ");
				System.out.println(oops);
				System.out.println(binding);
				result = new ModelAndView("sponsor/create");
			}
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView result;

		Sponsor sponsor;

		final int idUserAccount = LoginService.getPrincipal().getId();

		sponsor = this.sponsorService.findByUserAccountId(idUserAccount);

		result = new ModelAndView("sponsor/edit");

		result.addObject("sponsor", sponsor);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView saveEdit(@Valid final Sponsor sponsor, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			System.out.println("El error pasa por aqu� alvaro (IF de save())");
			System.out.println(binding);
			result = new ModelAndView("sponsor/edit");
		} else
			try {
				Assert.notNull(sponsor, "sponsor.null");
				final String password = sponsor.getUserAccount().getPassword();
				final Md5PasswordEncoder encoder = new Md5PasswordEncoder();
				final String hashPassword = encoder.encodePassword(password, null);
				sponsor.getUserAccount().setPassword(hashPassword);
				this.sponsorService.save(sponsor);
				result = new ModelAndView("actor/show");
				result.addObject("actor", sponsor);
			} catch (final Throwable oops) {
				System.out.println("El error es en sponsorController: ");
				System.out.println(oops);
				System.out.println(binding);
				result = new ModelAndView("sponsor/edit");
			}
		return result;
	}
}
