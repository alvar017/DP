
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

import services.NoteService;
import services.ReportService;
import services.WelcomeService;
import domain.Customer;
import domain.HandyWorker;
import domain.Note;
import domain.Report;

// NOTES

@Controller
@RequestMapping("/note/referee")
public class NoteRefereeController extends AbstractController {

	@Autowired
	private NoteService		noteService;
	@Autowired
	private ReportService	reportService;
	@Autowired
	private WelcomeService	welcomeService;


	// ==============================================================

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam final int id) {

		final ModelAndView res;

		Note note;
		note = this.noteService.create();

		final Report report = this.reportService.findOne(id);
		Assert.notNull(report);

		final HandyWorker hw = report.getComplaint().getFixUp().getHandyWorker();

		final Customer customer = report.getComplaint().getFixUp().getCustomer();
		note.setHandyWorker(hw);
		note.setReport(report);
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

				res.addObject("urlRedir", "/report/referee/show.do?reportId=");
				res.addObject("id", note.getReport().getId());

				res.addObject("requestURI", "report/referee/show.do");

				return res;

			} catch (final Throwable oops) {
				System.out.println("He pasado por el catch");
				System.out.println(oops);
				res = this.createEditModelAndView(note, "note.commit.error");
			}

		return res;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int id) {

		final ModelAndView res;

		Note note;
		note = this.noteService.findOne(id);

		res = this.createEditModelAndView(note);

		return res;
	}

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(@RequestParam("noteId") final int noteId) {

		final ModelAndView res;
		res = new ModelAndView("note/referee/show");

		final Note note;
		note = this.noteService.findOne(noteId);

		res.addObject("note", note);
		final String system = this.welcomeService.getSystem();
		res.addObject("system", system);
		final String logo = this.welcomeService.getLogo();
		res.addObject("logo", logo);
		res.addObject("requestURI", "note/referee/show.do");

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

		res = new ModelAndView("note/referee/create");
		res.addObject("note", note);
		final String system = this.welcomeService.getSystem();
		res.addObject("system", system);
		final String logo = this.welcomeService.getLogo();
		res.addObject("logo", logo);
		res.addObject("message", messageCode);

		return res;
	}

}
