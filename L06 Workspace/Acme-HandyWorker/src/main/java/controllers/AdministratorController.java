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
import services.AdministratorService;
import domain.Administrator;

@Controller
@RequestMapping("/administrator")
public class AdministratorController extends AbstractController {

	@Autowired
	AdministratorService	administratorService;


	// Constructors -----------------------------------------------------------

	public AdministratorController() {
		super();
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView result;

		Administrator administrator;

		final int idUserAccount = LoginService.getPrincipal().getId();

		administrator = this.administratorService.findByUserAccount(idUserAccount);

		result = new ModelAndView("administrator/edit");

		result.addObject("administrator", administrator);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView saveEdit(@Valid final Administrator administrator, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			System.out.println("El error pasa por aqu� alvaro (IF de save())");
			System.out.println(binding);
			result = new ModelAndView("administrator/edit");
		} else
			try {
				Assert.notNull(administrator, "administrator.null");
				final String password = administrator.getUserAccount().getPassword();
				final Md5PasswordEncoder encoder = new Md5PasswordEncoder();
				final String hashPassword = encoder.encodePassword(password, null);
				administrator.getUserAccount().setPassword(hashPassword);
				this.administratorService.save(administrator);
				result = new ModelAndView("actor/show");
				result.addObject("actor", administrator);
			} catch (final Throwable oops) {
				System.out.println("El error es en administratorController: ");
				System.out.println(oops);
				System.out.println(binding);
				result = new ModelAndView("administrator/edit");
			}
		return result;
	}
}
