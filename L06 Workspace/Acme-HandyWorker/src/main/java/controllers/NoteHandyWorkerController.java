
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

import security.LoginService;
import security.UserAccount;
import services.HandyWorkerService;
import services.NoteService;
import services.ReportService;
import services.WelcomeService;
import domain.Customer;
import domain.HandyWorker;
import domain.Note;
import domain.Report;

// NOTES

@Controller
@RequestMapping("/note/handyWorker")
public class NoteHandyWorkerController extends AbstractController {

	@Autowired
	private NoteService			noteService;
	@Autowired
	private HandyWorkerService	handyWorkerService;
	@Autowired
	private ReportService		reportService;
	@Autowired
	private WelcomeService		welcomeService;


	// ==============================================================

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam final int id) {

		final ModelAndView res;

		final UserAccount acc = LoginService.getPrincipal();

		Note note;
		note = this.noteService.create();

		final Report report = this.reportService.findOne(id);
		Assert.notNull(report);

		final Customer customer = report.getComplaint().getFixUp().getCustomer();

		final HandyWorker hw = this.handyWorkerService.findByUserAccountId(acc.getId());
		note.setReport(report);
		note.setHandyWorker(hw);
		note.setCustomer(customer);
		res = this.createEditModelAndView(note);

		return res;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Note note, final BindingResult binding) {

		ModelAndView res;

		if (binding.hasErrors()) {
			System.out.println(binding.getAllErrors().get(0));
			res = this.createEditModelAndView(note);
		} else
			try {
				this.noteService.save(note);

				res = new ModelAndView("workplan/handyWorker/redir");

				res.addObject("urlRedir", "/report/handyWorker/show.do?reportId=");
				res.addObject("id", note.getReport().getId());

				res.addObject("requestURI", "report/handyWorker/show.do");

				return res;

			} catch (final Throwable oops) {
				System.out.println(oops);
				res = this.createEditModelAndView(note, "note.commit.error");
			}

		return res;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int noteId) {

		final ModelAndView res;

		Note note;
		note = this.noteService.findOne(noteId);

		res = this.createEditModelAndView(note);

		return res;
	}

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(@RequestParam("noteId") final int noteId) {

		final ModelAndView res;
		res = new ModelAndView("note/handyWorker/show");

		final Note note;
		note = this.noteService.findOne(noteId);

		res.addObject("note", note);
		final String system = this.welcomeService.getSystem();
		res.addObject("system", system);
		final String logo = this.welcomeService.getLogo();
		res.addObject("logo", logo);

		res.addObject("requestURI", "note/handyWorker/show.do");

		return res;
	}

	// ==============================================================

	protected ModelAndView createEditModelAndView(final Note note) {

		ModelAndView res;

		res = this.createEditModelAndView(note, null);
		final String system = this.welcomeService.getSystem();
		res.addObject("system", system);
		final String logo = this.welcomeService.getLogo();
		res.addObject("logo", logo);

		return res;
	}

	protected ModelAndView createEditModelAndView(final Note note, final String messageCode) {

		final ModelAndView res;
		final UserAccount acc = LoginService.getPrincipal();
		final HandyWorker hw = this.handyWorkerService.findByUserAccountId(acc.getId());
		res = new ModelAndView("note/handyWorker/create");
		res.addObject("note", note);
		final String system = this.welcomeService.getSystem();
		res.addObject("system", system);
		final String logo = this.welcomeService.getLogo();
		res.addObject("logo", logo);
		res.addObject("message", messageCode);

		return res;
	}

}
