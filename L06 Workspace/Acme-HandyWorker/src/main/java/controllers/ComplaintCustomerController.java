
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
import domain.Complaint;
import domain.Customer;
import domain.FixUp;
import domain.HandyWorker;
import domain.Referee;

// COMPLAINTS

@Controller
@RequestMapping("/complaint/customer")
public class ComplaintCustomerController extends AbstractController {

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


	// ==============================================================

	protected ModelAndView createEditModelAndView(final Complaint complaint) {

		ModelAndView res;

		res = this.createEditModelAndView(complaint, null);

		return res;
	}

	protected ModelAndView createEditModelAndView(final Complaint complaint, final String messageCode) {

		final ModelAndView res;
		final UserAccount acc = LoginService.getPrincipal();
		final Collection<FixUp> fixUps;
		final Collection<Referee> referees;
		final Customer c = this.customerService.getCustomerByUserAccountId(acc.getId());

		fixUps = this.fixUpService.getFixUpByCustomerId(c.getId());
		referees = this.refereeService.findAll();

		res = new ModelAndView("complaint/customer/edit");
		res.addObject("complaint", complaint);
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

		complaints = this.complaintService.listing();

		res = new ModelAndView("complaint/customer/list");
		res.addObject("complaints", complaints);
		res.addObject("requestURI", "complaint/customer/list.do");

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
		res = new ModelAndView("complaint/customer/show");

		final Complaint complaint;
		complaint = this.complaintService.findOne(complaintId);
		final Collection<FixUp> fixUps;
		fixUps = this.fixUpService.getFixUpByCustomerId(complaint.getFixUp().getCustomer().getId());

		if (complaint.getReferee() != null) {
			final Referee referee;
			referee = this.refereeService.findOne(complaint.getReferee().getId());
			res.addObject("referee", referee);
		}

		final HandyWorker handyWorker;
		handyWorker = this.handyWorkerService.findOne(complaint.getFixUp().getHandyWorker().getId());
		res.addObject("handyWorker", handyWorker);

		res.addObject("complaint", complaint);
		res.addObject("fixUps", fixUps);
		res.addObject("requestURI", "complaint/customer/show.do");

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
