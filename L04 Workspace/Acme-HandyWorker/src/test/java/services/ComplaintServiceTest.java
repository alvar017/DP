
package services;

import java.util.Collection;

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
		final FixUp saveFixUp = this.fixUpService.save(fixUp);
		final Complaint c = this.complaintService.create();
		c.setFixUp(saveFixUp);
		final Complaint saveComplaint = this.complaintService.save(c);
		Assert.isTrue(this.complaintService.findAll().contains(saveComplaint));
	}

	//37.3 --> List and show the complaints regarding the fix-up tasks in which he or shes been in-volved.(test)
	@Test
	public void testListingFixUpHandyWorker() {

		//Use the method of complaintService
		final Collection<Complaint> res = this.complaintService.getAllComplaintsByHandyWorker(416);
		Assert.isTrue(res.size() == 6);
	}

	@Test
	public void testListingComplaintWithoutFixUp() {
		final Administrator administrator = this.administratorService.create();
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
		Assert.isTrue(this.refereeService.findAll().contains(saveReferee));

		final HandyWorker handyWorker = this.handyWorkerService.create();
		handyWorker.setName("CarmenHW");
		handyWorker.setSurname("carmenHW");
		handyWorker.getUserAccount().setUsername("carferbenHW");
		handyWorker.getUserAccount().setPassword("123456789");
		final HandyWorker saveHandyWorker = this.handyWorkerService.save(handyWorker);
		super.authenticate("carferbenHW");

		final Customer customer = this.customerService.create();
		customer.setName("Alvaro");
		customer.setSurname("alvaro");
		customer.getUserAccount().setUsername("customerAuth");
		customer.getUserAccount().setPassword("123456789");
		final Customer saveCustomer = this.customerService.save(customer);
		super.unauthenticate();
		super.authenticate("customerAuth");

		final FixUp fixUp = this.fixUpService.create();
		final FixUp saveFixUp = this.fixUpService.save(fixUp);

		final Complaint c1 = this.complaintService.create();

		super.unauthenticate();
		super.authenticate("refereeUser");

		final int sizeComplaintWithoutRefereeBefore = this.complaintService.getComplaintWithoutReferee().size();

		super.unauthenticate();
		super.authenticate("customerAuth");

		final Complaint saveCom1 = this.complaintService.save(c1);
		final Complaint c2 = this.complaintService.create();
		c2.setReferee(saveReferee);
		final Complaint saveCom2 = this.complaintService.save(c2);
		super.unauthenticate();
		super.authenticate("refereeUser");
		final int sizeComplaintWithoutRefereeAfter = this.complaintService.getComplaintWithoutReferee().size();
		Assert.isTrue(sizeComplaintWithoutRefereeAfter == sizeComplaintWithoutRefereeBefore + 1);
	}

}
