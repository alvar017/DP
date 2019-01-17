
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import javax.transaction.Transactional;

import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.MessageRepository;
import security.LoginService;
import security.UserAccount;
import domain.Actor;
import domain.Administrator;
import domain.MailBox;
import domain.Message;

@Service
@Transactional
public class MessageService {

	//Managed repository
	@Autowired
	private MessageRepository	messageRepository;

	@Autowired
	MailBoxService				mailBoxService;
	@Autowired
	AdministratorService		administratorService;
	@Autowired
	ActorService				actorService;
	@Autowired
	FixUpService				fixUpService;

	@Autowired
	private CustomerService		customerService;

	//private final List<String>	spamWords	= Arrays.asList("sex", "viagra", "cialis", "ferrete", "one million", "you've been selected", "Nigeria", "queryfonsiponsypaferrete", "sexo", "un millón", "ha sido seleccionado");

	public HashSet<String>		spamWords;


	//Carmen: Método para añadir spam words (adm)
	//	public HashSet<String> newSpamWords(final String newWord) {
	//		this.listSpamWords().add(newWord);
	//		return this.listSpamWords();
	//	}

	//Carmen: Método para mostrar las spam words
	public HashSet<String> listSpamWords() {
		return this.fixUpService.listSpamWords();
	}

	public Message create() {
		final Collection<MailBox> boxes = new ArrayList<>();
		final Message m = new Message();
		m.setMoment(LocalDateTime.now().toDate());
		m.setBody("");
		m.setMailBoxes(boxes);
		return m;
	}

	public Message exchangeMessage(final Message message, final Integer receiverId) {
		//this.checkSuspicious(message);
		final Boolean suspicious = this.checkSuspiciousWithBoolean(message);

		final UserAccount userSender = LoginService.getPrincipal();
		final Actor sender = this.actorService.getActorByUserId(userSender.getId());
		final MailBox outBoxSender = this.mailBoxService.getOutBoxActor(sender.getId());

		if (!outBoxSender.getMessages().contains(message) && !message.getMailBoxes().contains(outBoxSender)) {
			outBoxSender.getMessages().add(message);
			message.getMailBoxes().add(outBoxSender);
		}
		MailBox boxReceiver = null;

		if (suspicious) {
			System.out.println("id del exchangeMessafe");
			System.out.println(receiverId);
			boxReceiver = this.mailBoxService.getSpamBoxActor(receiverId);
			System.out.println(boxReceiver);
			boxReceiver.getMessages().add(message);
			message.getMailBoxes().add(boxReceiver);
		} else {
			boxReceiver = this.mailBoxService.getInBoxActor(receiverId);
			boxReceiver.getMessages().add(message);
			message.getMailBoxes().add(boxReceiver);
		}

		return message;
	}
	public Message sendBroadcast(final Message message) {
		final Administrator a = this.administratorService.findByUserAccount(LoginService.getPrincipal().getId());
		final Collection<MailBox> inBoxAdmin = this.mailBoxService.getAdminInBox();
		final MailBox spamBoxAdmin = this.mailBoxService.getSpamBoxActor(a.getId());
		final MailBox outBoxAdmin = this.mailBoxService.getOutBoxActor(a.getId());

		System.out.println(outBoxAdmin);

		message.getMailBoxes().add(outBoxAdmin);
		outBoxAdmin.getMessages().add(message);

		Assert.notNull(a);
		final Boolean suspicious = this.checkSuspiciousWithBoolean(message);

		if (suspicious) {
			final Collection<MailBox> result = this.mailBoxService.getspamBox();
			result.remove(spamBoxAdmin);
			for (final MailBox mailBox : result) {
				message.getMailBoxes().add(mailBox);
				mailBox.getMessages().add(message);
			}
		} else {
			final Collection<MailBox> result = this.mailBoxService.getInbox();

			result.removeAll(inBoxAdmin);

			for (final MailBox mailBox : result) {
				message.getMailBoxes().add(mailBox);
				mailBox.getMessages().add(message);
			}
		}

		return message;
	}
	public Message delete(final Message message, final Integer mailBoxId) {
		final UserAccount user = LoginService.getPrincipal();
		final Actor a = this.actorService.getActorByUserId(user.getId());

		final Collection<MailBox> boxes = a.getMailBoxes();
		final List<MailBox> boxesList = new ArrayList<>();
		boxesList.addAll(boxes);
		System.out.println("aqui");
		System.out.println(boxesList);
		MailBox trash = null;
		for (int i = 0; i < boxesList.size(); i++)
			if (boxesList.get(i).getName().equals("trashBox")) {
				System.out.println("Entra aqui");
				trash = boxesList.get(i);
			}
		System.out.println("aqui 2");
		System.out.println(trash);

		final MailBox v = this.mailBoxService.findOne(mailBoxId);

		System.out.println(v);

		if (v.getName().equals("trashBox"))
			for (int i = 0; i < boxesList.size(); i++) {
				message.getMailBoxes().remove(boxesList.get(i));
				boxesList.get(i).getMessages().remove(message);

			}
		else {
			System.out.println("falla aqui");
			message.getMailBoxes().remove(v);
			v.getMessages().remove(message);

			if (!trash.getMessages().contains(message)) {
				message.getMailBoxes().add(trash);
				trash.getMessages().add(message);
			}
		}

		if (message.getMailBoxes().size() == 0)
			this.messageRepository.delete(message);

		return message;

	}
	public Message findOne(final int id) {
		return this.messageRepository.findOne(id);
	}

	public Message save(final Message message) {
		return this.messageRepository.save(message);
	}

	private void checkSuspicious(final Message msg) {
		for (final String word : this.spamWords)
			if (msg.getBody().contains(word))
				this.customerService.getCustomerByUserAccountId(LoginService.getPrincipal().getId()).setIsSuspicious(true);
	}

	private Boolean checkSuspiciousWithBoolean(final Message msg) {
		Boolean res = false;
		System.out.println(this.spamWords);
		for (final String word : this.listSpamWords())
			if (msg.getBody().contains(word)) {
				res = true;
				this.actorService.getActorByUserId(LoginService.getPrincipal().getId()).setIsSuspicious(true);
			}
		return res;
	}
}
