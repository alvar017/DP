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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import services.NoteService;

@Controller
@RequestMapping("/fixUp/customer")
public class NoteHandyWorkerController extends AbstractController {

	@Autowired
	private NoteService					noteService;

	@Autowired
	private services.HandyWorkerService	HandyWorkerService;


	// Constructors -----------------------------------------------------------

	public NoteHandyWorkerController() {
		super();
	}

	//	@RequestMapping(value = "/create", method = RequestMethod.GET)
	//	public ModelAndView create() {
	//		ModelAndView result;
	//
	//		final String language = LocaleContextHolder.getLocale().getDisplayLanguage();
	//
	//		Note note;
	//
	//		final UserAccount userHw = LoginService.getPrincipal();
	//		final HandyWorker handyWorkerLogger = this.HandyWorkerService.getHandyWorkerByUserAccountId(userHw.getId());
	//
	//		note = this.noteService.create();
	//
	//		result = this.createEditModelAndView(note);
	//		result.addObject("language", language);
	//
	//		return result;
	//	}
	//
	//	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	//	public ModelAndView save(@Valid final Note note, final BindingResult binding) {
	//		ModelAndView result;
	//
	//		if (binding.hasErrors()) {
	//			System.out.println("Carmen: Hay un error");
	//			System.out.println("Carmen: El error es este: " + binding);
	//			result = this.createEditModelAndView(note);
	//		} else
	//			try {
	//				System.out.println("Carmen: Voy a intentar guardar");
	//				System.out.println("Carmen: Hay errores " + binding);
	//				this.noteService.save(note);
	//				result = new ModelAndView("redirect:show.do");
	//			} catch (final Throwable oops) {
	//				System.out.println("Carmen: Hay un error:" + oops);
	//				result = this.createEditModelAndView(note, "note.commit.error");
	//			}
	//		return result;
	//	}
	//
	//	private ModelAndView createEditModelAndView(final Note note) {
	//		ModelAndView result;
	//
	//		result = new ModelAndView("note/customer/create");
	//
	//		result.addObject("note", note);
	//
	//		return result;
	//	}
	//
	//	private ModelAndView createEditModelAndView(final Note note, final String messageCode) {
	//		ModelAndView result;
	//
	//		result = new ModelAndView("note/customer/create");
	//
	//		result.addObject("note", note);
	//		result.addObject("message", messageCode);
	//
	//		return result;
	//	}
	//
}