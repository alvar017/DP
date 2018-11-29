
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.HandyWorkerRepository;
import security.Authority;
import security.UserAccount;
import domain.HandyWorker;

@Service
@Transactional
public class HandyWorkerService {

	//Managed Repository -------------------	

	@Autowired
	private HandyWorkerRepository	handyWorkerRepository;


	//Supporting services ------------------

	//Simple CRUD Methods ------------------

	public HandyWorker create() {

		final HandyWorker hw = new HandyWorker();
		final UserAccount cuenta = new UserAccount();
		final List<Authority> autoridades = new ArrayList<>();
		final Authority authority = new Authority();
		authority.setAuthority(Authority.HANDYWORKER);
		autoridades.add(authority);
		cuenta.setAuthorities(autoridades);

		hw.setUserAccount(cuenta);

		return hw;
	}

	public Collection<HandyWorker> findAll() {
		return this.handyWorkerRepository.findAll();
	}

	public HandyWorker findOne(final int id) {
		final HandyWorker result = this.handyWorkerRepository.findOne(id);
		Assert.notNull(result);
		return result;
	}

	public HandyWorker save(final HandyWorker hw) {
		return this.handyWorkerRepository.save(hw);
	}

	public HandyWorker getHandyWorkerByUserAccountId(final int userAccountId) {
		HandyWorker res;
		res = this.handyWorkerRepository.findByUserAccountId(userAccountId);
		return res;
	}

	//	public HandyWorker getHandyWorkerByUserAccountId(final int userAccountId) {
	//		HandyWorker res;
	//		res = this.handyWorkerRepository.findByUserAccountId(userAccountId);
	//		return res;
	//	}

}
