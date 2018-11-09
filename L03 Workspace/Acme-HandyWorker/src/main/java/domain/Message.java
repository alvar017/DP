
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;

import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.LocalDate;

@Entity
@Access(AccessType.PROPERTY)
public class Message extends DomainEntity {

	private String				subject;
	private String				body;
	private LocalDate			moment;
	private Actor				sender;
	private Actor				recipient;
	private Priority			priority;
	private Collection<MailBox>	mailBoxes;


	@ManyToMany
	@Valid
	@NotEmpty
	public Collection<MailBox> getMailBoxes() {
		return this.mailBoxes;
	}

	public void setMailBoxes(final Collection<MailBox> mailBoxes) {
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
	@Temporal(TemporalType.TIMESTAMP)
	public LocalDate getMoment() {
		return this.moment;
	}

	public void setMoment(final LocalDate moment) {
		this.moment = moment;
	}

	@ManyToOne(optional = false)
	public Actor getSender() {
		return this.sender;
	}

	public void setSender(final Actor sender) {
		this.sender = sender;
	}

	@ManyToMany
	public Actor getRecipient() {
		return this.recipient;
	}

	public void setRecipient(final Actor recipient) {
		this.recipient = recipient;
	}
	@ElementCollection
	public Priority getPriority() {
		return this.priority;
	}

	public void setPriority(final Priority priority) {
		this.priority = priority;
	}

}
