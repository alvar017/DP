
package services;

import java.util.Collection;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ApplicationRepository;
import security.LoginService;
import security.UserAccount;
import domain.Administrator;
import domain.Application;
import domain.CreditCard;
import domain.Customer;
import domain.FixUp;
import domain.HandyWorker;

@Service
@Transactional
public class ApplicationService {

	//Managed Repository -------------------	

	@Autowired
	private ApplicationRepository	applicationRepository;

	//Supporting services ------------------
	@Autowired
	private HandyWorkerService		handyWorkerService;

	@Autowired
	private AdministratorService	administratorService;

	@Autowired
	private CustomerService			customerService;

	@Autowired
	private FixUpService			fixUpService;


	//Simple CRUD Methods ------------------

	public Application create() {

		final Application application = new Application();
		final UserAccount login = LoginService.getPrincipal();
		final HandyWorker handyWorker = this.handyWorkerService.getHandyWorkerByUserAccountId(login.getId());
		application.setApplier(handyWorker);
		application.setMoment(LocalDate.now().toDate());
		return application;

	}
	public Collection<Application> findAll() {
		//		final UserAccount login = LoginService.getPrincipal();
		//		final Customer customer = this.customerService.getCustomerByUserAccountId(login.getId());
		//
		//		return this.applicationRepository.findAllByCustomer(customer.getId());
		return this.applicationRepository.findAll();
	}
	public Application findOne(final int id) {
		return this.applicationRepository.findOne(id);
	}
	public Application save(final Application application) {
		return this.applicationRepository.save(application);
	}
	public void delete(final Application application) {
		Assert.notNull(this.applicationRepository.findOne(application.getId()), "La application no existe");
		this.applicationRepository.delete(application);
	}

	public Application update(final Application application) {
		final UserAccount login = LoginService.getPrincipal();
		Assert.isTrue(login != null);
		final int idLogin = login.getId();
		final HandyWorker hw = this.handyWorkerService.getHandyWorkerByUserAccountId(idLogin);
		Assert.isTrue(hw.equals(application.getApplier()));
		final Application originalApplication = this.findOne(application.getId());
		if (originalApplication.getState() != null && originalApplication.getState() == true)
			//			if (true)
			Assert.isTrue(application.getCreditCard() != null, "application.error.creditCard");
		final Application saveApplication = this.applicationRepository.save(application);
		return saveApplication;
	}

	public Application updateCustomer(final Application application) {
		final UserAccount login = LoginService.getPrincipal();
		Assert.isTrue(login != null);
		final int idLogin = login.getId();
		final Customer customer = this.customerService.getCustomerByUserAccountId(idLogin);
		Assert.isTrue(customer.equals(application.getFixUp().getCustomer()));
		final Application originalApplication = this.findOne(application.getId());
		if (originalApplication.getState() != null && originalApplication.getState() == true) {
			//			if (true)
			Assert.isTrue(this.checkCreditCard(application.getCreditCard()), "application.error.creditCard");
			System.out.println("Intento actualizar hw");
			final FixUp fixUp = application.getFixUp();
			Assert.isTrue(fixUp != null);
			fixUp.setHandyWorker(application.getApplier());
			this.fixUpService.save(fixUp);
		}
		final Application saveApplication = this.applicationRepository.save(application);
		return saveApplication;
	}

	private Boolean checkCreditCard(final CreditCard creditCard) {
		Boolean res = true;
		if (creditCard.getBrand().trim().isEmpty() || creditCard.getName().trim().isEmpty() || creditCard.getNumber().trim().isEmpty())
			res = false;
		return res;
	}
	//Other Methods

	public Collection<Application> findAllByCustomer(final Customer c) {
		final UserAccount login = LoginService.getPrincipal();
		Assert.isTrue(login != null);
		final int idLogin = login.getId();
		Assert.isTrue(c.getUserAccount().getId() == idLogin);
		return this.applicationRepository.findAllByCustomer(c.getId());
	}

