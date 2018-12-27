
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.ActorRepository;
import security.LoginService;
import security.UserAccount;
import domain.Actor;

@Service
@Transactional
public class ActorService {

	//Managed Repository -------------------	

	@Autowired
	private ActorRepository			actorRepository;

	//Managed Repository -------------------	

	@Autowired
	private AdministratorService	administratorService;


	//Supporting services ------------------

	public Collection<Actor> findAll() {
		return this.actorRepository.findAll();
	}

	public Actor findOne(final int id) {
		final Actor result = this.actorRepository.findOne(id);
		Assert.notNull(result);
		return result;
	}

	// Other M
	public Collection<Actor> findActorsSuspicious() {
		final UserAccount userAccount = LoginService.getPrincipal();
		final int idAdmin = userAccount.getId();
		Assert.isTrue(this.administratorService.getAdministratorByUserAccountId(idAdmin) != null);
		return this.actorRepository.findActorsSuspicious();
	}

	public Collection<Actor> findActorsBanned() {
		final UserAccount userAccount = LoginService.getPrincipal();
		final int idAdmin = userAccount.getId();
		Assert.isTrue(this.administratorService.getAdministratorByUserAccountId(idAdmin) != null);
		return this.actorRepository.findActorsBanned();
	}

	public Actor banActor(final Actor actor) {
		final UserAccount userAccount = LoginService.getPrincipal();
		final int idAdmin = userAccount.getId();
		Assert.isTrue(this.administratorService.getAdministratorByUserAccountId(idAdmin) != null);
		Assert.isTrue(this.findOne(actor.getId()) != null);
		Assert.isTrue(actor.getIsBanned() != true, "Actor is already banned");
		actor.setIsBanned(true);
		final Actor saveActor = this.actorRepository.save(actor);
		return saveActor;
	}

	public Actor unBanActor(final Actor actor) {
		final UserAccount userAccount = LoginService.getPrincipal();
		final int idAdmin = userAccount.getId();
		Assert.isTrue(this.administratorService.getAdministratorByUserAccountId(idAdmin) != null);
		Assert.isTrue(this.findOne(actor.getId()) != null);
		Assert.isTrue(actor.getIsBanned() != false, "Actor is not ban");
		actor.setIsBanned(false);
		final Actor saveActor = this.actorRepository.save(actor);
		return saveActor;
	}

	public Actor getActorByUserId(final Integer id) {
		final Actor a = this.actorRepository.getActorByUserId(id);
		return a;
	}

	public UserAccount getUserByActorId(final Integer id) {
		final UserAccount u = this.actorRepository.getUserByUserActorId(id);
		return u;
	}

	public Actor save(final Actor actor) {
		return this.actorRepository.save(actor);
	}
}
