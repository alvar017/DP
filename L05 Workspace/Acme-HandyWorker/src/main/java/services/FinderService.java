
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.FinderRepository;
import security.LoginService;
import security.UserAccount;
import domain.Finder;
import domain.FixUp;
import domain.HandyWorker;

@Service
@Transactional
public class FinderService {

	@Autowired
	private FinderRepository	finderRepository;

	@Autowired
	private HandyWorkerService	handyWorkerService;


	//Simple CRUD Methods ------------------

	//CARMEN
	public Finder create() {
		final Finder finder = new Finder();
		return finder;
	}
	//CARMEN

	//CARMEN
	public Collection<Finder> findAll() {
		return this.finderRepository.findAll();
	}
	//CARMEN

	//CARMEN
	public Finder save(final Finder finder) {
		return this.finderRepository.save(finder);
	}
	//CARMEN

	//CARMEN
	public Finder findOne(final int id) {
		return this.finderRepository.findOne(id);
	}
	//CARMEN

	//37.1 (CARMEN)--> HandyWorker: Change the filters of his or her finder.
	public Finder update(final Finder finder) {
		final UserAccount login = LoginService.getPrincipal();
		final HandyWorker hw = this.handyWorkerService.getHandyWorkerByUserAccountId(login.getId());

		final Finder saveFinder = this.finderRepository.save(finder);
		return saveFinder;
	}
	//alvaro

	public Collection<Finder> findFinderOfFixUp(final FixUp fixUp) {
		return this.finderRepository.findFinderOfFixUp(fixUp.getId());
	}
}
