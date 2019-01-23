
package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ComplaintService;
import services.RefereeService;
import services.ReportService;
import services.WelcomeService;
import domain.Complaint;
import domain.Report;

@Controller
@RequestMapping("/report/referee")
public class ReportRefereeController extends AbstractController {

	@Autowired
	private ReportService		reportService;
	@Autowired
	private ComplaintService	complaintService;
	@Autowired
	private RefereeService		refereeService;
	@Autowired
	private WelcomeService		welcomeService;


	// ==============================================================

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam("complaintId") final int complaintId) {

		final ModelAndView res;
		final Report report = this.reportService.create();

		final Complaint complaint = this.complaintService.findOne(complaintId);
		Assert.notNull(complaint);

		report.setComplaint(complaint);
		res = this.createEditModelAndView(report);

		return res;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Report report, final BindingResult binding) {

		ModelAndView res;

		if (binding.hasErrors()) {
			System.out.println(binding.getAllErrors().get(0));
			res = this.createEditModelAndView(report);
		} else
			try {
				this.reportService.save(report);

				//				res = new ModelAndView("complaint/referee/show");
				//
				//				final Complaint complaint;
				//				complaint = report.getComplaint();
				//
				//				res.addObject("complaint", complaint);
				//				final String system = this.welcomeService.getSystem();
				//				res.addObject("system", system);
				//				final String logo = this.welcomeService.getLogo();
				//				res.addObject("logo", logo);
				//				res.addObject("requestURI", "complaint/referee/show.do");

				res = new ModelAndView("workplan/handyWorker/redir");

				res.addObject("urlRedir", "/complaint/referee/show.do?complaintId=");
				res.addObject("id", report.getComplaint().getId());

			} catch (final Throwable oops) {
				System.out.println(oops);
				res = this.createEditModelAndView(report, "report.commit.error");
			}

		return res;
	}

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(@RequestParam("reportId") final int reportId) {

		final ModelAndView res;
		res = new ModelAndView("report/referee/show");

		final Report report;
		report = this.reportService.findOne(reportId);

		res.addObject("report", report);
		final String system = this.welcomeService.getSystem();
		res.addObject("system", system);
		final String logo = this.welcomeService.getLogo();
		res.addObject("logo", logo);
		res.addObject("requestURI", "report/referee/show.do");

		return res;
	}

	// ==============================================================

	protected ModelAndView createEditModelAndView(final Report report) {

		ModelAndView res;

		res = this.createEditModelAndView(report, null);

		return res;
	}

	protected ModelAndView createEditModelAndView(final Report report, final String messageCode) {

		ModelAndView res;

		res = new ModelAndView("report/referee/create");
		res.addObject("report", report);
		final String system = this.welcomeService.getSystem();
		res.addObject("system", system);
		final String logo = this.welcomeService.getLogo();
		res.addObject("logo", logo);
		res.addObject("message", messageCode);

		return res;
	}

}
