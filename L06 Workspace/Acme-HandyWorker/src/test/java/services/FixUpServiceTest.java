
package services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import domain.Customer;
import domain.Finder;
import domain.FixUp;
import domain.HandyWorker;

@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class FixUpServiceTest extends AbstractTest {

	//Services under Test

	@Autowired
	private FixUpService			fixUpService;
	@Autowired
	private CustomerService			customerService;
	@Autowired
	private WarrantyService			warrantyService;
	@Autowired
	private AdministratorService	administratorService;
	@Autowired
	private HandyWorkerService		handyWorkerService;
	@Autowired
	private FinderService			finderService;

	SimpleDateFormat				sdf	= new SimpleDateFormat("yyyy/MM/dd HH:mm");


	@Test
	public void testCreateFixUp() {
		final Customer customer = this.customerService.create();
		Assert.isTrue(customer != null);
	}
	@Test
	public void testSaveFixUp() {
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
		Assert.isTrue(this.fixUpService.findAll().contains(saveFixUp));
	}
	@Test
	public void testUpdateFixUp() {
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
		Assert.isTrue(this.fixUpService.findAll().contains(saveFixUp));
		saveFixUp.setAddress("Rodrigo de Triana 14");
		final FixUp saveFixUp2 = this.fixUpService.update(saveFixUp);
		Assert.isTrue(this.fixUpService.findAll().contains(saveFixUp2));
	}

	@Test
	public void testDeleteFixUp() {
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
		Assert.isTrue(this.fixUpService.findAll().contains(saveFixUp));
		this.fixUpService.delete(saveFixUp);
		Assert.isTrue(!this.fixUpService.findAll().contains(saveFixUp));
	}

	@Test
	public void testListingFixUp() {
		final Customer customer = this.customerService.create();
		customer.setName("Alvaro");
		customer.setSurname("alvaro");
		customer.getUserAccount().setUsername("dogran");
		customer.getUserAccount().setPassword("123456789");
		final Customer saveCustomer = this.customerService.save(customer);
		super.authenticate("dogran");
		final FixUp fixUp1 = this.fixUpService.create();
		@SuppressWarnings("deprecation")
		final Date startDate1 = new Date(2019, 11, 11);
		@SuppressWarnings("deprecation")
		final Date endDate1 = new Date(2019, 12, 11);
		fixUp1.setStartDate(startDate1);
		fixUp1.setEndDate(endDate1);
		fixUp1.setAddress("AddressTest");
		fixUp1.setDescription("DescriptionTest");
		final FixUp saveFixUp1 = this.fixUpService.save(fixUp1);

		final FixUp fixUp2 = this.fixUpService.create();
		@SuppressWarnings("deprecation")
		final Date startDate2 = new Date(2019, 11, 11);
		@SuppressWarnings("deprecation")
		final Date endDate2 = new Date(2019, 12, 11);
		fixUp2.setStartDate(startDate2);
		fixUp2.setEndDate(endDate2);
		fixUp2.setAddress("AddressTest");
		fixUp2.setDescription("DescriptionTest");
		final FixUp saveFixUp2 = this.fixUpService.save(fixUp2);

		final FixUp fixUp3 = this.fixUpService.create();
		@SuppressWarnings("deprecation")
		final Date startDate3 = new Date(2019, 11, 11);
		@SuppressWarnings("deprecation")
		final Date endDate3 = new Date(2019, 12, 11);
		fixUp3.setStartDate(startDate3);
		fixUp3.setEndDate(endDate3);
		fixUp3.setAddress("AddressTest");
		fixUp3.setDescription("DescriptionTest");
		final FixUp saveFixUp3 = this.fixUpService.save(fixUp3);

		final FixUp fixUp4 = this.fixUpService.create();
		@SuppressWarnings("deprecation")
		final Date startDate4 = new Date(2019, 11, 11);
		@SuppressWarnings("deprecation")
		final Date endDate4 = new Date(2019, 12, 11);
		fixUp4.setStartDate(startDate4);
		fixUp4.setEndDate(endDate4);
		fixUp4.setAddress("AddressTest");
		fixUp4.setDescription("DescriptionTest");
		final FixUp saveFixUp4 = this.fixUpService.save(fixUp4);

		final int numFixUpAfter = this.fixUpService.listing().size();
		Assert.isTrue(numFixUpAfter == 4);
	}

	@Test
	public void testShowFixUp() {
		final Customer customer = this.customerService.create();
		customer.setName("Alvaro");
		customer.setSurname("alvaro");
		customer.getUserAccount().setUsername("dogran");
		customer.getUserAccount().setPassword("123456789");
		final Customer saveCustomer = this.customerService.save(customer);
		super.authenticate("dogran");
		final FixUp fixUp1 = this.fixUpService.create();
		@SuppressWarnings("deprecation")
		final Date startDate4 = new Date(2019, 11, 11);
		@SuppressWarnings("deprecation")
		final Date endDate4 = new Date(2019, 12, 11);
		fixUp1.setStartDate(startDate4);
		fixUp1.setEndDate(endDate4);
		fixUp1.setAddress("AddressTest");
		fixUp1.setDescription("DescriptionTest");
		final FixUp saveFixUp1 = this.fixUpService.save(fixUp1);
		Assert.isTrue(this.fixUpService.showing(saveFixUp1.getId()).equals(saveFixUp1));
	}

	@Test
	public void test1251() {
		final Administrator adminTest = this.administratorService.createFirstAdmin();
		adminTest.setName("Alvaro");
		adminTest.setSurname("alvaro");
		adminTest.getUserAccount().setUsername("dogran");
		adminTest.getUserAccount().setPassword("123456789");
		final Administrator adminTestSave = this.administratorService.save(adminTest);
		super.authenticate("dogran");
		Assert.isTrue(this.fixUpService.minFixUpHandyWorker() == 2);
		Assert.isTrue(this.fixUpService.maxFixUpHandyWorker() == 2);
		Assert.isTrue(this.fixUpService.avgFixUpPerHandyWorker() == 2.0);
		Assert.isTrue(this.fixUpService.desviationFixUpPerHandyWorker() == 0.0);
	}

	@Test
	public void test1253() {
		final Administrator adminTest = this.administratorService.createFirstAdmin();
		adminTest.setName("Alvaro");
		adminTest.setSurname("alvaro");
		adminTest.getUserAccount().setUsername("dogran");
		adminTest.getUserAccount().setPassword("123456789");
		final Administrator adminTestSave = this.administratorService.save(adminTest);
		super.authenticate("dogran");
		Assert.isTrue(this.fixUpService.minPriceFixUp() == 70.0);
		Assert.isTrue(this.fixUpService.maxPriceFixUp() == 90.0);
		Assert.isTrue(this.fixUpService.avgPriceFixUp() == 82.5);
		Assert.isTrue(this.fixUpService.desviationPriceFixUp() == 8.2915619758885);
	}

	@Test
	public void findAllFixUps() {
		//CREAR HW
		final HandyWorker handyWorker = this.handyWorkerService.create();
		handyWorker.setName("Ferrete");
		handyWorker.setSurname("Ferrete");
		handyWorker.getUserAccount().setUsername("dogran");
		handyWorker.getUserAccount().setPassword("123456789");
		final HandyWorker saveHandyWorker = this.handyWorkerService.save(handyWorker);
		super.authenticate("dogran");
		Assert.isTrue(this.fixUpService.findAllByHW().size() == 4);
	}
	//FERRETE
	@Test
	public void filterAllFixUps() {
		//CREAR HW
		final HandyWorker handyWorker = this.handyWorkerService.create();
		handyWorker.setName("Ferrete");
		handyWorker.setSurname("Ferrete");
		handyWorker.getUserAccount().setUsername("dogran");
		handyWorker.getUserAccount().setPassword("123456789");
		final HandyWorker saveHandyWorker = this.handyWorkerService.save(handyWorker);
		super.authenticate("dogran");
		final Collection<FixUp> fixupsFiltradas = this.fixUpService.filterFixUps("repair", null, null, null, null, null, null);
		System.out.println(fixupsFiltradas);
		Assert.isTrue(fixupsFiltradas.size() == 3);
	}

	//FERRETE
	@Test
	public void testSuspiciousFixUp() {
		final Customer customer = this.customerService.create();
		customer.setName("Alvaro");
		customer.setSurname("alvaro");
		customer.getUserAccount().setUsername("dogran");
		customer.getUserAccount().setPassword("123456789");
		final Customer savedCustomer = this.customerService.save(customer);
		super.authenticate("dogran");
		final FixUp fixUp = this.fixUpService.create();
		@SuppressWarnings("deprecation")
		final Date startDate1 = new Date(2019, 11, 11);
		@SuppressWarnings("deprecation")
		final Date endDate1 = new Date(2019, 12, 11);
		fixUp.setStartDate(startDate1);
		fixUp.setEndDate(endDate1);
		fixUp.setAddress("AddressTest");
		fixUp.setDescription("DescriptionTest");
		fixUp.setDescription("sex");
		final FixUp saveFixUp = this.fixUpService.save(fixUp);
		//Comprobar que el customer está baneado
		Assert.isTrue(this.customerService.findOne(savedCustomer.getId()).getIsSuspicious() == true);
	}

	//73.2 (CARMEN) --> Display the fix-up tasks in his or her finder.
	@Test
	public void allFixUpsByFInder() {

		final Customer customer = this.customerService.create();
		customer.setName("Carmen");
		customer.setSurname("carmen");
		customer.getUserAccount().setUsername("carferben");
		customer.getUserAccount().setPassword("123456789");
		final Customer saveCustomer = this.customerService.save(customer);
		super.authenticate("carferben");

		final FixUp fixUp1 = this.fixUpService.create();
		@SuppressWarnings("deprecation")
		final Date startDate1 = new Date(2019, 11, 11);
		@SuppressWarnings("deprecation")
		final Date endDate1 = new Date(2019, 12, 11);
		fixUp1.setStartDate(startDate1);
		fixUp1.setEndDate(endDate1);
		fixUp1.setAddress("AddressTest");
		fixUp1.setDescription("DescriptionTest");
		final FixUp saveFixUp1 = this.fixUpService.save(fixUp1);

		final FixUp fixUp2 = this.fixUpService.create();
		@SuppressWarnings("deprecation")
		final Date startDate2 = new Date(2019, 11, 11);
		@SuppressWarnings("deprecation")
		final Date endDate2 = new Date(2019, 12, 11);
		fixUp2.setStartDate(startDate2);
		fixUp2.setEndDate(endDate2);
		fixUp2.setAddress("AddressTest");
		fixUp2.setDescription("DescriptionTest");
		final FixUp saveFixUp2 = this.fixUpService.save(fixUp2);

		final FixUp fixUp3 = this.fixUpService.create();
		@SuppressWarnings("deprecation")
		final Date startDate3 = new Date(2019, 11, 11);
		@SuppressWarnings("deprecation")
		final Date endDate3 = new Date(2019, 12, 11);
		fixUp3.setStartDate(startDate3);
		fixUp3.setEndDate(endDate3);
		fixUp3.setAddress("AddressTest");
		fixUp3.setDescription("DescriptionTest");
		final FixUp saveFixUp3 = this.fixUpService.save(fixUp3);

		final FixUp fixUp4 = this.fixUpService.create();
		@SuppressWarnings("deprecation")
		final Date startDate4 = new Date(2019, 11, 11);
		@SuppressWarnings("deprecation")
		final Date endDate4 = new Date(2019, 12, 11);
		fixUp4.setStartDate(startDate4);
		fixUp4.setEndDate(endDate4);
		fixUp4.setAddress("AddressTest");
		fixUp4.setDescription("DescriptionTest");
		final FixUp saveFixUp4 = this.fixUpService.save(fixUp4);

		final Collection<FixUp> fixUps = new ArrayList<>();
		fixUps.add(saveFixUp1);
		fixUps.add(saveFixUp2);
		fixUps.add(saveFixUp3);
		fixUps.add(saveFixUp4);

		final HandyWorker handyWorker = this.handyWorkerService.create();
		handyWorker.setName("CarmenH");
		handyWorker.setSurname("carmenH");
		handyWorker.getUserAccount().setUsername("carferbenH");
		handyWorker.getUserAccount().setPassword("123456789H");
		final HandyWorker saveHandyWorker = this.handyWorkerService.save(handyWorker);
		super.authenticate("carferbenH");

		final Finder finder = this.finderService.create();
		finder.setFixUps(fixUps);
		final Finder finderSave = this.finderService.save(finder);

		saveHandyWorker.setFinder(finderSave);
		final HandyWorker save2HandyWorker = this.handyWorkerService.save(saveHandyWorker);

		//		final HandyWorker handyWorker = this.handyWorkerService.create();
		//		handyWorker.setName("Alvaro");
		//		handyWorker.setSurname("alvaro");
		//		handyWorker.getUserAccount().setUsername("hwAuth");
		//		handyWorker.getUserAccount().setPassword("123456789");
		//		handyWorker.setFinder(finderSave);
		//		final HandyWorker saveHandyWorker = this.handyWorkerService.save(handyWorker);
		//		super.authenticate("hwAuth");

		final Collection<FixUp> resF = this.fixUpService.showAllFixUpbyFinder(finderSave.getId());
		final Integer list = resF.size();
		//        System.out.println(resF);
		Assert.isTrue(resF.size() == 4);
	}
	//(CARMEN)

}
