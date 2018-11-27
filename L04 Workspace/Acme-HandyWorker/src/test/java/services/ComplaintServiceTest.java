
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
import domain.Complaint;
import domain.Customer;
import domain.FixUp;
import domain.HandyWorker;

@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class ComplaintServiceTest extends AbstractTest {

	@Autowired
	private ComplaintService	complaintService;
	@Autowired
	private HandyWorkerService	handyWorkerService;
	@Autowired
	private FixUpService		fixUpService;
	@Autowired
	private CustomerService		customerService;
	@Autowired
	private WarrantyService		warrantyService;
	@Autowired
	private CategoryService		categoryService;


	@Test
	public void testCreateComplaint() {

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

		final Complaint c = this.complaintService.create();
		final Complaint saveCom = this.complaintService.save(c);
		Assert.isTrue(this.complaintService.findAll().contains(saveCom));
	}

	//37.3 --> List and show the complaints regarding the fix-up tasks in which he or shes been in-volved.(test)
	@Test
	public void testListingFixUpHandyWorker() {

		//Use the method of complaintService
		final Collection<Complaint> res = this.complaintService.getAllComplaintsByHandyWorker(416);
		Assert.isTrue(res.size() == 6);
	}

}
