
package domain;

import org.joda.time.LocalDate;

public class Endorsement extends DomainEntity {

	private LocalDate	moment;
	private String		comments;
	////////////////////////////////////
	private Endorsable endorsableSender;
	private Endorsable endorsableRec;
	
	

	public Endorsable getEndorsableSender() {
		return endorsableSender;
	}

	public void setEndorsableSender(Endorsable endorsableSender) {
		this.endorsableSender = endorsableSender;
	}

	public Endorsable getEndorsableRec() {
		return endorsableRec;
	}

	public void setEndorsableRec(Endorsable endorsableRec) {
		this.endorsableRec = endorsableRec;
	}

	public LocalDate getMoment() {
		return this.moment;
	}

	public void setMoment(final LocalDate moment) {
		this.moment = moment;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(final String comments) {
		this.comments = comments;
	}

	
}
