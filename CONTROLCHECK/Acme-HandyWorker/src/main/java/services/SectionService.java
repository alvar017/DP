
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.SectionRepository;
import security.LoginService;
import security.UserAccount;
import domain.HandyWorker;
import domain.Section;

@Service
@Transactional
public class SectionService {

	@Autowired
	private SectionRepository	sectionRepository;

	@Autowired
	private HandyWorkerService	handyWorkerService;


	public Section create() {
		final UserAccount user = LoginService.getPrincipal();
		final HandyWorker hw = this.handyWorkerService.findByUserAccountId(user.getId());
		Assert.notNull(hw);
		final Section section = new Section();
		section.setText("");
		section.setTitle("");
		return section;
	}

	public Section save(final Section section) {
		final UserAccount user = LoginService.getPrincipal();
		final HandyWorker hw = this.handyWorkerService.findByUserAccountId(user.getId());
		Assert.notNull(hw);
		Assert.isTrue(section.getTutorial().getHandyWorker().equals(hw));
		final Section sectionSaved = this.sectionRepository.save(section);
		return sectionSaved;
	}
	public Collection<Section> findAll() {
		return this.sectionRepository.findAll();
	}

	public Section findOne(final Integer id) {
		return this.sectionRepository.findOne(id);
	}

	public void delete(final Section section) {
		final UserAccount user = LoginService.getPrincipal();
		final HandyWorker hw = this.handyWorkerService.findByUserAccountId(user.getId());
		Assert.notNull(hw);
		Assert.isTrue(section.getTutorial().getHandyWorker().equals(hw));
		this.sectionRepository.delete(section);
	}

}
