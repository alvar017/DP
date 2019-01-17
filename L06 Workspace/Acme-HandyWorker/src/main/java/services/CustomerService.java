
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.CustomerRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Administrator;
import domain.Customer;
import domain.FixUp;
import domain.MailBox;

@Service
@Transactional
public class CustomerService {

	//Managed Repository -------------------	

	@Autowired
	private CustomerRepository		customerRepository;

	//Supporting services ------------------

	@Autowired
	private FixUpService			fixUpService;

	@Autowired
	private MailBoxService			mailBoxService;

	@Autowired
	private AdministratorService	administratorService;


	//Simple CRUD Methods ------------------

	public Customer create() {

		final Customer customer = new Customer();
		final UserAccount cuenta = new UserAccount();
		final List<Authority> autoridades = new ArrayList<>();
		final Authority authority = new Authority();
		authority.setAuthority(Authority.CUSTOMER);
		autoridades.add(authority);
		cuenta.setAuthorities(autoridades);
		customer.setCalification(0.1);
		customer.setUserAccount(cuenta);

		final Collection<MailBox> boxesDefault = new ArrayList<>();

		final MailBox inBox = this.mailBoxService.create();
		inBox.setName("inBox");
		inBox.setIsDefault(true);
		final MailBox outBox = this.mailBoxService.create();
		outBox.setName("outBox");
		outBox.setIsDefault(true);
		final MailBox spamBox = this.mailBoxService.create();
		spamBox.setName("spamBox");
		spamBox.setIsDefault(true);
		final MailBox trashBox = this.mailBoxService.create();
		trashBox.setName("trashBox");
		trashBox.setIsDefault(true);

		final MailBox inBoxSave = this.mailBoxService.save(inBox);
		final MailBox outBoxSave = this.mailBoxService.save(outBox);
		final MailBox spamBoxSave = this.mailBoxService.save(spamBox);
		final MailBox trashBoxSave = this.mailBoxService.save(trashBox);

		boxesDefault.add(inBoxSave);
		boxesDefault.add(outBoxSave);
		boxesDefault.add(spamBoxSave);
		boxesDefault.add(trashBoxSave);

		customer.setMailBoxes(boxesDefault);
		customer.setIsBanned(false);
		customer.setIsSuspicious(false);

		return customer;
	}

	public Collection<Customer> findAll() {
		return this.customerRepository.findAll();

	}

	public Customer findOne(final int id) {
		final Customer result = this.customerRepository.findOne(id);
		return result;
	}

	public Customer save(final Customer customer) {
		return this.customerRepository.save(customer);
	}

	//Other Methods

	public Customer findOwner(final FixUp fixUp) {

		final UserAccount user = LoginService.getPrincipal();
		final Authority authority = new Authority();
		authority.setAuthority(Authority.HANDYWORKER);
		Assert.isTrue(user.getAuthorities().contains(authority));

		return fixUp.getCustomer();
	}
	//----------MARI-----------------------
	// UN CUSTOMER MODIFICA SOLO SUS DATOS
	public Customer update(final Customer customer) {
		Assert.isTrue(LoginService.getPrincipal().getId() == customer.getUserAccount().getId());
		return this.customerRepository.save(customer);
	}

	//UN CUSTOMER AUTENTICADO NO PUEDE VOLVER A REGISTRARSE

	public Customer isRegister(final Customer customer) {
		final UserAccount a = customer.getUserAccount();
		Assert.isTrue(a.getUsername() == null);
		return this.customerRepository.save(customer);
	}

	public Customer getCustomerByUserAccountId(final int userAccountId) {
		Customer res;
		res = this.customerRepository.findByUserAccountId(userAccountId);
		return res;
	}

	//1259
	public Collection<Customer> betterCustomer() {
		final Administrator a = this.administratorService.findByUserAccount(LoginService.getPrincipal().getId());
		Assert.notNull(a);
		final Collection<Customer> result = this.customerRepository.betterCustomer();
		return result;
	}

	public Collection<Customer> getAllCustomersByHandyWorkers(final int hwId) {
		return this.customerRepository.getAllCustomersByHandyWorkers(hwId);
	}

	//AÑADIDO
	public Collection<Customer> getTopThreeCustomers() {
		Collection<Customer> list = this.customerRepository.getTopThreeCustomers();
		final List<Customer> customers = new ArrayList<>(list);
		if (customers.size() <= 3 && customers.size() > 0)
			customers.subList(0, customers.size());
		else if (customers.size() > 0)
			customers.subList(0, 2);

		list = customers;
		return list;
	}
}
