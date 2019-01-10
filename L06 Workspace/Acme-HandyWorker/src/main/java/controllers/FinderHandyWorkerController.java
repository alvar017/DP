/*
 * handyWorkerController.java
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

import javax.validation.Valid;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import security.LoginService;
import security.UserAccount;
import services.CategoryService;
import services.FinderService;
import services.FixUpService;
import services.HandyWorkerService;
import services.WarrantyService;
import services.WelcomeService;
import domain.Category;
import domain.Finder;
import domain.FixUp;
import domain.HandyWorker;
import domain.Warranty;

@Controller
@RequestMapping("/finder/handyWorker")
public class FinderHandyWorkerController extends AbstractController {

	@Autowired
	private FinderService		finderService;

	@Autowired
	private CategoryService		categoryService;

	@Autowired
	private WarrantyService		warrantyService;

	@Autowired
	private FixUpService		fixUpService;

	@Autowired
	private HandyWorkerService	handyWorkerService;

	@Autowired
	private WelcomeService		welcomeService;


	// Constructors -----------------------------------------------------------

	public FinderHandyWorkerController() {
		super();
	}

	// list ---------------------------------------------------------------		

	@RequestMapping(value = "/list")
	public ModelAndView list() {
		final ModelAndView result;
		final SimpleDateFormat formatter;

		final Finder finder = this.finderService.yourFinder();
		final String language = LocaleContextHolder.getLocale().getDisplayLanguage();

		formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		final String now = formatter.format(new Date());
		final String finderDateS = formatter.format(finder.getDate());

		System.out.println(now);
		System.out.println(finderDateS);

		final String[] elementosN = now.split(" ");
		final String fechaN = elementosN[0];
		final String horaN = elementosN[1];
		final String[] elementosF = finderDateS.split(" ");
		final String fechaF = elementosF[0];
		final String horaF = elementosF[1];

		System.out.println(fechaN);
		System.out.println(horaN);
		System.out.println(fechaF);
		System.out.println(horaF);

		final String[] horasN = horaN.split(":");
		final String nowH = horasN[0];
		final String nowM = horasN[1];
		final Integer nowHora = Integer.parseInt(nowH);
		final Integer nowMin = Integer.parseInt(nowM);

		final String[] horasF = horaF.split(":");
		final String finderH = horasF[0];
		final String finderM = horasF[1];
		final Integer finderHora = Integer.parseInt(finderH);
		final Integer finderMin = Integer.parseInt(finderM);

		final String[] diasN = fechaN.split("/");
		final String nowD = diasN[0];
		final String nowMes = diasN[1];
		final String nowA = diasN[2];
		final Integer nowDia = Integer.parseInt(nowD);

		final String[] diasF = fechaF.split("/");
		final String finderD = diasF[0];
		final String finderMes = diasF[1];
		final String fidnerA = diasF[2];
		final Integer finderDia = Integer.parseInt(finderD);

		if (((finderMin + nowMin) > this.finderService.getTime() * 60) || ((finderMin + nowMin) == 0 && Math.abs(nowDia - finderDia) >= this.finderService.getTime())
			|| ((finderMin + nowMin) < this.finderService.getTime() * 60 && Math.abs(nowDia - finderDia) >= this.finderService.getTime())) {

			final Collection<FixUp> fixUps = this.fixUpService.filterFixUps(finder.getKeyword(), finder.getWarranty(), finder.getCategory(), finder.getStartDate(), finder.getEndDate(), finder.getMinPrice(), finder.getMaxPrice());
			System.out.println(fixUps);
			finder.setFixUps(fixUps);
			this.finderService.save(finder);
			this.finderService.fixUpByFinder(finder.getFixUps());
		} else {
			final UserAccount user = LoginService.getPrincipal();
			final HandyWorker handyWorker = this.handyWorkerService.getHandyWorkerByUserAccountId(user.getId());
			Assert.isTrue(handyWorker != null);
			handyWorker.getSocialProfiles().remove(finder);

		}

		final Collection<FixUp> finderFixUp = this.finderService.getFinderFixUp();

		System.out.println(finderFixUp);

		//System
		final String system = this.welcomeService.getSystem();

		//RESULT
		final Integer resultF = this.finderService.getResult();

		result = new ModelAndView("finder/handyWorker/list");
		result.addObject("language", language);
		result.addObject("requestURI", "finder/handyWorker/list.do");
		result.addObject("resultF", resultF);
		result.addObject("system", system);
		result.addObject("finderFixUp", finder.getFixUps());

		return result;
	}
	//create----------------------------------------------------
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {

		ModelAndView res;

		final Collection<Category> category = this.categoryService.findAll();
		final Collection<Warranty> warranty = this.warrantyService.findAll();

		Finder finder;

		finder = this.finderService.create();

		final UserAccount user = LoginService.getPrincipal();
		final HandyWorker hw = this.handyWorkerService.getHandyWorkerByUserAccountId(user.getId());
		hw.setFinder(finder);

		res = this.createEditModelAndView(finder);

		res.addObject("category", category);
		res.addObject("warranty", warranty);

		return res;
	}

	//edit--------------------------------------------------------------

	//	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	//	public ModelAndView edit(@RequestParam("finderId") final int finderId) {
	//		ModelAndView result;
	//
	//		System.out.println("Carmen: Entro en edit");
	//
	//		final Collection<Category> category = this.categoryService.findAll();
	//		final Collection<Warranty> warranty = this.warrantyService.findAll();
	//
	//		final String language = LocaleContextHolder.getLocale().getDisplayLanguage();
	//
	//		final Finder finder = this.finderService.yourFinder();
	//
	//		result = this.createEditModelAndView(finder);
	//
	//		result.addObject("language", language);
	//		result.addObject("category", category);
	//		result.addObject("warranty", warranty);
	//
	//		System.out.println("Carmen: Salgo de edit");
	//
	//		return result;
	//	}

	//save ---------------------	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Finder finder, final BindingResult binding) {
		ModelAndView result;

		System.out.println("Carmen: Entro en el save");

		System.out.println("BindingErrors: " + binding.getFieldErrors());
		//		if (finder.getMaxPrice() == null || finder.getMaxPrice() < 0) {
		//			final ObjectError error = new ObjectError("maxPrice", "An account already exists for this email.");
		//			binding.addError(error);
		//			binding.rejectValue("maxPrice", "error.maxPrice.negative");
		//		}
		//		if (finder.getMinPrice() == null || finder.getMinPrice() < 0) {
		//			final ObjectError error = new ObjectError("minPrice", "An account already exists for this email.");
		//			binding.addError(error);
		//			binding.rejectValue("minPrice", "error.minPrice.negative");
		//		}
		if (binding.hasErrors()) {
			System.out.println("Carmen: Hay ERRORES");
			result = this.createEditModelAndView(finder);
		} else
			try {
				System.out.println("Carmen: Comienzo el try");

				final Date date = finder.getDate();
				final Date dateF = LocalDate.now().toDate();

				final Category category = finder.getCategory();
				final Warranty warranty = finder.getWarranty();
				final String keyword = finder.getKeyword();
				final Date endDate = finder.getEndDate();
				final Date startDate = finder.getStartDate();
				final Double minMoney = finder.getMinPrice();
				final Double maxMoney = finder.getMaxPrice();

				System.out.println("Category" + category);
				System.out.println("Warranty" + warranty);

				final Collection<FixUp> fixUps = this.fixUpService.filterFixUps(keyword, warranty, category, startDate, endDate, minMoney, maxMoney);

				System.out.println(fixUps);

				finder.setFixUps(fixUps);

				System.out.println(finder.getId());
				final Finder finderSave = this.finderService.save(finder);

				//				final UserAccount userLogger = LoginService.getPrincipal();
				//				final HandyWorker hwLogger = this.handyWorkerService.getHandyWorkerByUserAccountId(userLogger.getId());
				//				hwLogger.setFinder(finder);
				//
				//				System.out.println(hwLogger.getFinder().getFixUps());
				//
				//				System.out.println("Finder id" + hwLogger.getFinder().getId());
				//
				//				this.handyWorkerService.save(hwLogger);ç

				final UserAccount user = LoginService.getPrincipal();
				final HandyWorker handyWorker = this.handyWorkerService.getHandyWorkerByUserAccountId(user.getId());
				Assert.isTrue(handyWorker != null);
				if (handyWorker.getFinder() != null) {
					handyWorker.getSocialProfiles().remove(finder);
					handyWorker.setFinder(finderSave);
				} else
					handyWorker.setFinder(finderSave);
				final HandyWorker savedHandyWorker = this.handyWorkerService.save(handyWorker);

				System.out.println("Hw" + savedHandyWorker.getFixUps());

				final Collection<FixUp> finderFixUp = this.finderService.fixUpByFinder(finder.getFixUps());

				System.out.println("Carmen: !PUEDO GUARDAR¡");
				System.out.println("Busqueda de fixUps nuevos" + finder.getFixUps());
				System.out.println(finderFixUp);
				result = new ModelAndView("redirect:list.do");

				result.addObject("handyWorker", savedHandyWorker);
			} catch (final Throwable oops) {
				System.out.println("Carmen: !NO PUEDO GUARDAR¡");
				System.out.println("finder error:" + oops);
				if (oops.getMessage().equals("fixUp.wrongDate")) {
					System.out.println("1");
					result = this.createEditModelAndView(finder, "finder.wrongDate");
				} else if (oops.getMessage().equals("finder.wrongMomentDate")) {
					System.out.println("2");
					result = this.createEditModelAndView(finder, "finder.wrongMomentDate");
				} else if (oops.getMessage().equals("finder.wrongP")) {
					System.out.println("3");
					result = this.createEditModelAndView(finder, "finder.wrongP");
				} else if (oops.getMessage().equals("finder.wrong")) {
					System.out.println("4");
					result = this.createEditModelAndView(finder, "finder.wrong");
				} else
					result = this.createEditModelAndView(finder, "finder.wrongDate");
			}

		return result;
	}

	//others---------------------------
	private ModelAndView createEditModelAndView(final Finder finder) {
		ModelAndView result;

		System.out.println("Carmen:!Puedo entrar en createEditModelAndWiew de finder¡");

		result = new ModelAndView("finder/handyWorker/edit");

		result.addObject("finder", finder);

		System.out.println("Carmen: Salgo en createEditModelAndWiew de finder");

		return result;
	}

	private ModelAndView createEditModelAndView(final Finder finder, final String messageCode) {
		ModelAndView result;

		final Collection<Category> category = this.categoryService.findAll();
		final Collection<Warranty> warranty = this.warrantyService.findAll();

		result = new ModelAndView("finder/handyWorker/edit");

		result.addObject("finder", finder);
		result.addObject("message", messageCode);

		result.addObject("category", category);
		result.addObject("warranty", warranty);

		return result;
	}

}
