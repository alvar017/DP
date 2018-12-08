
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.TutorialRepository;
import security.LoginService;
import security.UserAccount;
import domain.HandyWorker;
import domain.Tutorial;

@Service
@Transactional
public class TutorialService {

	@Autowired
	private TutorialRepository	tutorialRepository;

	@Autowired
	private HandyWorkerService	handyWorkerService;


	public Tutorial create() {
		final UserAccount user = LoginService.getPrincipal();
		final HandyWorker hw = this.handyWorkerService.findByUserAccountId(user.getId());
		Assert.notNull(hw);
		final Tutorial tutorial = new Tutorial();
		tutorial.setHandyWorker(hw);
		return tutorial;
	}

	public Tutorial save(final Tutorial tutorial) {
		final UserAccount user = LoginService.getPrincipal();
		final HandyWorker hw = this.handyWorkerService.findByUserAccountId(user.getId());
		Assert.notNull(hw);
		Assert.isTrue(tutorial.getHandyWorker().equals(hw));
		final Tutorial tutorialSaved = this.tutorialRepository.save(tutorial);
		return tutorialSaved;
	}
	public Collection<Tutorial> findAll() {
		return this.tutorialRepository.findAll();
	}

	public Tutorial findOne(final Integer id) {
		return this.tutorialRepository.findOne(id);
	}

	public void delete(final Tutorial tutorial) {
		final UserAccount user = LoginService.getPrincipal();
		final HandyWorker hw = this.handyWorkerService.findByUserAccountId(user.getId());
		Assert.notNull(hw);
		Assert.isTrue(tutorial.getHandyWorker().equals(hw));
		this.tutorialRepository.delete(tutorial);
	}

	public Collection<Tutorial> findAllByHW(final HandyWorker hw) {
		return hw.getTutorials();
	}

}
