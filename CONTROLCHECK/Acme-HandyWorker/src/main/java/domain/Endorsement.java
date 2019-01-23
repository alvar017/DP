
package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Endorsement extends DomainEntity {

	private Date		moment;
	private String		comments;
	////////////////////////////////////
	private Endorsable	endorsableSender;
	private Endorsable	endorsableReceiver;


	@ManyToOne(optional = false)
	public Endorsable getEndorsableSender() {
		return this.endorsableSender;
	}

	public void setEndorsableSender(final Endorsable endorsableSender) {
		this.endorsableSender = endorsableSender;
	}
	@ManyToOne(optional = false)
	public Endorsable getendorsableReceiver() {
		return this.endorsableReceiver;
	}

	public void setendorsableReceiver(final Endorsable endorsableReceiver) {
		this.endorsableReceiver = endorsableReceiver;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm")
	@NotNull
	public Date getMoment() {
		return this.moment;
	}

	public void setMoment(final Date moment) {
		this.moment = moment;
	}

	@NotBlank
	public String getComments() {
		return this.comments;
	}

	public void setComments(final String comments) {
		this.comments = comments;
	}

}
