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

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.NoteService;
import services.RefereeService;
import services.ReportService;
import domain.Note;
import domain.Report;

@Controller
@RequestMapping("/note/handyWorker")
public class NoteHandyWorkerController extends AbstractController {

	@Autowired
	private NoteService					noteService;

	@Autowired
	private services.HandyWorkerService	HandyWorkerService;

	@Autowired
	private ReportService				reportService;

	@Autowired
	private RefereeService				refereeService;


	// Constructors -----------------------------------------------------------

	public NoteHandyWorkerController() {
		super();
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam("reportId") final int reportId) {
		ModelAndView result;

		final String language = LocaleContextHolder.getLocale().getDisplayLanguage();

		Note note;

		System.out.println("carmen aquí llego");

		note = this.noteService.create();

		final Report report = this.reportService.findOne(reportId);
		note.setReport(report);

		result = this.createEditModelAndView(note);
		result.addObject("language", language);
		result.addObject("note", note);

		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Note note, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			System.out.println("Carmen: Hay un error");
			System.out.println("Carmen: El error es este: " + binding);
			result = this.createEditModelAndView(note);
		} else
			try {
				System.out.println("Carmen: Voy a intentar guardar");

				System.out.println("Carmen: Hay errores " + binding);

				final Note noteSave = this.noteService.save(note);
				System.out.println("carmen llego");

				final Report report = note.getReport();
				final Report reportSave = this.reportService.save(report);

				result = new ModelAndView("report/handyWorker/show");
				result.addObject("report", reportSave);
				result.addObject("note", note);

			} catch (final Throwable oops) {
				System.out.println("Carmen: Hay un error:" + oops);
				result = this.createEditModelAndView(note, "note.commit.error");
			}
		return result;
	}

	private ModelAndView createEditModelAndView(final Note note) {
		ModelAndView result;

		result = new ModelAndView("note/handyWorker/create");

		result.addObject("note", note);

		return result;
	}

	private ModelAndView createEditModelAndView(final Note note, final String messageCode) {
		final ModelAndView result;

		final Report report = this.reportService.findOne(note.getReport().getId());

		result = new ModelAndView("note/handyWorker/create");

		result.addObject("note", note);
		result.addObject("report", report);
		result.addObject("message", messageCode);

		return result;
	}

}
