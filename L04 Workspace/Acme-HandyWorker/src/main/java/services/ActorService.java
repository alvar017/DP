
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
		return this.actorRepository.findActorsSuspicious();
	}

	public Collection<Actor> findActorsBanned() {
		return this.actorRepository.findActorsBanned();
	}

	public Actor banActor(final Actor actor) {
		final UserAccount userAccount = LoginService.getPrincipal();
		final int idAdmin = userAccount.getId();
		System.out.println(idAdmin);
		//		Assert.isTrue(this.administratorService.findOne(idAdmin) != null);
		//		Assert.isTrue(this.findOne(actor.getId()) != null);
		Assert.isTrue(actor.getIsBanned() != true, "Actor is already banned");
		actor.setIsBanned(true);
		final Actor saveActor = this.actorRepository.save(actor);
		return saveActor;
	}

	public Actor unBanActor(final Actor actor) {
		Assert.isTrue(this.findOne(actor.getId()) != null);
		Assert.isTrue(actor.getIsBanned() != false, "Actor is not ban");
		actor.setIsBanned(false);
		final Actor saveActor = this.actorRepository.save(actor);
		return saveActor;
	}
}
