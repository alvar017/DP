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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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

import security.LoginService;
import security.UserAccount;
import services.CategoryService;
import services.FixUpService;
import services.HandyWorkerService;
import services.WelcomeService;
import domain.Category;
import domain.FixUp;

@Controller
@RequestMapping("/category/administrator")
public class AdministratorCategoryController extends AbstractController {

	@Autowired
	private CategoryService		categoryService;
	@Autowired
	private HandyWorkerService	handyWorkerService;
	@Autowired
	private FixUpService		fixUpService;
	@Autowired
	private WelcomeService		welcomeService;


	// Constructors -----------------------------------------------------------

	public AdministratorCategoryController() {
		super();
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView listCategories() {
		final ModelAndView result;

		final UserAccount login = LoginService.getPrincipal();

		final String language = LocaleContextHolder.getLocale().getDisplayLanguage();

		final Collection<Category> categories = this.categoryService.findAll();

		result = new ModelAndView("category/administrator/list");
		result.addObject("language", language);
		result.addObject("categories", categories);
		result.addObject("requestURI", "category/administrator/list.do");
		final String system = this.welcomeService.getSystem();
		result.addObject("system", system);
		final String logo = this.welcomeService.getLogo();
		result.addObject("logo", logo);

		return result;
	}

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(@RequestParam("categoryId") final int categoryId) {
		ModelAndView result;

		final Category category = this.categoryService.findOne(categoryId);
		final String language = LocaleContextHolder.getLocale().getDisplayLanguage();

		result = new ModelAndView("category/administrator/show");
		result.addObject("category", category);
		result.addObject("language", language);
		result.addObject("requestURI", "category/administrator/show.do");
		final String system = this.welcomeService.getSystem();
		result.addObject("system", system);
		final String logo = this.welcomeService.getLogo();
		result.addObject("logo", logo);

		return result;
	}
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		final Category category;

		System.out.println("Entro en el create");

		category = this.categoryService.create();
		result = this.createEditModelAndView(category);
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int categoryId) {
		ModelAndView result;
		final Category category;

		category = this.categoryService.findOne(categoryId);
		Assert.notNull(category);
		result = this.createEditModelAndView(category);
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Category category, final BindingResult binding) {
		ModelAndView result;
		System.out.println(category);
		System.out.println(category.getSubCategories());
		System.out.println("Entro en el save");
		if (binding.hasErrors()) {
			System.out.println("Entro en el binding");
			System.out.println(binding.getAllErrors().get(0));
			result = this.createEditModelAndView(category);
		} else
			try {
				this.categoryService.save(category);
				result = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				System.out.println(oops);
				result = this.createEditModelAndView(category, "category.commit.error");
			}

		return result;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam("id") final int categoryId) {
		ModelAndView result;

		final String language = LocaleContextHolder.getLocale().getDisplayLanguage();
		final Category category = this.categoryService.findOne(categoryId);
		System.out.println("Category encontrado: " + category);
		Assert.notNull(category, "category.null");

		final Collection<Category> categories = this.categoryService.findAll();
		final List<Category> categoriesList = new ArrayList<>();
		categoriesList.addAll(categories);

		try {
			for (int i = 0; i < categories.size(); i++)
				if (categoriesList.get(i).getSubCategories().contains(category)) {
					category.setSubCategories(null);
					System.out.println("PASA ESTO1");
					categoriesList.get(i).getSubCategories().remove(category);
					System.out.println("PASA ESTO2");
				}
			final Collection<FixUp> fixUps = this.fixUpService.findAll();
			final List<FixUp> fixUpsList = new ArrayList<>();
			fixUpsList.addAll(fixUps);
			System.out.println("PASA ESTO3");
			for (int i = 0; i < fixUps.size(); i++)
				if (fixUpsList.get(i).getCategory() != null)
					if (fixUpsList.get(i).getCategory().getId() == categoryId)
						fixUpsList.get(i).setCategory(null);
			this.categoryService.delete(category);
			result = new ModelAndView("redirect:list.do");
		} catch (final Exception e) {
			System.out.println(e);
			result = new ModelAndView("redirect:list.do");
		}

		result.addObject("language", language);
		return result;
	}
	protected ModelAndView createEditModelAndView(final Category category) {
		ModelAndView result;

		result = this.createEditModelAndView(category, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Category category, final String messageCode) {
		ModelAndView result;
		final Collection<Category> categories = this.categoryService.findAll();
		final String language = LocaleContextHolder.getLocale().getDisplayLanguage();
		System.out.println(language);
		final Collection<String> nameCategories = new ArrayList<>();
		if (language == "English")
			for (final Category category2 : categories)
				nameCategories.add(category2.getNameEN());
		else
			for (final Category category2 : categories)
				nameCategories.add(category2.getNameES());

		if (nameCategories.contains(category.getNameEN()))
			nameCategories.remove(category.getNameEN());

		if (nameCategories.contains(category.getNameES()))
			nameCategories.remove(category.getNameES());

		result = new ModelAndView("category/administrator/edit");
		result.addObject("category", category);
		result.addObject("categories", categories);
		result.addObject("nameCategories", nameCategories);
		result.addObject("message", messageCode);
		final String system = this.welcomeService.getSystem();
		result.addObject("system", system);
		final String logo = this.welcomeService.getLogo();
		result.addObject("logo", logo);

		return result;
	}
}
