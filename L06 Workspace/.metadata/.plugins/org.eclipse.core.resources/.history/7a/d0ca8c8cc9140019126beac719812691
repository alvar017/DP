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

import security.LoginService;
import security.UserAccount;
import services.ActorService;
import services.FixUpService;
import services.HandyWorkerService;
import services.MailBoxService;
import domain.Actor;
import domain.MailBox;

@Controller
@RequestMapping("/mailBox")
public class MailBoxController extends AbstractController {

	@Autowired
	private MailBoxService		mailBoxService;
	@Autowired
	private HandyWorkerService	handyWorkerService;
	@Autowired
	private FixUpService		fixUpService;
	@Autowired
	private ActorService		actorService;


	// Constructors -----------------------------------------------------------

	public MailBoxController() {
		super();
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView listMailBox() {
		final ModelAndView result;

		final UserAccount login = LoginService.getPrincipal();

		final Actor logged = this.actorService.getActorByUserId(login.getId());
		System.out.println(logged.getName());
		Assert.notNull(logged);

		final Collection<MailBox> mailBoxes = logged.getMailBoxes();

		System.out.println(mailBoxes);

		result = new ModelAndView("mailBox/list");
		result.addObject("mailBoxes", mailBoxes);
		result.addObject("requestURI", "mailBox/list.do");

		return result;
	}
	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(@RequestParam("mailBoxId") final int mailBoxId) {
		ModelAndView result;

		final MailBox mailBox = this.mailBoxService.findOne(mailBoxId);
		final String language = LocaleContextHolder.getLocale().getDisplayLanguage();

		result = new ModelAndView("mailBox/show");
		result.addObject("mailBox", mailBox);
		result.addObject("language", language);
		result.addObject("requestURI", "mailBox/show.do");

		return result;
	}
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		final MailBox mailBox;

		System.out.println("Entro en el create");

		mailBox = this.mailBoxService.create();
		result = this.createEditModelAndView(mailBox);
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int mailBoxId) {
		ModelAndView result;
		final MailBox mailBox;

		mailBox = this.mailBoxService.findOne(mailBoxId);
		Assert.notNull(mailBox);
		result = this.createEditModelAndView(mailBox);
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final MailBox mailBox, final BindingResult binding) {
		ModelAndView result;
		final UserAccount login = LoginService.getPrincipal();
		final Actor logged = this.actorService.getActorByUserId(login.getId());

		System.out.println(mailBox);

		System.out.println(binding);
		System.out.println(mailBox);
		System.out.println("Entro en el save");

		if (binding.hasErrors()) {
			System.out.println("Entro en el binding");
			System.out.println(binding.getAllErrors().get(0));
			result = this.createEditModelAndView(mailBox);
		} else
			try {
				System.out.println("entro en update");
				final MailBox updated = this.mailBoxService.update(mailBox);
				if (!logged.getMailBoxes().contains(mailBox)) {
					logged.getMailBoxes().add(updated);
					final Actor saved = this.actorService.save(logged);
				}
				System.out.println(logged.getMailBoxes());
				result = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				System.out.println("entro en oopss");
				System.out.println(oops);
				result = this.createEditModelAndView(mailBox, "mailBox.commit.error");
			}

		return result;
	}
	protected ModelAndView createEditModelAndView(final MailBox mailBox) {
		ModelAndView result;

		result = this.createEditModelAndView(mailBox, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final MailBox mailBox, final String messageCode) {
		ModelAndView result;
		final UserAccount login = LoginService.getPrincipal();
		final Actor logged = this.actorService.getActorByUserId(login.getId());

		final Boolean idMail = mailBox.getId() == 0;

		System.out.println(mailBox.getId() == 0);

		if (mailBox.getIsDefault() == true || mailBox == null || (!logged.getMailBoxes().contains(mailBox) && !idMail)) {
			result = new ModelAndView("mailBox/list");
			result.addObject("mailBoxes", logged.getMailBoxes());
			result.addObject("requestURI", "mailBox/list.do");
			return result;
		}

		result = new ModelAndView("mailBox/edit");
		result.addObject("mailBox", mailBox);
		result.addObject("message", messageCode);

		return result;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam final int mailBoxId) {
		ModelAndView result;

		final MailBox mailBox = this.mailBoxService.findOne(mailBoxId);

		final UserAccount login = LoginService.getPrincipal();
		final Actor logged = this.actorService.getActorByUserId(login.getId());

		if (mailBox.getIsDefault() == true || mailBox == null || !logged.getMailBoxes().contains(mailBox)) {
			result = new ModelAndView("mailBox/list");
			result.addObject("mailBoxes", logged.getMailBoxes());
			result.addObject("requestURI", "mailBox/list.do");
			return result;
		}

		final String language = LocaleContextHolder.getLocale().getDisplayLanguage();

		System.out.println("Mailbox encontrado: " + mailBox);
		Assert.notNull(mailBox, "mailBox.null");

		try {
			System.out.println("entra en delete MailBox");
			this.mailBoxService.delete(mailBox);
			result = new ModelAndView("redirect:list.do");
		} catch (final Exception e) {
			System.out.println(e);
			System.out.println("entra en catch");
			result = this.createEditModelAndView(mailBox, "mailbox.commit.error");
		}

		result.addObject("language", language);
		return result;
	}
}
