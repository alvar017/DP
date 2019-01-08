
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

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
	private FinderRepository		finderRepository;

	@Autowired
	private HandyWorkerService		handyWorkerService;

	@Autowired
	private AdministratorService	administratorService;

	private Integer					result	= 10;


	public Integer numResult(final Integer newResult) {
		Assert.isTrue(!this.checkResult(newResult), "result.bad");
		this.result = newResult;
		return this.result;
	}

	public Integer getResult() {
		return this.result;
	}


	private Integer	time	= 24;


	public Integer newTime(final Integer newTime) {
		Assert.isTrue(!this.checkTime(newTime), "time.bad");
		this.time = newTime;
		return this.time;
	}

	private Boolean checkTime(final Integer time) {
		Boolean res = true;
		if (time >= 1 && time <= 24)
			res = false;
		return res;
	}

	public Integer getTime() {
		return this.time;
	}


	private final Collection<FixUp>	finderFixUp	= new ArrayList<>();


	public Collection<FixUp> fixUpByFinder(final Collection<FixUp> fixUps) {
		this.finderFixUp.addAll(fixUps);
		return this.finderFixUp;
	}

	private Boolean checkResult(final Integer result) {
		Boolean res = true;
		if (result > 0)
			res = false;
		return res;
	}

	public Collection<FixUp> getFinderFixUp() {
		return this.finderFixUp;
	}
	//Simple CRUD Methods ------------------

	public Integer updateResultFinder() {
		final Integer res = this.result;
		return res;
	}

	public Finder create() {

		final Finder finder = new Finder();

		final UserAccount login = LoginService.getPrincipal();
		final HandyWorker hw = this.handyWorkerService.getHandyWorkerByUserAccountId(login.getId());
		Assert.notNull(hw);

		final Date moment = LocalDate.now().toDate();

		finder.setDate(moment);

		System.out.println("paso2");
		return finder;
	}

	public Collection<Finder> findAll() {
		return this.finderRepository.findAll();
	}

	public Finder save(final Finder finder) {
		return this.finderRepository.save(finder);
	}

	public Finder findOne(final int id) {
		return this.finderRepository.findOne(id);
	}

	//HandyWorker: Change the filters of his or her finder.
	public Finder update(final Finder finder) {

		final UserAccount login = LoginService.getPrincipal();
		Assert.isTrue(login != null);
		Assert.isTrue(this.handyWorkerService.getHandyWorkerByUserAccountId(login.getId()) != null);
		final HandyWorker hw = this.handyWorkerService.getHandyWorkerByUserAccountId(login.getId());
		Assert.isTrue(hw.getFinder().equals(finder));
		Assert.isTrue(this.findOne(finder.getId()) != null);
		final Finder saveFinder = this.finderRepository.save(finder);
		return saveFinder;
	}

	public Finder yourFinder() {
		final UserAccount login = LoginService.getPrincipal();
		//		Assert.isTrue(login != null);
		Assert.isTrue(this.handyWorkerService.getHandyWorkerByUserAccountId(login.getId()) != null);
		final HandyWorker hw = this.handyWorkerService.getHandyWorkerByUserAccountId(login.getId());

		return hw.getFinder();
	}

	public Collection<Finder> findFinderOfFixUp(final FixUp fixUp) {
		return this.finderRepository.findFinderOfFixUp(fixUp.getId());
	}
}
