
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
import domain.Customer;
import domain.HandyWorker;

@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class AdministratorServiceTest extends AbstractTest {

	@Autowired
	private AdministratorService	administratorService;


	//	@Test
	public void testSaveAdministrator() {
		final Administrator administrator = this.administratorService.create();
		administrator.setName("Ana");
		administrator.setSurname("navarro");
		final Administrator saveAdministrator = this.administratorService.save(administrator);
		Assert.isTrue(this.administratorService.findAll().contains(saveAdministrator));
	}

	@Test
	public void testDashboard() {
		final Administrator administrator = this.administratorService.create();
		administrator.setName("Ana");
		administrator.setSurname("navarro");
		administrator.getUserAccount().setUsername("adminUser");
		administrator.getUserAccount().setPassword("12345678");
		final Administrator saveAdministrator = this.administratorService.save(administrator);
		Assert.isTrue(this.administratorService.findAll().contains(saveAdministrator));
		super.authenticate("adminUser");
		final Integer p1 = this.administratorService.getMinComplaintPerFixUp();
		final Integer p2 = this.administratorService.getMaxComplaintPerFixUp();
		final Double p3 = this.administratorService.getAverageComplaintPerFixUp();
		final Double p4 = this.administratorService.getStandardDeviationFixUp();
		final Integer z1 = this.administratorService.getMaxNotesPerFixUp();
		final Integer z2 = this.administratorService.getMinNotesPerFixUp();
		final Double z3 = this.administratorService.getAvgNotesPerFixUp();
		final Double z4 = this.administratorService.getStandardDeviationNotesPerFixUp();
		final Double z5 = this.administratorService.getRatioFixUpComplaint();
		final Collection<Customer> x1 = this.administratorService.getTopThreeCustomers();
		final Collection<HandyWorker> x2 = this.administratorService.getTopThreeHandyWorker();
		Assert.isTrue((p1 != null && p2 != null && p3 != null && p3 != null && p4 != null && z1 != null && z2 != null && z3 != null && z4 != null && z5 != null && x1 != null && x2 != null) == true);
	}
}
