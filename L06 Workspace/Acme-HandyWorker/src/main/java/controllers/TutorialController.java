
package controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.validation.Valid;

import org.apache.commons.lang.math.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.TutorialService;
import domain.Section;
import domain.Sponsorship;
import domain.Tutorial;

@Controller
@RequestMapping("/tutorial/")
public class TutorialController extends AbstractController {

	@Autowired
	private TutorialService	tutservice;


	//LIST
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {

		final ModelAndView result;
		Collection<Tutorial> tutorials;
		tutorials = this.tutservice.findAll();
		System.out.println(tutorials);

		result = new ModelAndView();
		result.addObject("tutorials", tutorials);
		result.addObject("requestURI", "tutorial/list.do");

		return result;

	}
	//SHOW
	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(@RequestParam("tutorialId") final int tutorialId) {

		final ModelAndView result;
		final Tutorial tutorial = this.tutservice.findOne(tutorialId);
		Assert.notNull(tutorial);
		final Collection<Sponsorship> sponsorships;
		sponsorships = tutorial.getSponsorships();
		final List<Sponsorship> listaSponsorships = new ArrayList<>(sponsorships);
		System.out.println(sponsorships);
		System.out.println(listaSponsorships);
		final SortedSet<Section> sections;

		sections = new TreeSet<>(tutorial.getSections());
		result = new ModelAndView();
		result.addObject("tutorial", tutorial);
		result.addObject("sections", sections);
		result.addObject("requestURI", "tutorial/show.do");

		if (!listaSponsorships.isEmpty()) {
			final Sponsorship randomSponsorship = listaSponsorships.get(RandomUtils.nextInt(listaSponsorships.size()));
			result.addObject("randomSponsorship", randomSponsorship);
		}
		return result;

	}
	//CREATE
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {

		final ModelAndView result;
		Tutorial tutorial;
		tutorial = this.tutservice.create();

		result = this.createEditModelAndView(tutorial);
		return result;
	}
	//EDIT
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam("tutorialId") final int tutorialId) {

		ModelAndView result;
		Tutorial tutorial;

		tutorial = this.tutservice.findOne(tutorialId);
		Assert.notNull(tutorial);
		result = this.createEditModelAndView(tutorial);

		return result;
	}
	//SAVE
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Tutorial tutorial, final BindingResult bindings) {

		ModelAndView result;
		if (bindings.hasErrors()) {

			System.out.println(bindings.toString());
			result = this.createEditModelAndView(tutorial);
		} else
			try {
				this.tutservice.save(tutorial);
				System.out.println("tutorial guardado");
				result = new ModelAndView("redirect:list.do");
			} catch (final Throwable error) {
				System.out.println(error.getMessage());
				result = this.createEditModelAndView(tutorial, "tutorial.commit.error");
			}
		return result;

	}
	//DELETE
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam("tutorialId") final int tutorialId) {

		ModelAndView result;
		final Tutorial tutorial = this.tutservice.findOne(tutorialId);
		Assert.notNull(tutorial);
		try {

			this.tutservice.delete(tutorial);
			result = new ModelAndView("redirect:list.do");

		} catch (final Throwable error) {
			result = this.createEditModelAndView(tutorial, "tutorial.commit.error");
		}
		return result;
	}
	private ModelAndView createEditModelAndView(final Tutorial tutorial) {
		return this.createEditModelAndView(tutorial, null);
	}
	private ModelAndView createEditModelAndView(final Tutorial tutorial, final String messageCode) {
		ModelAndView result;
		final Collection<Section> sections = tutorial.getSections();
		result = new ModelAndView("tutorial/handyWorker/edit");
		result.addObject("tutorial", tutorial);
		result.addObject("sections", sections);
		result.addObject("message", messageCode);

		return result;
	}
}