	public Collection<Application> findAllByCustomerLogger() {
		final UserAccount login = LoginService.getPrincipal();
		Assert.isTrue(login != null);
		final int idLogin = login.getId();
		final Customer c = this.customerService.getCustomerByUserAccountId(idLogin);
		Assert.isTrue(c != null);
		return this.applicationRepository.findAllByCustomer(c.getId());
	}

	public Collection<Application> findAllByFixUp(final FixUp f) {
		return this.applicationRepository.findAllByFixUp(f.getId());
	}

	//1255
	public Double getRatioPending() {
		final Administrator a = this.administratorService.findByUserAccount(LoginService.getPrincipal().getId());
		Assert.notNull(a);
		final Double result = this.applicationRepository.getRatioPending();
		return result;
	}

	//1256
	public Double getRatioAccepted() {
		final Administrator a = this.administratorService.findByUserAccount(LoginService.getPrincipal().getId());
		Assert.notNull(a);
		final Double result = this.applicationRepository.getRatioAccepted();
		return result;
	}

	//1257
	public Double getRatioRejected() {
		final Administrator a = this.administratorService.findByUserAccount(LoginService.getPrincipal().getId());
		Assert.notNull(a);
		final Double result = this.applicationRepository.getRatioRejected();
		return result;
	}

	//1258
	public Double getRatioUnmodifiable() {
		final Administrator a = this.administratorService.findByUserAccount(LoginService.getPrincipal().getId());
		Assert.notNull(a);
		final Double result = this.applicationRepository.getRatioUnmodifiable();
		return result;
	}

	//1252
	public Integer minFixUp() {
		final Administrator a = this.administratorService.findByUserAccount(LoginService.getPrincipal().getId());
		Assert.notNull(a);
		final Integer result = this.applicationRepository.minFixUp();
		return result;
	}

	//1252
	public Integer maxFixUp() {
		final Administrator a = this.administratorService.findByUserAccount(LoginService.getPrincipal().getId());
		Assert.notNull(a);
		final Integer result = this.applicationRepository.maxFixUp();
		return result;
	}

	//1252
	public Double avgPerFixUp() {
		final Administrator a = this.administratorService.findByUserAccount(LoginService.getPrincipal().getId());
		Assert.notNull(a);
		final Double result = this.applicationRepository.avgPerFixUp();
		return result;
	}

	//1252
	public Double desviationPerFixUp() {
		final Administrator a = this.administratorService.findByUserAccount(LoginService.getPrincipal().getId());
		Assert.notNull(a);
		final Double result = this.applicationRepository.desviationPerFixUp();
		return result;
	}

	//1254
	public Double maxPricePerApplication() {
		final Administrator a = this.administratorService.findByUserAccount(LoginService.getPrincipal().getId());
		Assert.notNull(a);
		final Double result = this.applicationRepository.maxPricePerApplication();
		return result;
	}

	//1254
	public Double minPricePerApplication() {
		final Administrator a = this.administratorService.findByUserAccount(LoginService.getPrincipal().getId());
		Assert.notNull(a);
		final Double result = this.applicationRepository.minPricePerApplication();
		return result;
	}

	//1254
	public Double averagePriceApp() {
		final Administrator a = this.administratorService.findByUserAccount(LoginService.getPrincipal().getId());
		Assert.notNull(a);
		final Double result = this.applicationRepository.averagePriceApp();
		return result;
	}

	//1254
	public Double desviationPriceApp() {
		final Administrator a = this.administratorService.findByUserAccount(LoginService.getPrincipal().getId());
		Assert.notNull(a);
		final Double result = this.applicationRepository.desviationPriceApp();
		return result;
	}

	public Collection<Application> findAllByHandyWorker(final int id) {
		return this.applicationRepository.findAllByHandyWorker(id);
	}
}