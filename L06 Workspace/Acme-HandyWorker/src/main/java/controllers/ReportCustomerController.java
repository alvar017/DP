
package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ReportService;
import domain.Report;

@Controller
@RequestMapping("/report/customer")
public class ReportCustomerController extends AbstractController {

	@Autowired
	private ReportService	reportService;


	// ==============================================================

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(@RequestParam("reportId") final int reportId) {

		final ModelAndView res;
		res = new ModelAndView("report/customer/show");

		final Report report;
		report = this.reportService.findOne(reportId);

		res.addObject("report", report);
		res.addObject("requestURI", "report/customer/show.do");

		return res;
	}
}
