
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ApplicationRepository;
import security.LoginService;
import security.UserAccount;
import domain.Application;
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
	private CustomerService			customerService;


	//Simple CRUD Methods ------------------

	public Application create() {

		final Application application = new Application();
		final UserAccount login = LoginService.getPrincipal();
		final HandyWorker handyWorker = this.handyWorkerService.getHandyWorkerByUserAccountId(login.getId());
		application.setApplier(handyWorker);
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

	public void update(final Application application) {
		this.applicationRepository.save(application);
	}

	//Other Methods

	public Collection<Application> findAllByCustomer(final Customer c) {
		return this.applicationRepository.findAllByCustomer(c.getId());
	}

	public Collection<Application> findAllByFixUp(final FixUp f) {
		return this.applicationRepository.findAllByFixUp(f.getId());
	}
}
