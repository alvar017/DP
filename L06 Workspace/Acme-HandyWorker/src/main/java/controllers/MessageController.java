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
import org.springframework.web.bind.annotation.ModelAttribute;
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
import services.MessageService;
import domain.Actor;
import domain.MailBox;
import domain.Message;

@Controller
@RequestMapping("/message")
public class MessageController extends AbstractController {

	@Autowired
	private MessageService		messageService;
	@Autowired
	private HandyWorkerService	handyWorkerService;
	@Autowired
	private FixUpService		fixUpService;
	@Autowired
	private ActorService		actorService;
	@Autowired
	private MailBoxService		mailBoxService;


	// Constructors -----------------------------------------------------------

	public MessageController() {
		super();
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView listMessages(@RequestParam("mailBoxId") final int mailBoxId) {
		final ModelAndView result;

		final MailBox m = this.mailBoxService.findOne(mailBoxId);

		final Collection<Message> msgs = m.getMessages();

		result = new ModelAndView("message/list");
		result.addObject("msgs", msgs);
		result.addObject("requestURI", "message/list.do");

		return result;
	}
	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(@RequestParam("messageId") final int messageId) {
		ModelAndView result;

		final Message msg = this.messageService.findOne(messageId);
		final String language = LocaleContextHolder.getLocale().getDisplayLanguage();

		result = new ModelAndView("message/show");
		result.addObject("msg", msg);
		result.addObject("language", language);
		result.addObject("requestURI", "message/show.do");

		return result;
	}
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		final Message msg;

		System.out.println("Entro en el create");

		msg = this.messageService.create();
		result = this.createEditModelAndView(msg);
		return result;
	}

