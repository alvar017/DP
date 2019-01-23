
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
import security.LoginService;
import security.UserAccount;
import domain.Administrator;
import domain.HandyWorker;
import domain.MailBox;
import domain.Tutorial;

@Service
@Transactional
public class HandyWorkerService {

	//Managed Repository -------------------	

	@Autowired
	private HandyWorkerRepository	handyWorkerRepository;

	@Autowired
	private MailBoxService			mailBoxService;

	@Autowired
	private AdministratorService	administratorService;


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

		hw.setCalification(0.1);
		hw.setUserAccount(cuenta);

		final Collection<MailBox> boxesDefault = new ArrayList<>();

		final MailBox inBox = this.mailBoxService.create();
		inBox.setName("inBox");
		inBox.setIsDefault(true);
		final MailBox outBox = this.mailBoxService.create();
		outBox.setName("outBox");
		outBox.setIsDefault(true);
		final MailBox spamBox = this.mailBoxService.create();
		spamBox.setName("spamBox");
		spamBox.setIsDefault(true);
		final MailBox trashBox = this.mailBoxService.create();
		trashBox.setName("trashBox");
		trashBox.setIsDefault(true);

		final MailBox inBoxSave = this.mailBoxService.save(inBox);
		final MailBox outBoxSave = this.mailBoxService.save(outBox);
		final MailBox spamBoxSave = this.mailBoxService.save(spamBox);
		final MailBox trashBoxSave = this.mailBoxService.save(trashBox);

		boxesDefault.add(inBoxSave);
		boxesDefault.add(outBoxSave);
		boxesDefault.add(spamBoxSave);
		boxesDefault.add(trashBoxSave);

		hw.setMailBoxes(boxesDefault);

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

	//UN HANDYWORKER MODIFICA SOLO SUS DATOS
	public HandyWorker update(final HandyWorker handyWorker) {
		Assert.isTrue(LoginService.getPrincipal().getId() == handyWorker.getUserAccount().getId()); //UN ACTOR SOLO PUEDE MODIFICICAR SUS DATOS 9.2
		return this.handyWorkerRepository.save(handyWorker);
	}
	//UN HANDYWORKER AUTENTICADO NO PUEDE VOLVER A REGISTRARSE
	public HandyWorker isRegister(final HandyWorker hw) {
		final UserAccount a = hw.getUserAccount();
		Assert.isTrue(a.getUsername() == null);
		return this.handyWorkerRepository.save(hw);
	}

	public HandyWorker getHandyWorkerByUserAccountId(final int userAccountId) {
		HandyWorker res;
		res = this.handyWorkerRepository.findByUserAccountId(userAccountId);
		return res;
	}

	public Collection<HandyWorker> getAllHandyWorkersByCustomer(final int customerId) {
		return this.handyWorkerRepository.getAllHandyWorkersByCustomer(customerId);
	}

	//12510
	public Collection<HandyWorker> betterHandyWorker() {
		final Administrator a = this.administratorService.findByUserAccount(LoginService.getPrincipal().getId());
		Assert.notNull(a);
		final Collection<HandyWorker> result = this.handyWorkerRepository.betterHandyWorker();
		return result;
	}

	public HandyWorker findByUserAccountId(final int id) {
		return this.handyWorkerRepository.findByUserAccountId(id);
	}

	public HandyWorker findByTutorial(final Tutorial tutorial) {
		return tutorial.getHandyWorker();
	}

	public Collection<HandyWorker> getTopThreeHandyWorker() {
		final Collection<HandyWorker> list = this.handyWorkerRepository.getTopThreeHandyWorker();
		if (list.size() < 3)
			return list;
		else {
			final List<HandyWorker> hw = new ArrayList<>(list);
			hw.subList(0, 2);
			return hw;
		}
	}
}
