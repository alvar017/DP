
package controllers;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import security.LoginService;
import security.UserAccount;
import services.ComplaintService;
import services.FixUpService;
import services.RefereeService;
import services.WelcomeService;
import domain.Complaint;
import domain.FixUp;
import domain.Referee;

// COMPLAINTS

@Controller
@RequestMapping("/complaint/referee")
public class ComplaintRefereeController extends AbstractController {

	@Autowired
	private ComplaintService	complaintService;
	@Autowired
	private RefereeService		refereeService;
	@Autowired
	private FixUpService		fixUpService;
	@Autowired
	private WelcomeService		welcomeService;


	// ==============================================================

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {

		ModelAndView res;
		Collection<Complaint> complaints;
		Collection<Complaint> myComplaints;

		final UserAccount acc = LoginService.getPrincipal();
		final Referee ref = this.refereeService.findByUserAccountId(acc.getId());

		complaints = this.complaintService.getComplaintWithoutReferee();
		myComplaints = this.complaintService.getComplaintByReferee(ref.getId());

		res = new ModelAndView("complaint/referee/list");
		final String system = this.welcomeService.getSystem();
		res.addObject("system", system);
		final String logo = this.welcomeService.getLogo();
		res.addObject("logo", logo);
		res.addObject("complaints", complaints);
		res.addObject("myComplaints", myComplaints);
		res.addObject("requestURI", "complaint/referee/list.do");

		return res;
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView add(@RequestParam("complaintId") final int complaintId) {

		ModelAndView res;
		Complaint complaint;

		complaint = this.complaintService.findOne(complaintId);
		Assert.notNull(complaint);

		System.out.println("He pasado por la parte de ADD del controlador");
		System.out.println("Complaint: " + complaint.getId());

		res = this.addpost(complaint);

		return res;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView addpost(@Valid final Complaint complaint) {

		System.out.println("He pasado por la parte de ADDPOST del controlador");

		ModelAndView res;

		final UserAccount acc = LoginService.getPrincipal();
		final Referee ref = this.refereeService.findByUserAccountId(acc.getId());

		System.out.println("ref antes: " + complaint.getReferee());
		complaint.setReferee(ref);
		System.out.println("ref despues: " + complaint.getReferee());

		this.complaintService.save(complaint);
		res = new ModelAndView("redirect:list.do");

		return res;
	}

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(@RequestParam("complaintId") final int complaintId) {

		final ModelAndView res;
		res = new ModelAndView("complaint/referee/show");

		final Complaint complaint;
		complaint = this.complaintService.findOne(complaintId);
		final Collection<FixUp> fixUps;
		fixUps = this.fixUpService.getFixUpByCustomerId(complaint.getFixUp().getCustomer().getId());

		if (complaint.getReferee() != null) {
			final Referee referee;
			referee = this.refereeService.findOne(complaint.getReferee().getId());
			res.addObject("referee", referee);
		}

		final String system = this.welcomeService.getSystem();
		res.addObject("system", system);
		final String logo = this.welcomeService.getLogo();
		res.addObject("logo", logo);
		res.addObject("fixUps", fixUps);
		res.addObject("requestURI", "complaint/customer/show.do");

		return res;
	}

	// ==============================================================

}