	@RequestMapping(value = "/createBroadcast", method = RequestMethod.GET)
	public ModelAndView createBroadcast() {
		ModelAndView result;
		final Message msg;

		System.out.println("Entro en el create");

		msg = this.messageService.create();
		result = this.createEditModelAndViewBroadcast(msg);
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int messageId) {
		ModelAndView result;
		final Message msg;

		msg = this.messageService.findOne(messageId);
		Assert.notNull(msg);
		result = this.createEditModelAndView(msg);
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView send(@ModelAttribute("msg") @Valid Message msg, final BindingResult binding, @RequestParam final String emailReceiver) {
		ModelAndView result;
		final UserAccount login = LoginService.getPrincipal();
		final Actor sender = this.actorService.getActorByUserId(login.getId());
		System.out.println(msg.getEmailReceiver());
		System.out.println("antes de exchangeMessage");
		final List<String> lisEmailReceiver = new ArrayList<>();
		lisEmailReceiver.addAll(msg.getEmailReceiver());
		for (int i = 0; i < msg.getEmailReceiver().size(); i++) {
			final Actor receiverIndex = this.actorService.getActorByEmail(lisEmailReceiver.get(i));
			msg = this.messageService.exchangeMessage(msg, receiverIndex.getId());
			System.out.println(receiverIndex);
			System.out.println(this.mailBoxService.getInBoxActor(receiverIndex.getId()).getMessages());
		}

		System.out.println("despues de exchangeMessage");
		System.out.println(sender);
		System.out.println(msg);
		System.out.println(msg.getMailBoxes());
		System.out.println(this.mailBoxService.getOutBoxActor(sender.getId()).getMessages());

		System.out.println("LA SUPER PRUEBA DEL COMBO");
		System.out.println(msg.getEmailReceiver());

		System.out.println("Entro en el save");
		if (binding.hasErrors()) {
			System.out.println("Entro en el binding messageController");
			System.out.println(binding.getAllErrors().get(0));

			final Collection<MailBox> mailBoxes = sender.getMailBoxes();
			System.out.println(mailBoxes);
			result = new ModelAndView("mailBox/list");
			result.addObject("mailBoxes", mailBoxes);
		} else
			try {
				System.out.println("intenta el list de exchange message");
				this.messageService.save(msg);
				final Collection<MailBox> mailBoxes = sender.getMailBoxes();
				System.out.println(mailBoxes);
				result = new ModelAndView("mailBox/list");
				result.addObject("mailBoxes", mailBoxes);
			} catch (final Throwable oops) {
				System.out.println("Es el oops");
				System.out.println(oops);
				result = this.createEditModelAndView(msg, "message.commit.error");
			}

		return result;
	}
	@RequestMapping(value = "/editBroadcast", method = RequestMethod.GET)
	public ModelAndView editBroadcast(@RequestParam final int messageId) {
		ModelAndView result;
		final Message msg;

		msg = this.messageService.findOne(messageId);
		Assert.notNull(msg);
		result = this.createEditModelAndViewBroadcast(msg);
		return result;
	}

	@RequestMapping(value = "/editBroadcast", method = RequestMethod.POST, params = "save")
	public ModelAndView sendBroadcast(@ModelAttribute("msg") @Valid Message msg, final BindingResult binding, @RequestParam final String emailReceiver) {
		ModelAndView result;
		final UserAccount login = LoginService.getPrincipal();
		final Actor sender = this.actorService.getActorByUserId(login.getId());
		System.out.println("antes de exchangeMessage");
		msg = this.messageService.sendBroadcast(msg);
		System.out.println("despues de exchangeMessage");
		System.out.println(sender);
		System.out.println(msg);
		System.out.println(msg.getMailBoxes());
		System.out.println(this.mailBoxService.getOutBoxActor(sender.getId()).getMessages());

		System.out.println("Entro en el save");
		if (binding.hasErrors()) {
			System.out.println("Entro en el binding messageController");
			System.out.println(binding.getAllErrors().get(0));

			final Collection<MailBox> mailBoxes = sender.getMailBoxes();
			System.out.println(mailBoxes);
			result = new ModelAndView("mailBox/list");
			result.addObject("mailBoxes", mailBoxes);
		} else
			try {
				System.out.println("intenta el list broadcast");
				this.messageService.save(msg);
				final Collection<MailBox> mailBoxes = sender.getMailBoxes();
				System.out.println(mailBoxes);
				result = new ModelAndView("mailBox/list");
				result.addObject("mailBoxes", mailBoxes);
			} catch (final Throwable oops) {
				System.out.println("Es el oops");
				System.out.println(oops);
				result = this.createEditModelAndView(msg, "message.commit.error");
			}

		return result;
	}
	protected ModelAndView createEditModelAndView(final Message msg) {
		ModelAndView result;

		result = this.createEditModelAndView(msg, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Message msg, final String msgCode) {
		ModelAndView result;
		final Collection<String> emails = this.actorService.getEmailofActors();
		System.out.println(emails);
		final List<String> listEmail = new ArrayList<>();
		listEmail.addAll(emails);
		for (int i = 0; i < listEmail.size(); i++) {
			final Actor a = this.actorService.getActorByEmail(listEmail.get(i));
			final MailBox inbox = this.mailBoxService.getInBoxActor(a.getId());
			System.out.println("inbox y actor");
			System.out.println(inbox);
			System.out.println(a);
			if (inbox == null)
				emails.remove(listEmail.get(i));
		}
		result = new ModelAndView("message/edit");
		result.addObject("msg", msg);
		result.addObject("emails", emails);
		result.addObject("msgCode", msgCode);

		return result;
	}
	protected ModelAndView createEditModelAndViewBroadcast(final Message msg) {
		ModelAndView result;

		result = this.createEditModelAndViewBroadcast(msg, null);

		return result;
	}

	protected ModelAndView createEditModelAndViewBroadcast(final Message msg, final String msgCode) {
		ModelAndView result;
		final Collection<String> emails = this.actorService.getEmailofActors();

		result = new ModelAndView("message/editBroadcast");
		result.addObject("msg", msg);
		result.addObject("emails", emails);
		result.addObject("msgCode", msgCode);

		return result;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam("id") final int msgId) {
		ModelAndView result;

		final UserAccount login = LoginService.getPrincipal();
		final Actor sender = this.actorService.getActorByUserId(login.getId());

		final String language = LocaleContextHolder.getLocale().getDisplayLanguage();
		final Message msg = this.messageService.findOne(msgId);
		System.out.println("Message encontrado: " + msg);
		Assert.notNull(msg, "msg.null");

		try {
			final Message m = this.messageService.delete(msg);
			final Collection<MailBox> mailBoxes = sender.getMailBoxes();
			System.out.println(mailBoxes);
			result = new ModelAndView("mailBox/list");
			result.addObject("mailBoxes", mailBoxes);
		} catch (final Exception e) {
			System.out.println(e);
			System.out.println("entro en el catch");
			result = new ModelAndView("message/show");
			result.addObject("msg", msg);
		}

		result.addObject("language", language);
		return result;
	}

	@RequestMapping(value = "/editMailBox", method = RequestMethod.GET)
	public ModelAndView editMailBox(@RequestParam final int msgId) {
		ModelAndView result;
		final Message message;

		message = this.messageService.findOne(msgId);
		System.out.println("Message de editMailBox");
		System.out.println(message);
		Assert.notNull(message);
		result = this.createEditModelAndViewMailBox(message);
		return result;
	}

	@RequestMapping(value = "/editMailBox", method = RequestMethod.POST, params = "save")
	public ModelAndView saveMailBox(@ModelAttribute("msg") @Valid final Message msg, final BindingResult binding) {
		ModelAndView result;
		System.out.println(msg.getMailBoxes());
		System.out.println("Entro en el save");
		final UserAccount login = LoginService.getPrincipal();
		final Actor sender = this.actorService.getActorByUserId(login.getId());

		System.out.println(sender.getName());
		System.out.println(sender.getEmail());

		if (binding.hasErrors()) {
			System.out.println("Entro en el binding messageController");
			System.out.println(binding.getAllErrors().get(0));

			final Collection<MailBox> mailBoxes = sender.getMailBoxes();
			System.out.println(mailBoxes);
			result = new ModelAndView("mailBox/list");
			result.addObject("mailBoxes", mailBoxes);
		} else
			try {
				System.out.println("intenta el list broadcast");
				this.messageService.save(msg);

				final Collection<MailBox> mailBoxes = sender.getMailBoxes();
				System.out.println(mailBoxes);
				result = new ModelAndView("mailBox/list");
				result.addObject("mailBoxes", mailBoxes);
			} catch (final Throwable oops) {
				System.out.println("Es el oops");
				System.out.println(oops);
				result = this.createEditModelAndView(msg, "message.commit.error");
			}

		return result;
	}
	protected ModelAndView createEditModelAndViewMailBox(final Message msg) {
		ModelAndView result;

		result = this.createEditModelAndViewMailBox(msg, null);

		return result;
	}

	protected ModelAndView createEditModelAndViewMailBox(final Message msg, final String msgCode) {
		ModelAndView result;

		final UserAccount logged = LoginService.getPrincipal();
		final Actor a = this.actorService.getActorByUserId(logged.getId());
		final Collection<MailBox> mailBoxes = a.getMailBoxes();
		final Collection<String> nameMailBox = new ArrayList<>();
		for (final MailBox mailbox : mailBoxes)
			nameMailBox.add(mailbox.getName());

		System.out.println("editModelAndView");
		System.out.println(mailBoxes);
		System.out.println(nameMailBox);

		result = new ModelAndView("message/editMailBox");
		result.addObject("msg", msg);
		result.addObject("nameMailBox", nameMailBox);
		result.addObject("msgCode", msgCode);

		return result;
	}

}
