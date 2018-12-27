
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.SocialProfileRepository;
import security.LoginService;
import domain.Actor;
import domain.SocialProfile;

@Service
@Transactional
public class SocialProfilelService {

	@Autowired
	private SocialProfileRepository	socialProfileRepository;

	@Autowired
	private ActorService			actorService;


	public SocialProfile create() {
		final SocialProfile socialProfile = new SocialProfile();
		return socialProfile;
	}

	public SocialProfile save(final SocialProfile socialProfile) {
		Assert.isTrue(socialProfile != null);
		final SocialProfile socialProfileSaved = this.socialProfileRepository.save(socialProfile);
		return socialProfileSaved;
	}
	public Collection<SocialProfile> findAll() {
		return this.socialProfileRepository.findAll();
	}

	public SocialProfile findOne(final Integer id) {
		return this.socialProfileRepository.findOne(id);
	}

	public void delete(final SocialProfile socialProfile) {
		final int userAccountId = LoginService.getPrincipal().getId();
		final Actor actor = this.actorService.findOne(userAccountId);
		Assert.isTrue(actor.getSocialProfiles().contains(socialProfile));
		Assert.isTrue(socialProfile != null);
		this.socialProfileRepository.delete(socialProfile);
	}

}
