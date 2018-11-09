
package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Access(AccessType.PROPERTY)
public class Endorsement extends DomainEntity {

	private Date		moment;
	private String		comments;
	////////////////////////////////////
	private Endorsable	endorsableSender;
	private Endorsable	endorsableRec;


	@ManyToOne(optional = false)
	public Endorsable getEndorsableSender() {
		return this.endorsableSender;
	}

	public void setEndorsableSender(final Endorsable endorsableSender) {
		this.endorsableSender = endorsableSender;
	}
	@ManyToOne(optional = false)
	public Endorsable getEndorsableRec() {
		return this.endorsableRec;
	}

	public void setEndorsableRec(final Endorsable endorsableRec) {
		this.endorsableRec = endorsableRec;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getMoment() {
		return this.moment;
	}

	public void setMoment(final Date moment) {
		this.moment = moment;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(final String comments) {
		this.comments = comments;
	}

}
