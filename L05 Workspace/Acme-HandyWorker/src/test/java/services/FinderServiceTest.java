
package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Finder;
import domain.HandyWorker;

@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class FinderServiceTest extends AbstractTest {

	@Autowired
	private FinderService		finderService;
	@Autowired
	private HandyWorkerService	handyWorkerService;


	//37.1 (CARMEN)--> HandyWorker: Change the filters of his or her finder.
	@Test
	public void testUpdateFinder() {

		final HandyWorker hw = this.handyWorkerService.create();
		hw.setName("Alvaro");
		hw.setSurname("alvaro");
		hw.getUserAccount().setUsername("dogran");
		hw.getUserAccount().setPassword("123456789");
		final HandyWorker saveHW = this.handyWorkerService.save(hw);
		super.authenticate("dogran");

		final Finder f = this.finderService.findOne(462);

		f.setVersion(2);

		final Finder saveF = this.finderService.update(f);

		Assert.isTrue(this.finderService.findAll().contains(saveF));
	}
	//CARMEN
}
