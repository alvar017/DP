
package services;

import java.util.Date;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Customer;
import domain.FixUp;
import domain.HandyWorker;
import domain.Phase;

@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class PhaseServiceTest extends AbstractTest {

	@Autowired
	private ReportService			reportService;
	@Autowired
	private AdministratorService	administratorService;
	@Autowired
	private RefereeService			refereeService;
	@Autowired
	private ComplaintService		complaintService;
	@Autowired
	private FixUpService			fixUpService;
	@Autowired
	private PhaseService			phaseService;
	@Autowired
	private HandyWorkerService		handyWorkerService;
	@Autowired
	private CustomerService			customerService;


	//CARMEN

	@Test
	public void testSavePhase() {
		//CREAR HW
		final HandyWorker handyWorker = this.handyWorkerService.create();
		handyWorker.setName("Ferrete");
		handyWorker.setSurname("Ferrete");
		handyWorker.getUserAccount().setUsername("dogran");
		handyWorker.getUserAccount().setPassword("123456789");
		final HandyWorker saveHandyWorker = this.handyWorkerService.save(handyWorker);
		//CREAR PHASE
		final Phase phase = this.phaseService.create();
		@SuppressWarnings("deprecation")
		final Date startDate2 = new Date(2019, 11, 11);
		@SuppressWarnings("deprecation")
		final Date endDate2 = new Date(2019, 12, 11);
		phase.setStartDate(startDate2);
		phase.setEndDate(endDate2);
		phase.setTitle("titleTest");
		//CREAR CUSTOMER
		final Customer customer = this.customerService.create();
		customer.setName("Alvaro");
		customer.setSurname("alvaro");
		customer.getUserAccount().setUsername("dogran2");
		customer.getUserAccount().setPassword("123456789");
		final Customer saveCustomer = this.customerService.save(customer);
		//AUTENTICAR CUTOMER PARA CREAR FIXUP
		super.authenticate("dogran2");
		final FixUp fixUp = this.fixUpService.create();
		fixUp.setHandyWorker(saveHandyWorker);
		@SuppressWarnings("deprecation")
		final Date startDate = new Date(2019, 11, 11);
		@SuppressWarnings("deprecation")
		final Date endDate = new Date(2019, 12, 11);
		fixUp.setStartDate(startDate);
		fixUp.setEndDate(endDate);
		fixUp.setAddress("AddressTest");
		fixUp.setDescription("DescriptionTest");
		final FixUp saveFixUp = this.fixUpService.save(fixUp);
		super.unauthenticate();
		//AUTENTICAR HW PARA GUARDAR PHASE
		super.authenticate("dogran");
		phase.setFixUp(saveFixUp);
		final Phase savePhase = this.phaseService.save(phase);
		Assert.isTrue(this.phaseService.findAll().contains(savePhase));

	}

	@Test
	public void testDeletePhase() {
		//CREAR HW
		final HandyWorker handyWorker = this.handyWorkerService.create();
		handyWorker.setName("Ferrete");
		handyWorker.setSurname("Ferrete");
		handyWorker.getUserAccount().setUsername("dogran");
		handyWorker.getUserAccount().setPassword("123456789");
		final HandyWorker saveHandyWorker = this.handyWorkerService.save(handyWorker);
		//CREAR PHASE
		final Phase phase = this.phaseService.create();
		@SuppressWarnings("deprecation")
		final Date startDate2 = new Date(2019, 11, 11);
		@SuppressWarnings("deprecation")
		final Date endDate2 = new Date(2019, 12, 11);
		phase.setStartDate(startDate2);
		phase.setEndDate(endDate2);
		phase.setTitle("titleTest");
		//CREAR CUSTOMER
		final Customer customer = this.customerService.create();
		customer.setName("Alvaro");
		customer.setSurname("alvaro");
		customer.getUserAccount().setUsername("dogran2");
		customer.getUserAccount().setPassword("123456789");
		final Customer saveCustomer = this.customerService.save(customer);
		//AUTENTICAR CUTOMER PARA CREAR FIXUP
		super.authenticate("dogran2");
		final FixUp fixUp = this.fixUpService.create();
		fixUp.setHandyWorker(saveHandyWorker);
		@SuppressWarnings("deprecation")
		final Date startDate = new Date(2019, 11, 11);
		@SuppressWarnings("deprecation")
		final Date endDate = new Date(2019, 12, 11);
		fixUp.setStartDate(startDate);
		fixUp.setEndDate(endDate);
		fixUp.setAddress("AddressTest");
		fixUp.setDescription("DescriptionTest");
		final FixUp saveFixUp = this.fixUpService.save(fixUp);
		super.unauthenticate();
		//AUTENTICAR HW PARA GUARDAR PHASE
		super.authenticate("dogran");
		phase.setFixUp(saveFixUp);
		final Phase savePhase = this.phaseService.save(phase);
		Assert.isTrue(this.phaseService.findAll().contains(savePhase));
		this.phaseService.delete(savePhase);
		Assert.isTrue(!this.phaseService.findAll().contains(savePhase));
	}

}
