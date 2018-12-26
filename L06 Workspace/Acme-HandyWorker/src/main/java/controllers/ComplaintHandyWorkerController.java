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

import services.ComplaintService;
import domain.Complaint;
import domain.FixUp;
import domain.Referee;
import domain.Report;

@Controller
@RequestMapping("/complaint/handyWorker")
public class ComplaintHandyWorkerController extends AbstractController {

	@Autowired
	private ComplaintService	complaintService;


	// Constructors -----------------------------------------------------------

	public ComplaintHandyWorkerController() {
		super();
	}

	@RequestMapping(value = "/list")
	public ModelAndView list() {
		ModelAndView result;
		final Collection<Complaint> complaint = this.complaintService.getAllComplaintsByHandyWorker();
		final String language = LocaleContextHolder.getLocale().getDisplayLanguage();

		System.out.println("Carmen: Entro en el list");

		result = new ModelAndView("complaint/handyWorker/list");
		result.addObject("complaint", complaint);
		result.addObject("language", language);
		result.addObject("requestURI", "complaint/handyWorker/list.do");

		System.out.println("Carmen: Salgo en el list");

		return result;
	}

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(@RequestParam("complaintId") final int complaintId) {
		ModelAndView result;

		System.out.println("Carmen: Entro en el show");

		final Complaint complaint = this.complaintService.findOne(complaintId);
		final FixUp fixUp = complaint.getFixUp();

		System.out.println("Carmen: El fixUp de la complaint es:" + fixUp);

		final Referee referee = complaint.getReferee();
		final Collection<Report> reports = complaint.getReports();

		final String language = LocaleContextHolder.getLocale().getDisplayLanguage();

		result = new ModelAndView("complaint/handyWorker/show");
		result.addObject("reports", reports);
		result.addObject("complaint", complaint);
		result.addObject("referee", referee);
		result.addObject("language", language);
		result.addObject("fixUp", fixUp);

		result.addObject("requestURI", "complaint/handyWorker/show.do");

		System.out.println("Carmen: Salgo en el show");

		return result;
	}
}