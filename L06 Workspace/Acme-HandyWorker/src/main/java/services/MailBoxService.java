
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.MailBoxRepository;
import security.LoginService;
import security.UserAccount;
import domain.Administrator;
import domain.MailBox;
import domain.Message;

@Service
@Transactional
public class MailBoxService {

	//Managed repository
	@Autowired
	private MailBoxRepository		mailBoxRepository;

	@Autowired
	private AdministratorService	administratorService;


	public MailBox create() {
		final MailBox result = new MailBox();

		final Collection<Message> messages = new ArrayList<>();
		result.setMessages(messages);
		result.setIsDefault(false);
		return result;
	}

	public MailBox update(final MailBox mailBox) {
		final MailBox oldMailBox = this.mailBoxRepository.findOne(mailBox.getId());
		if (oldMailBox.getName() == "inBox" || oldMailBox.getName() == "outBox" || oldMailBox.getName() == "spamBox" || oldMailBox.getName() == "trashBox")
			Assert.isTrue(oldMailBox.getName() == mailBox.getName());
		final MailBox result = this.mailBoxRepository.save(mailBox);
		return result;
	}

	public MailBox save(final MailBox mailBox) {
		final MailBox result = this.mailBoxRepository.save(mailBox);
		return result;
	}

	public void delete(final MailBox mailBox) {
		Assert.isTrue(mailBox.getIsDefault() == false);
		Assert.notNull(this.mailBoxRepository.findOne(mailBox.getId()), "La fixUp no existe");
		this.mailBoxRepository.delete(mailBox);
	}

	public MailBox findOne(final int id) {
		final MailBox result = this.mailBoxRepository.findOne(id);
		return result;
	}

	public Collection<MailBox> findAll() {
		final Collection<MailBox> result = this.mailBoxRepository.findAll();
		return result;
	}

	public MailBox findOneDefault(final int id) {
		final MailBox result = this.mailBoxRepository.getBoxDefaultId(id);
		return result;
	}

	public Collection<MailBox> findAllDefault() {
		final Collection<MailBox> result = this.mailBoxRepository.getBoxDefault();
		return result;
	}

	public Collection<MailBox> getInbox() {
		return this.mailBoxRepository.getInBox();
	}

	public Collection<MailBox> getAdminInBox() {
		final UserAccount adminUser = LoginService.getPrincipal();
		final Administrator administrator = this.administratorService.findByUserAccount(adminUser.getId());
		final Collection<MailBox> box = this.mailBoxRepository.getAdminInBox(administrator.getId());
		return box;
	}

	//broadcast
	public Collection<MailBox> addMessageCollectionInBpox(final Message message) {
		final Administrator a = this.administratorService.findByUserAccount(LoginService.getPrincipal().getId());
		Assert.notNull(a);
		final Collection<MailBox> result = this.mailBoxRepository.getInBox();

		final Collection<MailBox> inBoxAdmin = this.getAdminInBox();

		result.removeAll(inBoxAdmin);

		for (final MailBox mailBox : result) {
			final Collection<Message> messages = mailBox.getMessages();
			messages.add(message);
			mailBox.setMessages(messages);
		}
		return result;
	}

	public MailBox getInBoxActor(final Integer id) {
		final Collection<MailBox> inBoxCollection = this.mailBoxRepository.getInBoxActor(id);
		final List<MailBox> inBoxList = (List<MailBox>) inBoxCollection;
		final MailBox inBox = inBoxList.get(0);
		return inBox;
	}

	public MailBox getTrashBoxActor(final Integer id) {
		final Collection<MailBox> inBoxCollection = this.mailBoxRepository.getTrashBoxActor(id);
		final List<MailBox> TrashBoxList = (List<MailBox>) inBoxCollection;
		final MailBox trashBox = TrashBoxList.get(0);
		return trashBox;
	}

	public MailBox getSpamBoxActor(final Integer id) {
		final Collection<MailBox> spamBoxCollection = this.mailBoxRepository.getTrashBoxActor(id);
		final List<MailBox> SpamBoxList = (List<MailBox>) spamBoxCollection;
		final MailBox spamBox = SpamBoxList.get(0);
		return spamBox;
	}

	public MailBox getOutBoxActor(final Integer id) {
		final Collection<MailBox> outBoxCollection = this.mailBoxRepository.getOutBoxActor(id);
		final List<MailBox> outBoxList = (List<MailBox>) outBoxCollection;
		final MailBox inBox = outBoxList.get(0);
		return inBox;
	}
}
