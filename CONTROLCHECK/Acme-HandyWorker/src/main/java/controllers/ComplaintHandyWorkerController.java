
package controllers;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import security.LoginService;
import security.UserAccount;
import services.ComplaintService;
import services.CustomerService;
import services.FixUpService;
import services.HandyWorkerService;
import services.RefereeService;
import services.WelcomeService;
import domain.Complaint;
import domain.Customer;
import domain.FixUp;
import domain.HandyWorker;
import domain.Referee;

// COMPLAINTS

@Controller
@RequestMapping("/complaint/handyWorker")
public class ComplaintHandyWorkerController extends AbstractController {

	@Autowired
	private ComplaintService	complaintService;
	@Autowired
	private FixUpService		fixUpService;
	@Autowired
	private RefereeService		refereeService;
	@Autowired
	private CustomerService		customerService;
	@Autowired
	private HandyWorkerService	handyWorkerService;
	@Autowired
	private WelcomeService		welcomeService;


	// ==============================================================

	protected ModelAndView createEditModelAndView(final Complaint complaint) {

		ModelAndView res;

		res = this.createEditModelAndView(complaint, null);
		final String system = this.welcomeService.getSystem();
		res.addObject("system", system);
		final String logo = this.welcomeService.getLogo();
		res.addObject("logo", logo);

		return res;
	}

	protected ModelAndView createEditModelAndView(final Complaint complaint, final String messageCode) {

		final ModelAndView res;
		final UserAccount acc = LoginService.getPrincipal();
		final Collection<FixUp> fixUps;
		final Collection<Referee> referees;
		final HandyWorker hw = this.handyWorkerService.getHandyWorkerByUserAccountId(acc.getId());

		fixUps = this.fixUpService.getFixUpByCustomerId(hw.getId());
		referees = this.refereeService.findAll();

		final String system = this.welcomeService.getSystem();

		res = new ModelAndView("complaint/handyWorker/edit");
		res.addObject("complaint", complaint);
		res.addObject("system", system);
		final String logo = this.welcomeService.getLogo();
		res.addObject("logo", logo);
		res.addObject("fixUps", fixUps);
		res.addObject("referees", referees);
		res.addObject("message", messageCode);

		return res;
	}

	// ==============================================================

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {

		ModelAndView res;
		Collection<Complaint> complaints;

		complaints = this.complaintService.getAllComplaintsByHandyWorker();

		res = new ModelAndView("complaint/handyWorker/list");
		final String system = this.welcomeService.getSystem();
		res.addObject("system", system);
		final String logo = this.welcomeService.getLogo();
		res.addObject("logo", logo);

		res.addObject("complaints", complaints);
		res.addObject("requestURI", "complaint/handyWorker/list.do");

		return res;
	}
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {

		ModelAndView res;
		Complaint complaint;

		complaint = this.complaintService.create();
		res = this.createEditModelAndView(complaint);

		return res;
	}

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(@RequestParam("complaintId") final int complaintId) {

		final ModelAndView res;
		res = new ModelAndView("complaint/handyWorker/show");

		final Complaint complaint;
		complaint = this.complaintService.findOne(complaintId);
		final Collection<FixUp> fixUps;
		fixUps = this.fixUpService.getFixUpByCustomerId(complaint.getFixUp().getCustomer().getId());

		if (complaint.getReferee() != null) {
			final Referee referee;
			referee = this.refereeService.findOne(complaint.getReferee().getId());
			res.addObject("referee", referee);
		}

		final Customer customer = this.customerService.findOne(complaint.getFixUp().getCustomer().getId());
		res.addObject("customer", customer);

		res.addObject("complaint", complaint);
		final String system = this.welcomeService.getSystem();
		res.addObject("system", system);
		final String logo = this.welcomeService.getLogo();
		res.addObject("logo", logo);

		res.addObject("fixUps", fixUps);
		res.addObject("requestURI", "complaint/handyWorker/show.do");

		return res;
	}
	@RequestMapping(value = "/show", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@Valid final Complaint complaint, final BindingResult binding) {

		ModelAndView res;

		try {
			this.complaintService.delete(complaint);
			res = new ModelAndView("redirect:list.do");
		} catch (final Throwable oops) {
			res = this.createEditModelAndView(complaint, "complaint.commit.error");
		}

		return res;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam("complaintId") final int complaintId) {

		ModelAndView res;
		Complaint complaint;

		complaint = this.complaintService.findOne(complaintId);
		Assert.notNull(complaint);
		res = this.createEditModelAndView(complaint);

		return res;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Complaint complaint, final BindingResult binding) {

		ModelAndView res;

		if (binding.hasErrors()) {
			res = this.createEditModelAndView(complaint);
			System.out.println(binding);
		} else
			try {
				this.complaintService.save(complaint);
				res = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				System.out.println("El error: " + oops);
				res = this.createEditModelAndView(complaint, "complaint.commit.error");

			}

		return res;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam("complaintId") final int complaintId) {

		ModelAndView res;
		final Complaint complaint = this.complaintService.findOne(complaintId);
		Assert.notNull(complaint);

		try {
			this.complaintService.delete(complaint);
			res = new ModelAndView("redirect:list.do");
		} catch (final Exception e) {
			res = this.createEditModelAndView(complaint, "complaint.commit.error");
		}

		return res;
	}
	// ==============================================================

}
