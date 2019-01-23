
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
import services.ApplicationService;
import services.FixUpService;
import services.HandyWorkerService;
import services.PhaseService;
import services.WelcomeService;
import domain.FixUp;
import domain.Phase;

@Controller
@RequestMapping("/workplan/handyWorker")
public class PhaseHandyWorkerController extends AbstractController {

	@Autowired
	PhaseService						phaseService;
	@Autowired
	FixUpService						fixUpService;
	@Autowired
	private ApplicationService			applicationService;
	@Autowired
	private HandyWorkerService			handyWorkerService;

	@Autowired
	private WelcomeService				welcomeService;

	ApplicationHandyWorkerController	applicationController	= new ApplicationHandyWorkerController();


	//SHOW
	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(@RequestParam("fixUpId") final int fixUpId) {

		final ModelAndView result;
		final FixUp fixUp = this.fixUpService.findOne(fixUpId);
		final Collection<Phase> workplan = this.phaseService.getPhasesByFixUp(fixUp);

		result = new ModelAndView();
		result.addObject("workplan", workplan);
		final String system = this.welcomeService.getSystem();
		result.addObject("system", system);
		final String logo = this.welcomeService.getLogo();
		result.addObject("logo", logo);
		result.addObject("requestURI", "workplan/handyWorker/show.do");

		return result;

	}
	//CREATE
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam(value = "fixUpId", defaultValue = "-1") final int fixUpId) {

		final ModelAndView result;
		final FixUp fixUp = this.fixUpService.findOne(fixUpId);
		final Phase phase;
		phase = this.phaseService.create();
		phase.setFixUp(fixUp);

		result = this.createEditModelAndView(phase);
		return result;
	}
	//EDIT
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam(value = "phaseId", defaultValue = "-1") final int phaseId) {

		ModelAndView result;
		Phase phase;
		phase = this.phaseService.findOne(phaseId);
		if (LoginService.getPrincipal().getId() != phase.getFixUp().getHandyWorker().getUserAccount().getId())
			result = new ModelAndView("welcome/index");
		else {

			Assert.notNull(phase);
			result = this.createEditModelAndView(phase);
		}

		return result;
	}
	//SAVE
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Phase phase, final BindingResult bindings) {
		System.out.println(phase);
		ModelAndView result;
		if (bindings.hasErrors()) {

			System.out.println(bindings.toString());
			result = this.createEditModelAndView(phase);
		} else
			try {
				this.phaseService.save(phase);
				System.out.println("phase guardada");

				result = new ModelAndView("workplan/handyWorker/redir");

				result.addObject("urlRedir", "/fixUp/handyWorker/show.do?fixUpId=");
				result.addObject("id", phase.getFixUp().getId());

			} catch (final Exception error) {
				System.out.println("hay un error: " + error);
				if (error.getMessage().equals("phase.wrongDate"))
					result = this.createEditModelAndView(phase, "phase.wrongDate");
				else
					result = this.createEditModelAndView(phase, "tutorial.commit.error");
			}
		return result;

	}
	//DELETE
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam(value = "phaseId", defaultValue = "-1") final int phaseId) {

		ModelAndView result;
		final Phase phase = this.phaseService.findOne(phaseId);
		Assert.notNull(phase);
		try {

			this.phaseService.delete(phase);
			System.out.println("phase borrada");

			result = new ModelAndView("workplan/handyWorker/redir");

			result.addObject("urlRedir", "/fixUp/handyWorker/show.do?fixUpId=");
			result.addObject("id", phase.getFixUp().getId());

		} catch (final Throwable error) {
			result = this.createEditModelAndView(phase, "tutorial.commit.error");
		}
		return result;
	}
	private ModelAndView createEditModelAndView(final Phase phase) {
		return this.createEditModelAndView(phase, null);
	}
	private ModelAndView createEditModelAndView(final Phase phase, final String messageCode) {
		ModelAndView result;
		result = new ModelAndView("workplan/handyWorker/edit");
		result.addObject("phase", phase);
		result.addObject("message", messageCode);

		return result;
	}

}
