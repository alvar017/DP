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
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.FixUpService;
import services.HandyWorkerService;
import services.WarrantyService;
import services.WelcomeService;
import domain.Warranty;

@Controller
@RequestMapping("/warranty/administrator")
public class AdministratorWarrantyController extends AbstractController {

	@Autowired
	private WarrantyService		warrantyService;
	@Autowired
	private HandyWorkerService	handyWorkerService;
	@Autowired
	private FixUpService		fixUpService;
	@Autowired
	private WelcomeService		welcomeService;


	// Constructors -----------------------------------------------------------

	public AdministratorWarrantyController() {
		super();
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView listWarranties() {
		final ModelAndView result;

		final Collection<Warranty> warranties = this.warrantyService.findAll();

		result = new ModelAndView("warranty/administrator/list");
		result.addObject("warranties", warranties);
		final String system = this.welcomeService.getSystem();
		result.addObject("system", system);
		final String logo = this.welcomeService.getLogo();
		result.addObject("logo", logo);

		result.addObject("requestURI", "warranty/administrator/list.do");

		return result;
	}

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(@RequestParam("warrantyId") final int warrantyId) {
		ModelAndView result;

		final Warranty warranty = this.warrantyService.findOne(warrantyId);
		final String language = LocaleContextHolder.getLocale().getDisplayLanguage();

		result = new ModelAndView("warranty/administrator/show");
		result.addObject("warranty", warranty);
		result.addObject("language", language);
		final String system = this.welcomeService.getSystem();
		result.addObject("system", system);
		final String logo = this.welcomeService.getLogo();
		result.addObject("logo", logo);

		result.addObject("requestURI", "warranty/administrator/show.do");

		return result;
	}
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		final Warranty warranty;

		System.out.println("Entro en el create");

		warranty = this.warrantyService.create();
		result = this.createEditModelAndView(warranty);
		final String logo = this.welcomeService.getLogo();
		result.addObject("logo", logo);
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam(value = "warrantyId", defaultValue = "-1") final int warrantyId) {
		ModelAndView result;
		final Warranty warranty;
		final Collection<Warranty> warranties = this.warrantyService.findAll();

		if (warrantyId == -1) {
			result = new ModelAndView("warranty/administrator/list");
			result.addObject("warranties", warranties);
			final String logo = this.welcomeService.getLogo();
			result.addObject("logo", logo);
		} else {
			warranty = this.warrantyService.findOne(warrantyId);
			Assert.notNull(warranty);
			result = this.createEditModelAndView(warranty);
			final String logo = this.welcomeService.getLogo();
			result.addObject("logo", logo);
		}
		return result;
	}
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Warranty warranty, final BindingResult binding) {
		ModelAndView result;
		System.out.println(warranty);
		System.out.println("Entro en el save");
		if (binding.hasErrors()) {
			System.out.println("Entro en el binding");
			System.out.println(binding.getAllErrors().get(0));
			result = this.createEditModelAndView(warranty);
			final String logo = this.welcomeService.getLogo();
			result.addObject("logo", logo);
		} else
			try {
				this.warrantyService.save(warranty);
				result = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				System.out.println(oops);
				result = this.createEditModelAndView(warranty, "warranty.commit.error");
				final String logo = this.welcomeService.getLogo();
				result.addObject("logo", logo);
			}

		return result;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam("id") final int warrantyId) {
		ModelAndView result;

		final String language = LocaleContextHolder.getLocale().getDisplayLanguage();
		final Warranty warranty = this.warrantyService.findOne(warrantyId);
		System.out.println("Warranty encontrado: " + warranty);
		if (warranty.getIsFinal() == true) {
			result = new ModelAndView("redirect:list.do");
			final String logo = this.welcomeService.getLogo();
			result.addObject("logo", logo);
			return result;
		}
		Assert.notNull(warranty, "warranty.null");

		try {
			this.warrantyService.delete(warranty);
			result = new ModelAndView("redirect:list.do");
			final String logo = this.welcomeService.getLogo();
			result.addObject("logo", logo);
		} catch (final Exception e) {
			result = new ModelAndView("welcome/index");
			final String logo = this.welcomeService.getLogo();
			result.addObject("logo", logo);
		}

		result.addObject("language", language);
		return result;
	}
	protected ModelAndView createEditModelAndView(final Warranty warranty) {
		ModelAndView result;

		result = this.createEditModelAndView(warranty, null);
		final String system = this.welcomeService.getSystem();
		result.addObject("system", system);
		final String logo = this.welcomeService.getLogo();
		result.addObject("logo", logo);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Warranty warranty, final String messageCode) {
		ModelAndView result;

		if (warranty.getIsFinal() == true) {
			result = new ModelAndView("welcome/index");
			return result;
		}

		result = new ModelAndView("warranty/administrator/edit");
		result.addObject("warranty", warranty);
		final String system = this.welcomeService.getSystem();
		result.addObject("system", system);
		final String logo = this.welcomeService.getLogo();
		result.addObject("logo", logo);

		result.addObject("message", messageCode);

		return result;
	}
}
