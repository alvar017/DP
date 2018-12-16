
package services;

import java.util.Arrays;
import java.util.Collection;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.HandyWorker;
import domain.Tutorial;

@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class TutorialServiceTest extends AbstractTest {

	@Autowired
	private TutorialService		tutorialService;

	@Autowired
	private HandyWorkerService	handyWorkerService;


	@Test
	public void testCreate() {
		final HandyWorker handyWorker = this.handyWorkerService.create();
		handyWorker.setName("Alvaro");
		handyWorker.setSurname("alvaro");
		handyWorker.getUserAccount().setUsername("dogran");
		handyWorker.getUserAccount().setPassword("123456789");
		final HandyWorker saveHandyWorker = this.handyWorkerService.save(handyWorker);
		super.authenticate("dogran");
		final Tutorial tutorial = this.tutorialService.create();
		Assert.notNull(tutorial);
		Assert.isTrue(tutorial.getHandyWorker().equals(saveHandyWorker));
	}

	@Test
	public void testFindAll() {
		final HandyWorker handyWorker = this.handyWorkerService.create();
		handyWorker.setName("Alvaro");
		handyWorker.setSurname("alvaro");
		handyWorker.getUserAccount().setUsername("dogran");
		handyWorker.getUserAccount().setPassword("123456789");
		final HandyWorker saveHandyWorker = this.handyWorkerService.save(handyWorker);
		super.authenticate("dogran");
		final Tutorial tutorial = this.tutorialService.create();
		final Tutorial tutorialSaved = this.tutorialService.save(tutorial);
		Assert.isTrue(this.tutorialService.findAll().contains(tutorialSaved));
		Assert.isTrue(tutorialSaved.getHandyWorker().equals(saveHandyWorker));
	}

	@Test
	public void testFindOne() {
		final HandyWorker handyWorker = this.handyWorkerService.create();
		handyWorker.setName("Alvaro");
		handyWorker.setSurname("alvaro");
		handyWorker.getUserAccount().setUsername("dogran");
		handyWorker.getUserAccount().setPassword("123456789");
		final HandyWorker saveHandyWorker = this.handyWorkerService.save(handyWorker);
		super.authenticate("dogran");
		final Tutorial tutorial = this.tutorialService.create();
		final Tutorial tutorialSaved = this.tutorialService.save(tutorial);
		Assert.notNull(this.tutorialService.findOne(tutorialSaved.getId()));
		Assert.isTrue(tutorialSaved.getHandyWorker().equals(saveHandyWorker));
	}

	@Test
	public void testDelete() {
		final HandyWorker handyWorker = this.handyWorkerService.create();
		handyWorker.setName("Alvaro");
		handyWorker.setSurname("alvaro");
		handyWorker.getUserAccount().setUsername("dogran");
		handyWorker.getUserAccount().setPassword("123456789");
		final HandyWorker saveHandyWorker = this.handyWorkerService.save(handyWorker);
		super.authenticate("dogran");
		final Tutorial tutorial = this.tutorialService.create();
		final Tutorial tutorialSaved = this.tutorialService.save(tutorial);
		Assert.notNull(this.tutorialService.findOne(tutorialSaved.getId()));
		Assert.isTrue(tutorialSaved.getHandyWorker().equals(saveHandyWorker));
		this.tutorialService.delete(tutorialSaved);
		Assert.isTrue(!this.tutorialService.findAll().contains(tutorialSaved));
	}

	@Test
	public void testFindAllTutorialsByHW() {
		//CREAR HW
		final HandyWorker handyWorker = this.handyWorkerService.create();
		handyWorker.setName("Ferrete");
		handyWorker.setSurname("Ferrete");
		handyWorker.getUserAccount().setUsername("dogran");
		handyWorker.getUserAccount().setPassword("123456789");
		final HandyWorker saveHandyWorker = this.handyWorkerService.save(handyWorker);
		super.authenticate("dogran");
		//CREAR tutorial
		final Tutorial tutorial1 = this.tutorialService.create();
		final Tutorial tutorial2 = this.tutorialService.create();
		final Tutorial tutorial3 = this.tutorialService.create();
		final Tutorial tutorial4 = this.tutorialService.create();
		//ASIGNAR HW
		final Collection<Tutorial> tutorials = Arrays.asList(tutorial1, tutorial2, tutorial3, tutorial4);
		//GUARDAR TUTORIALS
		saveHandyWorker.setTutorials(tutorials);
		System.out.println(this.tutorialService.findAllByHW(saveHandyWorker));
		Assert.isTrue(this.tutorialService.findAllByHW(saveHandyWorker).size() == 4);

	}

}
