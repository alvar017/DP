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

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import security.LoginService;
import security.UserAccountRepository;
import services.ActorService;
import services.AdministratorService;
import services.ApplicationService;
import services.CustomerService;
import services.FinderService;
import services.FixUpService;
import services.HandyWorkerService;
import services.QuoletService;
import services.WelcomeService;
import domain.Actor;
import domain.Administrator;
import domain.Customer;
import domain.HandyWorker;

@Controller
@RequestMapping("/administrator")
public class AdministratorController extends AbstractController {

	//CONTROLCHECK
	@Autowired
	QuoletService					quoletService;
	//CONTROLCHECK

	@Autowired
	HandyWorkerService				handyWorkerService;

	@Autowired
	AdministratorService			administratorService;

	@Autowired
	private ActorService			actorService;

	@Autowired
	private FixUpService			fixUpService;

	@Autowired
	private FinderService			finderService;

	@Autowired
	private ApplicationService		applicationService;

	@Autowired
	private WelcomeService			welcomeService;

	@Autowired
	private CustomerService			customerService;

	@Autowired
	private UserAccountRepository	userAccountService;


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
		final String system = this.welcomeService.getSystem();
		result.addObject("system", system);
		final String logo = this.welcomeService.getLogo();
		result.addObject("logo", logo);

