
package domain;

import java.util.List;

import org.joda.time.LocalDate;

public class Message extends DomainEntity {

	private String		subject;
	private String		body;
	private LocalDate	moment;
	private Actor		sender;
	private Actor		recipient;
	private Priority	priority;
	private List<MailBox> mailBoxes;


	public List<MailBox> getMailBoxes() {
		return mailBoxes;
	}

	public void setMailBoxes(List<MailBox> mailBoxes) {
		this.mailBoxes = mailBoxes;
	}

	public String getSubject() {
		return this.subject;
	}

	public void setSubject(final String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return this.body;
	}

	public void setBody(final String body) {
		this.body = body;
	}

	public LocalDate getMoment() {
		return this.moment;
	}

	public void setMoment(final LocalDate moment) {
		this.moment = moment;
	}

	public Actor getSender() {
		return this.sender;
	}

	public void setSender(final Actor sender) {
		this.sender = sender;
	}

	public Actor getRecipient() {
		return this.recipient;
	}

	public void setRecipient(final Actor recipient) {
		this.recipient = recipient;
	}

	public Priority getPriority() {
		return this.priority;
	}

	public void setPriority(final Priority priority) {
		this.priority = priority;
	}

}
