
package services;

import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Administrator;
import domain.Complaint;
import domain.Customer;
import domain.FixUp;
import domain.HandyWorker;
import domain.Referee;

@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class ComplaintServiceTest extends AbstractTest {

	@Autowired
	private ComplaintService		complaintService;
	@Autowired
	private HandyWorkerService		handyWorkerService;
	@Autowired
	private FixUpService			fixUpService;
	@Autowired
	private CustomerService			customerService;
	@Autowired
	private AdministratorService	administratorService;
	@Autowired
	private RefereeService			refereeService;


	@Test
	public void testCreateComplaint() {

		final Complaint c = this.complaintService.create();
		Assert.isTrue(c != null);
	}

	@Test
	public void testSaveComplaint() {
		final Customer customer = this.customerService.create();
		customer.setName("Alvaro");
		customer.setSurname("alvaro");
		customer.getUserAccount().setUsername("dogran");
		customer.getUserAccount().setPassword("123456789");
		final Customer saveCustomer = this.customerService.save(customer);
		super.authenticate("dogran");
		final FixUp fixUp = this.fixUpService.create();
		@SuppressWarnings("deprecation")
		final Date startDate = new Date(2019, 11, 11);
		@SuppressWarnings("deprecation")
		final Date endDate = new Date(2019, 12, 11);
		fixUp.setStartDate(startDate);
		fixUp.setEndDate(endDate);
		fixUp.setAddress("AddressTest");
		fixUp.setDescription("DescriptionTest");
		final FixUp saveFixUp = this.fixUpService.save(fixUp);
		final Complaint c = this.complaintService.create();
		c.setFixUp(saveFixUp);
		c.setDescription("test");
		final Complaint saveComplaint = this.complaintService.save(c);
		Assert.isTrue(this.complaintService.findAll().contains(saveComplaint));
	}

	//37.3 --> List and show the complaints regarding the fix-up tasks in which he or shes been in-volved.(test)
	//Este falla pero carmen lo tiene bien
	//	@Test
	//	public void testListingFixUpHandyWorker() {
	//
	//		//Use the method of complaintService
	//		final Collection<Complaint> res = this.complaintService.getAllComplaintsByHandyWorker(463);
	//		Assert.isTrue(res.size() == 6);
	//	}

	@Test
	public void testListingComplaintWithoutReferee() {
		final Administrator administrator = this.administratorService.createFirstAdmin();
		administrator.setName("Ana");
		administrator.setSurname("navarro");
		administrator.getUserAccount().setUsername("adminUser");
		administrator.getUserAccount().setPassword("12345678");
		final Administrator saveAdministrator = this.administratorService.save(administrator);
		Assert.isTrue(this.administratorService.findAll().contains(saveAdministrator));
		super.authenticate("adminUser");
		final Referee referee = this.refereeService.create();
		referee.setName("Alvaro");
		referee.setSurname("alvaro");
		referee.getUserAccount().setUsername("refereeUser");
		referee.getUserAccount().setPassword("12345678");
		final Referee saveReferee = this.refereeService.save(referee);
		super.authenticate("refereeUser");
		final int complaintWithoutRefereeBefore = this.complaintService.getComplaintWithoutReferee().size();
		super.unauthenticate();

		final Customer customer = this.customerService.create();
		customer.setName("Alvaro");
		customer.setSurname("alvaro");
		customer.getUserAccount().setUsername("dogran");
		customer.getUserAccount().setPassword("123456789");
		final Customer saveCustomer = this.customerService.save(customer);
		super.authenticate("dogran");
		final FixUp fixUp = this.fixUpService.create();
		@SuppressWarnings("deprecation")
		final Date startDate = new Date(2019, 11, 11);
		@SuppressWarnings("deprecation")
		final Date endDate = new Date(2019, 12, 11);
		fixUp.setStartDate(startDate);
		fixUp.setEndDate(endDate);
		fixUp.setAddress("AddressTest");
		fixUp.setDescription("DescriptionTest");
		final FixUp saveFixUp = this.fixUpService.save(fixUp);
		final Complaint c1 = this.complaintService.create();
		final Complaint c2 = this.complaintService.create();
		c1.setFixUp(saveFixUp);
		c1.setDescription("test");

		c1.setReferee(saveReferee);
		c2.setFixUp(saveFixUp);
		c2.setDescription("test");

		super.unauthenticate();

		super.authenticate("refereeUser");
		final Complaint saveComplaint1 = this.complaintService.save(c1);
		final Complaint saveComplaint2 = this.complaintService.save(c2);
		final int complaintWithoutRefereeAfter = this.complaintService.getComplaintWithoutReferee().size();
		Assert.isTrue(complaintWithoutRefereeBefore + 1 == complaintWithoutRefereeAfter);
	}

	@Test
	public void testAssignedRefereeToComplaint() {
		final Customer customer = this.customerService.create();
		customer.setName("Alvaro");
		customer.setSurname("alvaro");
		customer.getUserAccount().setUsername("dogran");
		customer.getUserAccount().setPassword("123456789");
		final Customer saveCustomer = this.customerService.save(customer);
		super.authenticate("dogran");
		final FixUp fixUp = this.fixUpService.create();
		@SuppressWarnings("deprecation")
		final Date startDate = new Date(2019, 11, 11);
		@SuppressWarnings("deprecation")
		final Date endDate = new Date(2019, 12, 11);
		fixUp.setStartDate(startDate);
		fixUp.setEndDate(endDate);
		fixUp.setAddress("AddressTest");
		fixUp.setDescription("DescriptionTest");
		final FixUp saveFixUp = this.fixUpService.save(fixUp);
		final Complaint c1 = this.complaintService.create();
		c1.setFixUp(saveFixUp);
		c1.setDescription("test");

		final Complaint saveComplaint1 = this.complaintService.save(c1);
		super.unauthenticate();
		// Creo un referee
		final Administrator administrator = this.administratorService.createFirstAdmin();
		administrator.setName("Ana");
		administrator.setSurname("navarro");
		administrator.getUserAccount().setUsername("adminUser");
		administrator.getUserAccount().setPassword("12345678");
		final Administrator saveAdministrator = this.administratorService.save(administrator);
		Assert.isTrue(this.administratorService.findAll().contains(saveAdministrator));
		super.authenticate("adminUser");
		final Referee referee = this.refereeService.create();
		referee.setName("Alvaro");
		referee.setSurname("alvaro");
		referee.getUserAccount().setUsername("refereeUser");
		referee.getUserAccount().setPassword("12345678");
		final Referee saveReferee = this.refereeService.save(referee);
		super.authenticate("refereeUser");
		// Asocio el referee al complaint
		final Boolean refereeBefore = saveComplaint1.getReferee() != null;
		this.complaintService.setReefereeToAComplaint(saveComplaint1);
		final Boolean refereeAfter = saveComplaint1.getReferee() != null;
		Assert.isTrue(refereeAfter != refereeBefore);
	}

	@Test
	public void testListingByReferee() {
		final Customer customer = this.customerService.create();
		customer.setName("Alvaro");
		customer.setSurname("alvaro");
		customer.getUserAccount().setUsername("dogran");
		customer.getUserAccount().setPassword("123456789");
		final Customer saveCustomer = this.customerService.save(customer);
		super.authenticate("dogran");
		final FixUp fixUp = this.fixUpService.create();
		@SuppressWarnings("deprecation")
		final Date startDate = new Date(2019, 11, 11);
		@SuppressWarnings("deprecation")
		final Date endDate = new Date(2019, 12, 11);
		fixUp.setStartDate(startDate);
		fixUp.setEndDate(endDate);
		fixUp.setAddress("AddressTest");
		fixUp.setDescription("DescriptionTest");
		final FixUp saveFixUp = this.fixUpService.save(fixUp);
		final Complaint c1 = this.complaintService.create();
		c1.setFixUp(saveFixUp);
		c1.setDescription("test");

		final Complaint saveComplaint1 = this.complaintService.save(c1);
		super.unauthenticate();
		// Creo un referee
		final Administrator administrator = this.administratorService.createFirstAdmin();
		administrator.setName("Ana");
		administrator.setSurname("navarro");
		administrator.getUserAccount().setUsername("adminUser");
		administrator.getUserAccount().setPassword("12345678");
		final Administrator saveAdministrator = this.administratorService.save(administrator);
		Assert.isTrue(this.administratorService.findAll().contains(saveAdministrator));
		super.authenticate("adminUser");
		final Referee referee = this.refereeService.create();
		referee.setName("Alvaro");
		referee.setSurname("alvaro");
		referee.getUserAccount().setUsername("refereeUser");
		referee.getUserAccount().setPassword("12345678");
		final Referee saveReferee = this.refereeService.save(referee);
		super.authenticate("refereeUser");
		// Asocio el referee al complaint
		final Boolean refereeBefore = saveComplaint1.getReferee() != null;
		this.complaintService.setReefereeToAComplaint(saveComplaint1);
		final Boolean refereeAfter = saveComplaint1.getReferee() != null;
		Assert.isTrue(refereeAfter != refereeBefore);
		Assert.isTrue(this.complaintService.getComplaintByReferee(saveReferee.getId()).size() == 1);
	}

	@Test
	public void testListingFixUpHandyWorker() {

		final HandyWorker handyWorker = this.handyWorkerService.create();
		handyWorker.setName("Alvaro");
		handyWorker.setSurname("alvaro");
		handyWorker.getUserAccount().setUsername("hwAuth");
		handyWorker.getUserAccount().setPassword("123456789");
		final HandyWorker saveHandyWorker = this.handyWorkerService.save(handyWorker);
		super.authenticate("hwAuth");

		final Customer customer = this.customerService.create();
		customer.setName("Carmen");
		customer.setSurname("carmen");
		customer.getUserAccount().setUsername("carferben");
		customer.getUserAccount().setPassword("123456789");
		final Customer saveCustomer = this.customerService.save(customer);
		super.authenticate("carferben");

		final FixUp fixUp1 = this.fixUpService.create();
		@SuppressWarnings("deprecation")
		final Date startDate = new Date(2019, 11, 11);
		@SuppressWarnings("deprecation")
		final Date endDate = new Date(2019, 12, 11);
		fixUp1.setStartDate(startDate);
		fixUp1.setEndDate(endDate);
		fixUp1.setAddress("AddressTest");
		fixUp1.setDescription("DescriptionTest");
		fixUp1.setHandyWorker(saveHandyWorker);
		fixUp1.setCustomer(saveCustomer);
		final FixUp saveFixUp1 = this.fixUpService.save(fixUp1);

		final Complaint c = this.complaintService.create();
		c.setFixUp(saveFixUp1);
		c.setDescription("test");

		final Complaint cSave = this.complaintService.save(c);

		super.authenticate("hwAuth");

		final Collection<Complaint> res = this.complaintService.getAllComplaintsByHandyWorker();
		Assert.isTrue(res.size() == 1);
	}
	//CARMEN

	//35.1 (FRAN)
	@Test
	public void testFindAll() {

		final Collection<Complaint> all = this.complaintService.findAll();
		Assert.isTrue(all.size() == 6);
	}
	//FRAN

	//35.1 (FRAN)
	@Test
	public void testFindOne() {

		final Customer customer = this.customerService.create();
		customer.setName("Alvaro");
		customer.setSurname("alvaro");
		customer.getUserAccount().setUsername("customerAuth");
		customer.getUserAccount().setPassword("123456789");
		this.customerService.save(customer);
		super.authenticate("customerAuth");

		final Complaint c = this.complaintService.create();
		final FixUp fixUp = this.fixUpService.create();
		@SuppressWarnings("deprecation")
		final Date startDate = new Date(2019, 11, 11);
		@SuppressWarnings("deprecation")
		final Date endDate = new Date(2019, 12, 11);
		fixUp.setStartDate(startDate);
		fixUp.setEndDate(endDate);
		fixUp.setAddress("AddressTest");
		fixUp.setDescription("DescriptionTest");
		final FixUp savedFixUp = this.fixUpService.save(fixUp);
		c.setFixUp(savedFixUp);
		c.setDescription("test");

		final Complaint savedC = this.complaintService.save(c);

		final Complaint one = this.complaintService.findOne(savedC.getId());

		Assert.isTrue(savedC.getId() == one.getId());
	}
	//FRAN

}
