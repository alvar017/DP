
package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Sponsor;

@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class SponsorServiceTest extends AbstractTest {

	//Services under Test

	@Autowired
	private SponsorService	sponsorService;


	@Test
	public void testSaveSponsor() {
		final Sponsor sp = this.sponsorService.create();
		sp.setName("Carmenlita");
		sp.setSurname("papafrita");
		final Sponsor saveSponsor = this.sponsorService.save(sp);
		Assert.isTrue(this.sponsorService.findAll().contains(saveSponsor));
	}
	@Test
	public void testUpdateSponsor() {
		final Sponsor sponsor = this.sponsorService.create();
		//
		sponsor.setName("Ana");
		sponsor.setSurname("navarro");
		sponsor.getUserAccount().setUsername("anita");
		sponsor.getUserAccount().setPassword("123456");
		//

		final Sponsor sponsor2 = this.sponsorService.save(sponsor);
		//

		super.authenticate("anita");
		//

		sponsor2.setName("Anasssss");
		sponsor2.setSurname("navarrosssss");
		final Sponsor saveSponsor2 = this.sponsorService.update(sponsor2);

		Assert.isTrue(this.sponsorService.findAll().contains(saveSponsor2));
	}

	//
	@Test
	public void testRegisterSponsor() {
		final Sponsor ana = this.sponsorService.create();
		ana.setName("Ana");
		ana.setSurname("navarro");
		final Sponsor saveAna = this.sponsorService.isRegister(ana);
		Assert.isTrue(this.sponsorService.findAll().contains(saveAna));

	}
}
