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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ReportService;
import domain.Note;
import domain.Report;

@Controller
@RequestMapping("/report/handyWorker")
public class ReportHandyWorkerController extends AbstractController {

	@Autowired
	private ReportService	reportService;


	// Constructors -----------------------------------------------------------

	public ReportHandyWorkerController() {
		super();
	}

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(@RequestParam("reportId") final int reportId) {
		ModelAndView result;

		System.out.println("Carmen: Entro en el show");

		final Report report = this.reportService.findOne(reportId);
		final Collection<Note> note = report.getNotes();

		final String language = LocaleContextHolder.getLocale().getDisplayLanguage();

		result = new ModelAndView("report/handyWorker/show");
		result.addObject("note", note);
		result.addObject("report", report);
		result.addObject("language", language);

		result.addObject("requestURI", "report/handyWorker/show.do");

		System.out.println("Carmen: Salgo en el show");

		return result;
	}
}