		return result;
	}

	//BANNER
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView saveEdit(@Valid final Administrator administrator, final BindingResult binding) {
		ModelAndView result;
		if (administrator.getEmail() != null
			&& this.actorService.getActorByEmail(administrator.getEmail()) != null
			&& this.administratorService.findByUserAccount(LoginService.getPrincipal().getId()).getId() != this.actorService.getActorByEmail(administrator.getEmail()).getId()
			|| !(administrator.getEmail().matches("[\\w\\.\\w]{1,}(@)") || administrator.getEmail().matches("[\\w\\s\\w]{1,}(<)[\\w\\.\\w]{1,}(@)(>)") || administrator.getEmail().matches("[\\w\\.\\w]{1,}(@)[\\w]{1,}\\.[\\w]{1,}") || administrator
				.getEmail().matches("[\\w\\s\\w]{1,}(<)[\\w\\.\\w]{1,}(@)[\\w]{1,}\\.[\\w]{1,}(>)"))) {
			final ObjectError error = new ObjectError("actor.email", "An account already exists for this email.");
			binding.addError(error);
			binding.rejectValue("email", "error.actor.email.exits");
		}
		if (administrator.getUserAccount().getUsername().length() < 5 || administrator.getUserAccount().getUsername().length() > 32) {
			final ObjectError error = new ObjectError("userAccount.username", "An account already exists for this email.");
			binding.addError(error);
			binding.rejectValue("userAccount.username", "error.userAccount.username");
		}
		if (!this.userAccountService.findByUsername(administrator.getUserAccount().getUsername()).equals(administrator.getUserAccount())) {
			final ObjectError error = new ObjectError("userAccount.username", "An account already exists for this email.");
			binding.addError(error);
			binding.rejectValue("userAccount.username", "error.userAccount.username.exits");
		}

		if (binding.hasErrors()) {
			System.out.println("El error pasa por aqu� alvaro (IF de save())");
			System.out.println(binding);
			result = new ModelAndView("administrator/edit");
		} else
			try {
				Assert.notNull(administrator, "administrator.null");
				//				final String password = administrator.getUserAccount().getPassword();
				//				final Md5PasswordEncoder encoder = new Md5PasswordEncoder();
				//				final String hashPassword = encoder.encodePassword(password, null);
				//				administrator.getUserAccount().setPassword(hashPassword);
				if (administrator.getPhone().matches("^([0-9]{4,})$"))
					administrator.setPhone("+" + this.welcomeService.getPhone() + " " + administrator.getPhone());
				this.administratorService.save(administrator);
				result = new ModelAndView("actor/show");
				result.addObject("actor", administrator);
				final String system = this.welcomeService.getSystem();
				result.addObject("system", system);
				final String logo = this.welcomeService.getLogo();
				result.addObject("logo", logo);
				SimpleDateFormat formatter;
				String moment;
				formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
				moment = formatter.format(new Date());
				result.addObject("moment", moment);

			} catch (final Throwable oops) {
				System.out.println("El error es en administratorController: ");
				System.out.println(oops);
				System.out.println(binding);
				result = new ModelAndView("administrator/edit");
				final String system = this.welcomeService.getSystem();
				result.addObject("system", system);
				final String logo = this.welcomeService.getLogo();
				result.addObject("logo", logo);
				SimpleDateFormat formatter;
				String moment;
				formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
				moment = formatter.format(new Date());
				result.addObject("moment", moment);
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
		final String phone = this.welcomeService.getPhone();

		//Country�s Phone
		final String phoneCountry = this.welcomeService.getCountry();

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
		//Score Words
		final HashSet<String> scoreWordsPositives = this.administratorService.listScoreWordsPositivas();
		final HashSet<String> scoreWordsNegatives = this.administratorService.listScoreWordsNegativas();
		result.addObject("scoreWordsPos", scoreWordsPositives);
		result.addObject("scoreWordsNeg", scoreWordsNegatives);

		result.addObject("time", time);
		result.addObject("resultF", resultF);

		result.addObject("system", system);

		result.addObject("phone", phone);
		result.addObject("phoneCountry", phoneCountry);

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
		final String phone = this.welcomeService.getPhone();

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
		final HashSet<String> scoreWordsPositives = this.administratorService.listScoreWordsPositivas();
		final HashSet<String> scoreWordsNegatives = this.administratorService.listScoreWordsNegativas();
		result.addObject("scoreWordsPos", scoreWordsPositives);
		result.addObject("scoreWordsNeg", scoreWordsNegatives);
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

		try {
			System.out.println(newIVA);
			System.out.println("Carmen: Voy a intentar guardar");
			final Integer iva = this.fixUpService.newIva(newIVA);
			this.applicationService.newIva(newIVA);
			System.out.println(iva);
			result = new ModelAndView("redirect:list.do");
		} catch (final Exception e) {
			result = this.createEditModelAndView(newIVA, "iva.bad");
		}

		return result;
	}

	private ModelAndView createEditModelAndView(final Integer newIVA, final String messageCode) {
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
		final String phone = this.welcomeService.getPhone();

		//Brand
		final HashSet<String> brand = this.applicationService.listBrands();

		final String language = LocaleContextHolder.getLocale().getDisplayLanguage();

		System.out.println("Carmen: Entro en el list");

		System.out.println(actor);

		result = new ModelAndView("administrator/list");
		result.addObject("message", messageCode);

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
		final HashSet<String> scoreWordsPositives = this.administratorService.listScoreWordsPositivas();
		final HashSet<String> scoreWordsNegatives = this.administratorService.listScoreWordsNegativas();
		result.addObject("scoreWordsPos", scoreWordsPositives);
		result.addObject("scoreWordsNeg", scoreWordsNegatives);
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
		try {
			final Integer resultF = this.finderService.numResult(newResult);

			System.out.println(resultF);

			System.out.println("Carmen: Voy a intentar guardar");

			result = new ModelAndView("redirect:list.do");

		} catch (final Exception e) {
			result = this.createEditModelAndView(newResult, "resutl.bad");
		}
		return result;
	}

	@RequestMapping(value = "/newTime", method = RequestMethod.GET)
	public ModelAndView newTime(@RequestParam("newTime") final Integer newTime) {
		ModelAndView result;
		try {
			final Integer time = this.finderService.newTime(newTime);

			System.out.println(time);

			System.out.println("Carmen: Voy a intentar guardar");

			result = new ModelAndView("redirect:list.do");

		} catch (final Exception e) {
			result = this.createEditModelAndView(newTime, "time.bad");
		}

		return result;
	}

	@RequestMapping(value = "/newLogo", method = RequestMethod.GET)
	public ModelAndView newLogo(@RequestParam("newLogo") final String newLogo) {
		ModelAndView result;
		try {
			final String logo = this.welcomeService.newLogo(newLogo);

			System.out.println("Carmen: Voy a intentar guardar");

			result = new ModelAndView("redirect:list.do");

		} catch (final Exception e) {
			result = this.createEditModelAndView(newLogo, "logo.bad");
		}

		return result;
	}

	private ModelAndView createEditModelAndView(final String newLogo, final String messageCode) {
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
		final String phone = this.welcomeService.getPhone();

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

		result.addObject("message", messageCode);
		final HashSet<String> scoreWordsPositives = this.administratorService.listScoreWordsPositivas();
		final HashSet<String> scoreWordsNegatives = this.administratorService.listScoreWordsNegativas();
		result.addObject("scoreWordsPos", scoreWordsPositives);
		result.addObject("scoreWordsNeg", scoreWordsNegatives);
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
	public ModelAndView newPhone(@RequestParam("newPhone") final String newPhone) {
		ModelAndView result;

		final String phone = this.welcomeService.newPhone(newPhone);

		System.out.println("Carmen: Voy a intentar guardar");

		result = new ModelAndView("redirect:list.do");

		return result;
	}

	@RequestMapping(value = "/newPhoneCountry", method = RequestMethod.GET)
	public ModelAndView newPhoneCountry(@RequestParam("newPhoneCountry") final String newPhoneCountry) {
		ModelAndView result;

		this.welcomeService.newCountry(newPhoneCountry);
		result = new ModelAndView("redirect:list.do");

		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;

		Administrator administrator;

		administrator = this.administratorService.create();

		result = new ModelAndView("administrator/create");
		final String system = this.welcomeService.getSystem();
		result.addObject("system", system);
		final String logo = this.welcomeService.getLogo();
		result.addObject("logo", logo);
		result.addObject("administrator", administrator);

		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Administrator administrator, final BindingResult binding) {
		ModelAndView result;

		if (this.actorService.getActorByEmail(administrator.getEmail()) != null
			|| !(administrator.getEmail().matches("[\\w\\.\\w]{1,}(@)") || administrator.getEmail().matches("[\\w\\s\\w]{1,}(<)[\\w\\.\\w]{1,}(@)(>)") || administrator.getEmail().matches("[\\w\\.\\w]{1,}(@)[\\w]{1,}\\.[\\w]{1,}") || administrator
				.getEmail().matches("[\\w\\s\\w]{1,}(<)[\\w\\.\\w]{1,}(@)[\\w]{1,}\\.[\\w]{1,}(>)"))) {
			final ObjectError error = new ObjectError("actor.email", "An account already exists for this email.");
			binding.addError(error);
			binding.rejectValue("email", "error.actor.email.exits");
		}
		if (administrator.getUserAccount().getPassword().length() < 5 || administrator.getUserAccount().getPassword().length() > 32) {
			final ObjectError error = new ObjectError("userAccount.password", "An account already exists for this email.");
			binding.addError(error);
			binding.rejectValue("userAccount.password", "error.userAccount.password");
		}

		if (administrator.getUserAccount().getUsername().length() < 5 || administrator.getUserAccount().getUsername().length() > 32) {
			final ObjectError error = new ObjectError("userAccount.username", "An account already exists for this email.");
			binding.addError(error);
			binding.rejectValue("userAccount.username", "error.userAccount.username");
		}
		if (this.userAccountService.findByUsername(administrator.getUserAccount().getUsername()) != null) {
			final ObjectError error = new ObjectError("userAccount.username", "An account already exists for this email.");
			binding.addError(error);
			binding.rejectValue("userAccount.username", "error.userAccount.username.exits");
		}

		if (binding.hasErrors()) {
			System.out.println("El error pasa por aqu�");
			System.out.println(binding);
			result = new ModelAndView("administrator/create");
		} else
			try {
				System.out.println("El error pasa por aqu�");
				System.out.println(binding);
				if (administrator.getPhone().matches("^([0-9]{4,})$"))
					administrator.setPhone("+" + this.welcomeService.getPhone() + " " + administrator.getPhone());
				final String password = administrator.getUserAccount().getPassword();
				final Md5PasswordEncoder encoder = new Md5PasswordEncoder();
				final String hashPassword = encoder.encodePassword(password, null);
				administrator.getUserAccount().setPassword(hashPassword);
				this.administratorService.save(administrator);
				result = new ModelAndView("welcome/index");
				final String system = this.welcomeService.getSystem();
				result.addObject("system", system);
				final String logo = this.welcomeService.getLogo();
				result.addObject("logo", logo);
				SimpleDateFormat formatter;
				String moment;
				formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
				moment = formatter.format(new Date());
				result.addObject("moment", moment);
			} catch (final Throwable oops) {
				System.out.println("El error: ");
				System.out.println(oops);
				System.out.println(binding);
				result = new ModelAndView("administrator/create");
				final String system = this.welcomeService.getSystem();
				result.addObject("system", system);
				final String logo = this.welcomeService.getLogo();
				result.addObject("logo", logo);
				SimpleDateFormat formatter;
				String moment;
				formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
				moment = formatter.format(new Date());
				result.addObject("moment", moment);
			}
		return result;
	}
	@RequestMapping(value = "/statistics", method = RequestMethod.GET)
	public ModelAndView statistics() {
		final ModelAndView result;

		//12.5.1
		Integer minFixUpPerUser = this.fixUpService.minFixUpHandyWorker();
		if (minFixUpPerUser == null)
			minFixUpPerUser = 0;

		Integer maxFixUpPerUser = this.fixUpService.maxFixUpHandyWorker();
		if (maxFixUpPerUser == null)
			maxFixUpPerUser = 0;

		Double avgFixUpPerUser = this.fixUpService.avgFixUpPerHandyWorker();
		if (avgFixUpPerUser == null)
			avgFixUpPerUser = 0.0;

		Double desviationFixUpPerUser = this.fixUpService.desviationFixUpPerHandyWorker();
		if (desviationFixUpPerUser == null)
			desviationFixUpPerUser = 0.0;
		//
		//12.5.2
		Integer minAppPerFixUp = this.applicationService.minFixUp();
		if (minAppPerFixUp == null)
			minAppPerFixUp = 0;

		Integer maxAppUpPerFixUp = this.applicationService.maxFixUp();
		if (maxAppUpPerFixUp == null)
			maxAppUpPerFixUp = 0;

		Double avgAppPerFixUp = this.applicationService.avgPerFixUp();
		if (avgAppPerFixUp == null)
			avgAppPerFixUp = 0.0;

		Double desviationAppPerFixUp = this.applicationService.desviationPerFixUp();
		if (desviationAppPerFixUp == null)
			desviationAppPerFixUp = 0.0;
		//
		//12.5.3
		Double minPriceFixUp = this.fixUpService.minPriceFixUp();
		if (minPriceFixUp == null)
			minPriceFixUp = 0.0;

		Double maxPriceFixUp = this.fixUpService.maxPriceFixUp();
		if (maxPriceFixUp == null)
			maxPriceFixUp = 0.0;

		Double avgPriceFixUp = this.fixUpService.avgPriceFixUp();
		if (avgPriceFixUp == null)
			avgPriceFixUp = 0.0;

		Double desviationPriceFixUp = this.fixUpService.desviationPriceFixUp();
		if (desviationPriceFixUp == null)
			desviationPriceFixUp = 0.0;
		//
		//12.5.4
		Double minPriceApp = this.applicationService.minPricePerApplication();
		if (minPriceApp == null)
			minPriceApp = 0.0;

		Double maxPriceApp = this.applicationService.maxPricePerApplication();
		if (maxPriceApp == null)
			maxPriceApp = 0.0;

		Double avgPriceApp = this.applicationService.averagePriceApp();
		if (avgPriceApp == null)
			avgPriceApp = 0.0;

		Double desviationPriceApp = this.applicationService.desviationPriceApp();
		if (desviationPriceApp == null)
			desviationPriceApp = 0.0;
		//
		//12.5.5
		Double pendingApplicationRatio = this.applicationService.getRatioPending();
		if (pendingApplicationRatio == null)
			pendingApplicationRatio = 0.0;
		//
		//12.5.6
		Double acceptedApplicationRatio = this.applicationService.getRatioAccepted();
		if (acceptedApplicationRatio == null)
			acceptedApplicationRatio = 0.0;
		//
		//12.5.7
		Double rejectedApplicationRatio = this.applicationService.getRatioRejected();
		if (rejectedApplicationRatio == null)
			rejectedApplicationRatio = 0.0;
		//
		//12.5.8
		Double unModificableApplicationRatio = this.applicationService.getRatioUnmodifiable();
		if (unModificableApplicationRatio == null)
			unModificableApplicationRatio = 0.0;
		//
		//12.5.9
		final Collection<Customer> betterCustomers = this.customerService.betterCustomer();
		//
		//12.5.10
		final Collection<HandyWorker> betterHandyWorkers = this.handyWorkerService.betterHandyWorker();
		//
		System.out.println(betterCustomers);
		System.out.println(betterHandyWorkers);

		result = new ModelAndView("administrator/statistics");

		result.addObject("minFixUpPerUser", minFixUpPerUser);
		result.addObject("maxFixUpPerUser", maxFixUpPerUser);
		result.addObject("avgFixUpPerUser", avgFixUpPerUser);
		result.addObject("desviationFixUpPerUser", desviationFixUpPerUser);

		result.addObject("minAppPerFixUp", minAppPerFixUp);
		result.addObject("maxAppUpPerFixUp", maxAppUpPerFixUp);
		result.addObject("avgAppPerFixUp", avgAppPerFixUp);
		result.addObject("desviationAppPerFixUp", desviationAppPerFixUp);

		result.addObject("minPriceFixUp", minPriceFixUp);
		result.addObject("maxPriceFixUp", maxPriceFixUp);
		result.addObject("avgPriceFixUp", avgPriceFixUp);
		result.addObject("desviationPriceFixUp", desviationPriceFixUp);

		result.addObject("minPriceApp", minPriceApp);
		result.addObject("maxPriceApp", maxPriceApp);
		result.addObject("avgPriceApp", avgPriceApp);
		result.addObject("desviationPriceApp", desviationPriceApp);

		result.addObject("pendingApplicationRatio", pendingApplicationRatio);

		result.addObject("acceptedApplicationRatio", acceptedApplicationRatio);

		result.addObject("rejectedApplicationRatio", rejectedApplicationRatio);

		result.addObject("unModificableApplicationRatio", unModificableApplicationRatio);

		result.addObject("betterCustomers", betterCustomers);

		result.addObject("betterHandyWorkers", betterHandyWorkers);

		final String system = this.welcomeService.getSystem();
		result.addObject("system", system);
		final String logo = this.welcomeService.getLogo();
		result.addObject("logo", logo);

		result.addObject("requestURI", "administrator/statistics.do");

		// CONTROLCHECK
		final Double ratioPublishedQuolet = this.quoletService.getRatioPublished();
		final Double ratioUnpublishedQuolet = this.quoletService.getRatioUnpublished();
		final Double averageQuolet = this.quoletService.getAverage();
		final Double standardDeviation = this.quoletService.getStandardDeviation();
		result.addObject("ratioPublishedQuolet", ratioPublishedQuolet);
		result.addObject("ratioUnpublishedQuolet", ratioUnpublishedQuolet);
		result.addObject("averageQuolet", averageQuolet);
		result.addObject("standardDeviation", standardDeviation);
		// CONTROLCHECK

		return result;
	}
	@RequestMapping(value = "/deleteScoreWordPositiva", method = RequestMethod.GET)
	public ModelAndView deleteScoreWordPositive(@RequestParam("deleteScoreWord") final String ScoreWord) {
		ModelAndView result;

		//		this.administratorService.deleteScoreWordsPositivas(ScoreWord);
		this.administratorService.listScoreWordsPositivas().remove(ScoreWord);
		result = new ModelAndView("redirect:list.do");

		return result;
	}

	@RequestMapping(value = "/deleteScoreWordNegativa", method = RequestMethod.GET)
	public ModelAndView deleteScoreWordNegative(@RequestParam("deleteScoreWord") final String ScoreWord) {
		ModelAndView result;

		//		this.administratorService.deleteScoreWordsNegativas(ScoreWord);
		this.administratorService.listScoreWordsNegativas().remove(ScoreWord);
		result = new ModelAndView("redirect:list.do");

		return result;
	}

	@RequestMapping(value = "/newScoreWordPositiva", method = RequestMethod.GET)
	public ModelAndView newScoreWordPositives(@RequestParam("newScoreWord") final String newScoreWord) {
		ModelAndView result;

		//		this.administratorService.newScoreWordsPositivas(newScoreWord);
		this.administratorService.listScoreWordsPositivas().add(newScoreWord);
		result = new ModelAndView("redirect:list.do");

		return result;
	}

	@RequestMapping(value = "/newScoreWordNegativa", method = RequestMethod.GET)
	public ModelAndView newScoreWord(@RequestParam("newScoreWord") final String newScoreWord) {
		ModelAndView result;

		//		this.administratorService.newScoreWordsNegativas(newScoreWord);
		this.administratorService.listScoreWordsNegativas().add(newScoreWord);
		result = new ModelAndView("redirect:list.do");

		return result;
	}

}
