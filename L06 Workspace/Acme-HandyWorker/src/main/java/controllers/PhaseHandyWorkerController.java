
package controllers;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import security.LoginService;
import security.UserAccount;
import services.ApplicationService;
import services.FixUpService;
import services.HandyWorkerService;
import services.PhaseService;
import domain.Category;
import domain.FixUp;
import domain.HandyWorker;
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

	HandyWorkerApplicationController	applicationController	= new HandyWorkerApplicationController();


	//SHOW
	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(@RequestParam("fixUpId") final int fixUpId) {

		final ModelAndView result;
		final FixUp fixUp = this.fixUpService.findOne(fixUpId);
		final Collection<Phase> workplan = this.phaseService.getPhasesByFixUp(fixUp);

		result = new ModelAndView();
		result.addObject("workplan", workplan);
		result.addObject("requestURI", "workplan/handyWorker/show.do");

		return result;

	}
	//CREATE
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam("fixUpId") final int fixUpId) {

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
	public ModelAndView edit(@RequestParam("phaseId") final int phaseId) {

		ModelAndView result;
		Phase phase;

		phase = this.phaseService.findOne(phaseId);
		Assert.notNull(phase);
		result = this.createEditModelAndView(phase);

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
				final UserAccount login = LoginService.getPrincipal();
				//SE PODRÍA LLAMAR AL MÉTODO DE OTRO CONTROLADOR PARA CREAR LA VISTA????
				final HandyWorker logged = this.handyWorkerService.getHandyWorkerByUserAccountId(login.getId());
				final FixUp fixUp = this.fixUpService.findOne(phase.getFixUp().getId());
				final boolean checkHW = logged.getId() == fixUp.getHandyWorker().getId();
				System.out.println("checkHW: " + checkHW);
				//======================================
				final Category category = fixUp.getCategory();
				final String language = LocaleContextHolder.getLocale().getDisplayLanguage();
				final Collection<Phase> workplan = this.phaseService.getPhasesByFixUp(fixUp);

				result = new ModelAndView("fixUp/handyWorker/show");
				result.addObject("fixUp", fixUp);
				result.addObject("category", category);
				result.addObject("language", language);
				//NUEVO
				result.addObject("workplan", workplan);
				result.addObject("checkHW", checkHW);
				//========================================
				result.addObject("requestURI", "fixUp/handyWorker/show.do?fixUpId=" + fixUp.getId());

			} catch (final Exception error) {
				System.out.println("hay un error: " + error);
				error.printStackTrace();
				result = this.createEditModelAndView(phase, "tutorial.commit.error");
			}
		return result;

	}
	//DELETE
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam("phaseId") final int phaseId) {

		ModelAndView result;
		final Phase phase = this.phaseService.findOne(phaseId);
		Assert.notNull(phase);
		try {

			this.phaseService.delete(phase);
			final UserAccount login = LoginService.getPrincipal();
			//SE PODRÍA LLAMAR AL MÉTODO DE OTRO CONTROLADOR PARA CREAR LA VISTA????
			final HandyWorker logged = this.handyWorkerService.getHandyWorkerByUserAccountId(login.getId());
			final FixUp fixUp = this.fixUpService.findOne(phase.getFixUp().getId());
			final boolean checkHW = logged.getId() == fixUp.getHandyWorker().getId();
			System.out.println("checkHW: " + checkHW);
			//======================================
			final Category category = fixUp.getCategory();
			final String language = LocaleContextHolder.getLocale().getDisplayLanguage();
			final Collection<Phase> workplan = this.phaseService.getPhasesByFixUp(fixUp);

			result = new ModelAndView("fixUp/handyWorker/show");
			result.addObject("fixUp", fixUp);
			result.addObject("category", category);
			result.addObject("language", language);
			//NUEVO
			result.addObject("workplan", workplan);
			result.addObject("checkHW", checkHW);
			//========================================
			result.addObject("requestURI", "fixUp/handyWorker/show.do?fixUpId=" + fixUp.getId());

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
