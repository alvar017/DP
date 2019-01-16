
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
import domain.Customer;
import domain.Endorsement;
import domain.FixUp;
import domain.HandyWorker;

@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class EndorsementsServiceTest extends AbstractTest {

	//Services under Test

	@Autowired
	private EndorsementsService	endorsementsService;
	@Autowired
	private CustomerService		customerService;
	@Autowired
	private FixUpService		fixUpService;
	@Autowired
	private HandyWorkerService	handyWorkerService;


	@Test
	public void testSaveEndorsementsByCustomer() {
		//Creo el customer al que se le asignara el endorsement
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
		final HandyWorker hw = this.handyWorkerService.create();
		hw.setName("hw");
		hw.setSurname("hwsur");
		final HandyWorker saveHandyWorker = this.handyWorkerService.save(hw);
		fixUp.setHandyWorker(saveHandyWorker);
		final FixUp saveFixUp = this.fixUpService.save(fixUp);
		final Endorsement endorsement = this.endorsementsService.create();
		endorsement.setMoment(startDate);
		endorsement.setendorsableReceiver(saveHandyWorker);
		endorsement.setComments("comment");
		final Endorsement saveEndorsement = this.endorsementsService.save(endorsement);
		Assert.isTrue(this.endorsementsService.findAll().contains(saveEndorsement));
	}

	@Test
	public void testSaveEndorsementsByHandyWorker() {
		//Creo el customer al que se le asignara el endorsement
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
		final HandyWorker hw = this.handyWorkerService.create();
		hw.setName("hw");
		hw.setSurname("hwsur");
		hw.getUserAccount().setUsername("dogran2");
		hw.getUserAccount().setPassword("dogran2");
		final HandyWorker saveHandyWorker = this.handyWorkerService.save(hw);
		fixUp.setHandyWorker(saveHandyWorker);
		final FixUp saveFixUp = this.fixUpService.save(fixUp);
		super.unauthenticate();
		super.authenticate("dogran2");
		final Endorsement endorsement = this.endorsementsService.create();
		endorsement.setMoment(startDate);
		endorsement.setendorsableReceiver(saveCustomer);
		endorsement.setComments("comment");
		final Endorsement saveEndorsement = this.endorsementsService.save(endorsement);
		Assert.isTrue(this.endorsementsService.findAll().contains(saveEndorsement));
	}

	@Test
	public void testListingEndorsements() {
		//Creo el customer al que se le asignara el endorsement
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
		final HandyWorker hw = this.handyWorkerService.create();
		hw.setName("hw");
		hw.setSurname("hwsur");
		final HandyWorker saveHandyWorker = this.handyWorkerService.save(hw);
		fixUp.setHandyWorker(saveHandyWorker);
		final FixUp saveFixUp = this.fixUpService.save(fixUp);
		final Endorsement endorsement = this.endorsementsService.create();
		endorsement.setMoment(startDate);
		endorsement.setendorsableReceiver(saveHandyWorker);
		endorsement.setComments("comment");
		final Endorsement saveEndorsement = this.endorsementsService.save(endorsement);
		super.unauthenticate();

		// Creo el otro customer que crea una endorsement
		final Customer customer2 = this.customerService.create();
		customer2.setName("Alvaro");
		customer2.setSurname("alvaro");
		customer2.getUserAccount().setUsername("dogran2");
		customer2.getUserAccount().setPassword("123456789");
		final Customer saveCustomer2 = this.customerService.save(customer2);
		super.authenticate("dogran2");
		final FixUp fixUp2 = this.fixUpService.create();
		@SuppressWarnings("deprecation")
		final Date startDate2 = new Date(2019, 11, 11);
		@SuppressWarnings("deprecation")
		final Date endDate2 = new Date(2019, 12, 11);
		fixUp2.setStartDate(startDate2);
		fixUp2.setEndDate(endDate2);
		fixUp2.setAddress("AddressTest");
		fixUp2.setDescription("DescriptionTest");
		final HandyWorker hw2 = this.handyWorkerService.create();
		hw2.setName("hw");
		hw2.setSurname("hwsur");
		final HandyWorker saveHandyWorker2 = this.handyWorkerService.save(hw2);
		fixUp2.setHandyWorker(saveHandyWorker2);
		final FixUp saveFixUp2 = this.fixUpService.save(fixUp2);
		final Endorsement endorsement2 = this.endorsementsService.create();
		endorsement2.setComments("comment");
		endorsement2.setMoment(startDate);
		endorsement2.setendorsableReceiver(saveHandyWorker2);
		final Endorsement saveEndorsement2 = this.endorsementsService.save(endorsement2);
		Assert.isTrue(this.endorsementsService.findAll().size() == 2);
		final Collection<Endorsement> endorses = this.endorsementsService.listing();
		Assert.isTrue(this.endorsementsService.listing().contains(saveEndorsement2));
	}

	@Test
	public void testShowingEndorsements() {
		//Creo el customer al que se le asignara el endorsement
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
		final HandyWorker hw = this.handyWorkerService.create();
		hw.setName("hw");
		hw.setSurname("hwsur");
		final HandyWorker saveHandyWorker = this.handyWorkerService.save(hw);
		fixUp.setHandyWorker(saveHandyWorker);
		final FixUp saveFixUp = this.fixUpService.save(fixUp);
		final Endorsement endorsement = this.endorsementsService.create();
		endorsement.setMoment(startDate);
		endorsement.setendorsableReceiver(saveHandyWorker);
		endorsement.setComments("comment");
		final Endorsement saveEndorsement = this.endorsementsService.save(endorsement);
		super.unauthenticate();

		// Creo el otro customer que crea una endorsement
		final Customer customer2 = this.customerService.create();
		customer2.setName("Alvaro");
		customer2.setSurname("alvaro");
		customer2.getUserAccount().setUsername("dogran2");
		customer2.getUserAccount().setPassword("123456789");
		final Customer saveCustomer2 = this.customerService.save(customer2);
		super.authenticate("dogran2");
		final FixUp fixUp2 = this.fixUpService.create();
		@SuppressWarnings("deprecation")
		final Date startDate2 = new Date(2019, 11, 11);
		@SuppressWarnings("deprecation")
		final Date endDate2 = new Date(2019, 12, 11);
		fixUp2.setStartDate(startDate2);
		fixUp2.setEndDate(endDate2);
		fixUp2.setAddress("AddressTest");
		fixUp2.setDescription("DescriptionTest");
		final HandyWorker hw2 = this.handyWorkerService.create();
		hw2.setName("hw");
		hw2.setSurname("hwsur");
		final HandyWorker saveHandyWorker2 = this.handyWorkerService.save(hw2);
		fixUp2.setHandyWorker(saveHandyWorker2);
		final FixUp saveFixUp2 = this.fixUpService.save(fixUp2);
		final Endorsement endorsement2 = this.endorsementsService.create();
		endorsement2.setComments("comment");
		endorsement2.setMoment(startDate);
		endorsement2.setendorsableReceiver(saveHandyWorker2);
		final Endorsement saveEndorsement2 = this.endorsementsService.save(endorsement2);
		Assert.isTrue(this.endorsementsService.findAll().size() == 2);
		final Collection<Endorsement> endorses = this.endorsementsService.listing();
		Assert.isTrue(this.endorsementsService.listing().contains(saveEndorsement2));
		Assert.isTrue(this.endorsementsService.showing(saveEndorsement2.getId()).equals(saveEndorsement2));
	}

	@Test
	public void testUpdateEndorsements() {
		//Creo el customer al que se le asignara el endorsement
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
		final HandyWorker hw = this.handyWorkerService.create();
		hw.setName("hw");
		hw.setSurname("hwsur");
		final HandyWorker saveHandyWorker = this.handyWorkerService.save(hw);
		fixUp.setHandyWorker(saveHandyWorker);
		final FixUp saveFixUp = this.fixUpService.save(fixUp);
		final Endorsement endorsement = this.endorsementsService.create();
		endorsement.setMoment(startDate);
		endorsement.setendorsableReceiver(saveHandyWorker);
		endorsement.setComments("comment");
		final Endorsement saveEndorsement = this.endorsementsService.save(endorsement);
		saveEndorsement.setComments("ddad");
		final Endorsement updateEndorsement = this.endorsementsService.update(saveEndorsement);
	}

	@Test
	public void testDeleteEndorsements() {
		//Creo el customer al que se le asignara el endorsement
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
		final HandyWorker hw = this.handyWorkerService.create();
		hw.setName("hw");
		hw.setSurname("hwsur");
		final HandyWorker saveHandyWorker = this.handyWorkerService.save(hw);
		fixUp.setHandyWorker(saveHandyWorker);
		final FixUp saveFixUp = this.fixUpService.save(fixUp);
		final Endorsement endorsement = this.endorsementsService.create();
		endorsement.setMoment(startDate);
		endorsement.setendorsableReceiver(saveHandyWorker);
		endorsement.setComments("comment");
		final Endorsement saveEndorsement = this.endorsementsService.save(endorsement);
		Assert.isTrue(this.endorsementsService.findAll().contains(saveEndorsement));
		this.endorsementsService.delete(saveEndorsement);
		Assert.isTrue(!this.endorsementsService.findAll().contains(saveEndorsement));
	}

	@Test
	public void testListingEndorsementsSendToMe() {
		//Creo el customer al que se le asignara el endorsement
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
		final HandyWorker hw = this.handyWorkerService.create();
		hw.setName("hw");
		hw.setSurname("hwsur");
		final HandyWorker saveHandyWorker = this.handyWorkerService.save(hw);
		fixUp.setHandyWorker(saveHandyWorker);
		final FixUp saveFixUp = this.fixUpService.save(fixUp);
		final Endorsement endorsement = this.endorsementsService.create();
		endorsement.setMoment(startDate);
		endorsement.setendorsableReceiver(saveHandyWorker);
		endorsement.setComments("comment");
		final Endorsement saveEndorsement = this.endorsementsService.save(endorsement);
		super.unauthenticate();

		// Creo el otro customer que crea una endorsement
		final Customer customer2 = this.customerService.create();
		customer2.setName("Alvaro");
		customer2.setSurname("alvaro");
		customer2.getUserAccount().setUsername("dogran2");
		customer2.getUserAccount().setPassword("123456789");
		final Customer saveCustomer2 = this.customerService.save(customer2);
		super.authenticate("dogran2");
		final FixUp fixUp2 = this.fixUpService.create();
		@SuppressWarnings("deprecation")
		final Date startDate2 = new Date(2019, 11, 11);
		@SuppressWarnings("deprecation")
		final Date endDate2 = new Date(2019, 12, 11);
		fixUp2.setStartDate(startDate2);
		fixUp2.setEndDate(endDate2);
		fixUp2.setAddress("AddressTest");
		fixUp2.setDescription("DescriptionTest");
		final HandyWorker hw2 = this.handyWorkerService.create();
		hw2.setName("hw");
		hw2.setSurname("hwsur");
		final HandyWorker saveHandyWorker2 = this.handyWorkerService.save(hw2);
		fixUp2.setHandyWorker(saveHandyWorker2);
		final FixUp saveFixUp2 = this.fixUpService.save(fixUp2);
		final Endorsement endorsement2 = this.endorsementsService.create();
		endorsement2.setMoment(startDate);
		endorsement2.setendorsableReceiver(saveHandyWorker2);
		endorsement2.setComments("comment");
		final Endorsement saveEndorsement2 = this.endorsementsService.save(endorsement2);
		Assert.isTrue(this.endorsementsService.findAll().size() == 2);
		final Collection<Endorsement> endorses = this.endorsementsService.listing();
		Assert.isTrue(this.endorsementsService.listing().contains(saveEndorsement2));
	}

	@Test
	public void testCalificate() {
		//Creo el customer al que se le asignara el endorsement
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
		final HandyWorker hw = this.handyWorkerService.create();
		hw.setName("hw");
		hw.setSurname("hwsur");
		final HandyWorker saveHandyWorker = this.handyWorkerService.save(hw);
		fixUp.setHandyWorker(saveHandyWorker);
		final FixUp saveFixUp = this.fixUpService.save(fixUp);
		final Endorsement endorsement = this.endorsementsService.create();
		endorsement.setComments("comment");
		endorsement.setMoment(startDate);
		endorsement.setendorsableReceiver(saveHandyWorker);
		endorsement.setComments("bueno rápido servicial");
		final Endorsement saveEndorsement = this.endorsementsService.save(endorsement);
		Assert.isTrue(this.endorsementsService.findAll().contains(saveEndorsement));
		this.endorsementsService.calificateUser(saveEndorsement);
		System.out.println(saveHandyWorker.getCalification());
	}
}
