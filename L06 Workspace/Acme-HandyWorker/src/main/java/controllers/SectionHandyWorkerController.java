
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

import services.FixUpService;
import services.HandyWorkerService;
import services.SectionService;
import services.TutorialService;
import services.WelcomeService;
import domain.Section;
import domain.Tutorial;

@Controller
@RequestMapping("/section/handyWorker")
public class SectionHandyWorkerController extends AbstractController {

	@Autowired
	TutorialService						tutorialService;
	@Autowired
	FixUpService						fixUpService;
	@Autowired
	private SectionService				sectionService;
	@Autowired
	private HandyWorkerService			handyWorkerService;
	@Autowired
	private WelcomeService				welcomeService;

	ApplicationHandyWorkerController	applicationController	= new ApplicationHandyWorkerController();


	//CREATE
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam(value = "tutorialId", defaultValue = "-1") final int tutorialId) {

		final ModelAndView result;
		final Tutorial tutorial = this.tutorialService.findOne(tutorialId);
		final Section section;
		section = this.sectionService.create();
		section.setTutorial(tutorial);
		section.setNumber(tutorial.getSections().size() + 1);

		result = this.createEditModelAndView(section);
		return result;
	}
	//EDIT
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam(value = "sectionId", defaultValue = "-1") final int sectionId) {

		ModelAndView result;
		Section section;

		section = this.sectionService.findOne(sectionId);
		Assert.notNull(section);
		result = this.createEditModelAndView(section);

		return result;
	}
	//SAVE
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Section section, final BindingResult bindings) {
		System.out.println(section);
		ModelAndView result;
		if (bindings.hasErrors()) {

			System.out.println(bindings.toString());
			result = this.createEditModelAndView(section);
		} else
			try {
				this.sectionService.save(section);
				System.out.println("section guardada");

				result = new ModelAndView("workplan/handyWorker/redir");

				result.addObject("urlRedir", "/tutorial/handyWorker/show.do?tutorialId=");
				result.addObject("id", section.getTutorial().getId());

				//======================================

			} catch (final Exception error) {
				System.out.println("hay un error: " + error);
				if (error.getMessage().equals("section.wrongDate"))
					result = this.createEditModelAndView(section, "section.wrongDate");
				else
					result = this.createEditModelAndView(section, "tutorial.commit.error");
			}
		return result;

	}
	//DELETE
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam(value = "sectionId", defaultValue = "-1") final int sectionId) {

		ModelAndView result;
		final Section section = this.sectionService.findOne(sectionId);
		Assert.notNull(section);
		try {

			this.sectionService.delete(section);
			System.out.println("section borrada");

			result = new ModelAndView("workplan/handyWorker/redir");

			result.addObject("urlRedir", "/tutorial/handyWorker/show.do?tutorialId=");
			result.addObject("id", section.getTutorial().getId());

		} catch (final Throwable error) {
			result = this.createEditModelAndView(section, "tutorial.commit.error");
		}
		return result;
	}
	private ModelAndView createEditModelAndView(final Section section) {
		ModelAndView res;

		res = this.createEditModelAndView(section, null);
		final String system = this.welcomeService.getSystem();
		res.addObject("system", system);
		final String logo = this.welcomeService.getLogo();
		res.addObject("logo", logo);
		return res;
	}
	private ModelAndView createEditModelAndView(final Section section, final String messageCode) {
		ModelAndView result;
		result = new ModelAndView("section/handyWorker/edit");
		result.addObject("section", section);
		final String system = this.welcomeService.getSystem();
		result.addObject("system", system);
		final String logo = this.welcomeService.getLogo();
		result.addObject("logo", logo);
		result.addObject("message", messageCode);

		return result;
	}

}
