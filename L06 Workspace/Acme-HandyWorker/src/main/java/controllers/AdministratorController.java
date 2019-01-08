/*
 * AdministratorController.java
 * 
 * Copyright (C) 2018 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package controllers;

import java.util.Collection;
import java.util.HashSet;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import security.LoginService;
import services.ActorService;
import services.AdministratorService;
import services.ApplicationService;
import services.FinderService;
import services.FixUpService;
import services.WelcomeService;
import domain.Actor;
import domain.Administrator;

@Controller
@RequestMapping("/administrator")
public class AdministratorController extends AbstractController {

	@Autowired
	AdministratorService		administratorService;

	@Autowired
	private ActorService		actorService;

	@Autowired
	private FixUpService		fixUpService;

	@Autowired
	private FinderService		finderService;

	@Autowired
	private ApplicationService	applicationService;

	@Autowired
	private WelcomeService		welcomeService;


	// Constructors -----------------------------------------------------------

	public AdministratorController() {
		super();
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView result;

		Administrator administrator;

		final int idUserAccount = LoginService.getPrincipal().getId();

		administrator = this.administratorService.findByUserAccount(idUserAccount);

		result = new ModelAndView("administrator/edit");

		result.addObject("administrator", administrator);

		return result;
	}

	//BANNER
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView saveEdit(@Valid final Administrator administrator, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			System.out.println("El error pasa por aqu� alvaro (IF de save())");
			System.out.println(binding);
			result = new ModelAndView("administrator/edit");
		} else
			try {
				Assert.notNull(administrator, "administrator.null");
				final String password = administrator.getUserAccount().getPassword();
				final Md5PasswordEncoder encoder = new Md5PasswordEncoder();
				final String hashPassword = encoder.encodePassword(password, null);
				administrator.getUserAccount().setPassword(hashPassword);
				this.administratorService.save(administrator);
				result = new ModelAndView("actor/show");
				result.addObject("actor", administrator);

			} catch (final Throwable oops) {
				System.out.println("El error es en administratorController: ");
				System.out.println(oops);
				System.out.println(binding);
				result = new ModelAndView("administrator/edit");
			}
		return result;
	}

	//CONFIGURATION
	@RequestMapping(value = "/list")
	public ModelAndView list() {
		ModelAndView result;

		//Actores suspendidos
		final Collection<Actor> actor = this.actorService.findActorsSuspicious();
		final Collection<Actor> actorB = this.actorService.findActorsBanned();

		//Logo
		final String logo = this.welcomeService.getLogo();

		//iva
		final Integer iva = this.fixUpService.getIva();

		//Spam words
		final HashSet<String> spamWords = this.fixUpService.listSpamWords();

		System.out.println("Carmen: Esta es la lista de spam words");
		System.out.println(spamWords);

		//Welcome page
		final String ingles = this.welcomeService.getS();
		final String spanish = this.welcomeService.getE();

		//Finder
		final Integer time = this.finderService.getTime();
		final Integer resultF = this.finderService.getResult();

		//System
		final String system = this.welcomeService.getSystem();

		//Phone
		final Integer phone = this.welcomeService.getPhone();

		//Brand
		final HashSet<String> brand = this.applicationService.listBrands();

		final String language = LocaleContextHolder.getLocale().getDisplayLanguage();

		System.out.println("Carmen: Entro en el list");

		System.out.println(actor);

		result = new ModelAndView("administrator/list");
		result.addObject("actor", actor);
		result.addObject("actorB", actorB);

		result.addObject("logo", logo);

		result.addObject("ingles", ingles);
		result.addObject("spanish", spanish);

		result.addObject("iva", iva);

		result.addObject("spamWords", spamWords);

		result.addObject("time", time);
		result.addObject("resultF", resultF);

		result.addObject("system", system);

		result.addObject("phone", phone);

		result.addObject("brand", brand);

		result.addObject("language", language);
		result.addObject("requestURI", "administrator/list.do");

		return result;
	}

	@RequestMapping(value = "/editA", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam("actorId") final int actorId) {
		ModelAndView result;

		System.out.println("Carmen: Voy a unBanActor");

		final String language = LocaleContextHolder.getLocale().getDisplayLanguage();

		Actor actorM;

		final Collection<Actor> actor = this.actorService.findActorsSuspicious();

		//Welcome page
		final String ingles = this.welcomeService.getS();
		final String spanish = this.welcomeService.getE();

		//Spam words
		final HashSet<String> spamWords = this.fixUpService.listSpamWords();
		System.out.println("Carmen: Esta es la lista de spam words");
		System.out.println(spamWords);

		//Finder
		final Integer time = this.finderService.getTime();
		final Integer resultF = this.finderService.getResult();

		//iva
		final Integer iva = this.fixUpService.getIva();

		//System
		final String system = this.welcomeService.getSystem();

		//Phone
		final Integer phone = this.welcomeService.getPhone();

		//Logo
		final String logo = this.welcomeService.getLogo();

		//Brand
		final HashSet<String> brand = this.applicationService.listBrands();

		actorM = this.actorService.findOne(actorId);
		final Collection<Actor> actorB = this.actorService.findActorsBanned();

		Assert.notNull(actorId);

		System.out.println("Carmen: El actor es:" + actor + "se encunetra ban" + actorM.getIsBanned());

		if (actorM.getIsBanned() == false) {
			this.actorService.banActor(actorM);
			actorB.add(actorM);
		} else {
			this.actorService.unBanActor(actorM);
			actorB.remove(actorM);
		}

		System.out.println("Carmen: El actor es:" + actor + "se encunetra ban" + actorM.getIsBanned());

		this.actorService.save(actorM);

		result = new ModelAndView("administrator/list");
		result.addObject("language", language);
		result.addObject("spamWords", spamWords);
		result.addObject("ingles", ingles);
		result.addObject("spanish", spanish);
		result.addObject("iva", iva);
		result.addObject("actor", actor);
		result.addObject("actorB", actorB);
		result.addObject("time", time);
		result.addObject("resultF", resultF);
		result.addObject("system", system);
		result.addObject("phone", phone);
		result.addObject("logo", logo);
		result.addObject("brand", brand);

		return result;
	}

	@RequestMapping(value = "/newSpamWord", method = RequestMethod.GET)
	public ModelAndView newSpamWord(@RequestParam("newSpamWord") final String newSpamWord) {
		ModelAndView result;

		System.out.println("Carmen: Voy a intentar guardar");
		HashSet<String> spamWords = this.fixUpService.listSpamWords();
		spamWords = this.fixUpService.newSpamWords(newSpamWord);
		System.out.println("Carmen llego");
		System.out.println("Carmen: Ya hemos guardado:" + spamWords);
		result = new ModelAndView("redirect:list.do");

		return result;
	}

	@RequestMapping(value = "/newIVA", method = RequestMethod.GET)
	public ModelAndView newIVA(@RequestParam("newIVA") final Integer newIVA) {
		ModelAndView result;

		System.out.println(newIVA);
		System.out.println("Carmen: Voy a intentar guardar");
		final Integer iva = this.fixUpService.newIva(newIVA);
		this.applicationService.newIva(newIVA);
		System.out.println(iva);
		result = new ModelAndView("redirect:list.do");

		return result;
	}

	@RequestMapping(value = "/newWelcome", method = RequestMethod.GET)
	public ModelAndView newWelcome(@RequestParam("newIngles") final String newIngles, @RequestParam("newSpanish") final String newSpanish) {
		ModelAndView result;

		System.out.println(newIngles);
		System.out.println(newSpanish);

		System.out.println("Carmen: Voy a intentar guardar");

		final String ingles = this.welcomeService.newE(newIngles);
		System.out.println(ingles);

		final String spanish = this.welcomeService.newS(newSpanish);
		System.out.println(spanish);

		result = new ModelAndView("redirect:list.do");

		return result;
	}

	@RequestMapping(value = "/newSystem", method = RequestMethod.GET)
	public ModelAndView newSystem(@RequestParam("newSystem") final String newSystem) {
		ModelAndView result;

		System.out.println(newSystem);

		System.out.println("Carmen: Voy a intentar guardar");

		final String system = this.welcomeService.newSystem(newSystem);
		System.out.println(system);

		result = new ModelAndView("redirect:list.do");

		return result;
	}

	@RequestMapping(value = "/header", method = RequestMethod.GET)
	public ModelAndView header() {
		ModelAndView result;

		final String system = this.welcomeService.getSystem();

		result = new ModelAndView("master-page/header");

		result.addObject("requestURI", "master-page/header.do");
		result.addObject("system", system);

		return result;
	}

	@RequestMapping(value = "/newResult", method = RequestMethod.GET)
	public ModelAndView newResult(@RequestParam("newResult") final Integer newResult) {
		ModelAndView result;

		final Integer resultF = this.finderService.numResult(newResult);

		System.out.println(resultF);

		System.out.println("Carmen: Voy a intentar guardar");

		result = new ModelAndView("redirect:list.do");

		return result;
	}

	@RequestMapping(value = "/newTime", method = RequestMethod.GET)
	public ModelAndView newTime(@RequestParam("newTime") final Integer newTime) {
		ModelAndView result;

		final Integer time = this.finderService.newTime(newTime);

		System.out.println(time);

		System.out.println("Carmen: Voy a intentar guardar");

		result = new ModelAndView("redirect:list.do");

		return result;
	}

	@RequestMapping(value = "/newLogo", method = RequestMethod.GET)
	public ModelAndView newLogo(@RequestParam("newLogo") final String newLogo) {
		ModelAndView result;

		final String logo = this.welcomeService.newLogo(newLogo);

		System.out.println("Carmen: Voy a intentar guardar");

		result = new ModelAndView("redirect:list.do");

		return result;
	}

	@RequestMapping(value = "/newBrand", method = RequestMethod.GET)
	public ModelAndView newBrand(@RequestParam("newBrand") final String newBrand) {
		ModelAndView result;

		final HashSet<String> brand = this.applicationService.newBrand(newBrand);

		System.out.println("Carmen: Voy a intentar guardar");

		result = new ModelAndView("redirect:list.do");

		return result;
	}

	@RequestMapping(value = "/newPhone", method = RequestMethod.GET)
	public ModelAndView newPhone(@RequestParam("newPhone") final Integer newPhone) {
		ModelAndView result;

		final Integer phone = this.welcomeService.newPhone(newPhone);

		System.out.println("Carmen: Voy a intentar guardar");

		result = new ModelAndView("redirect:list.do");

		return result;
	}

}
