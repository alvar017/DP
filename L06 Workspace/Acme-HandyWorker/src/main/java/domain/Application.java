
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
public class Application extends DomainEntity {

	private Date		moment;
	private Boolean		state;
	private Money		offered;
	private String		comments;
	/////////////////////////////////////
	private HandyWorker	applier;
	private FixUp		fixUp;
	//	private Collection<Phase>	workplan;	// workplan = phases;
	private CreditCard	creditCard;


	//	@Valid
	//	@OneToMany
	//	public Collection<Phase> getWorkplan() {
	//		return this.workplan;
	//	}
	//
	//	public void setWorkplan(final Collection<Phase> workplan) {
	//		this.workplan = workplan;
	//	}

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm")
	@NotNull
	public Date getMoment() {
		return this.moment;
	}

	public void setMoment(final Date moment) {
		this.moment = moment;
	}

	public Boolean getState() {
		return this.state;
	}

	public void setState(final Boolean state) {
		this.state = state;
	}

	public Money getOffered() {
		return this.offered;
	}

	public void setOffered(final Money offered) {
		this.offered = offered;
	}

	@NotBlank
	public String getComments() {
		return this.comments;
	}

	public void setComments(final String comments) {
		this.comments = comments;
	}

	@ManyToOne(optional = false)
	public HandyWorker getApplier() {
		return this.applier;
	}

	public void setApplier(final HandyWorker applier) {
		this.applier = applier;
	}

	@ManyToOne(optional = false)
	public FixUp getFixUp() {

		return this.fixUp;
	}

	public void setFixUp(final FixUp fixUp) {
		this.fixUp = fixUp;
	}

	public CreditCard getCreditCard() {
		return this.creditCard;
	}

	public void setCreditCard(final CreditCard creditCard) {
		this.creditCard = creditCard;
	}
	@Override
	public String toString() {
		return "Application[ " + this.getId() + " ]";
	}
}
