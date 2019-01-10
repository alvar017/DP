
package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Access(AccessType.PROPERTY)
public class Message extends DomainEntity {

	private String				subject;
	private String				body;
	private Date				moment;
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
	public Date getMoment() {
		return this.moment;
	}

	public void setMoment(final Date moment) {
		this.moment = moment;
	}

	public Priority getPriority() {
		return this.priority;
	}

	public void setPriority(final Priority priority) {
		this.priority = priority;
	}

}
