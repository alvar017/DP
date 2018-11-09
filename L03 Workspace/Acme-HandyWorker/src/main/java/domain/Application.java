
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.Past;

import org.joda.time.LocalDate;

@Entity
@Access(AccessType.PROPERTY)
public class Application extends DomainEntity {

	private LocalDate			moment;
	private Boolean				state;
	private Money				offered;
	private String				comments;
	/////////////////////////////////////
	private HandyWorker			applier;
	private FixUp				fixUp;
	private Collection<Phase>	workplan;	// workplan = phases;


	@Valid
	@OneToMany
	public Collection<Phase> getWorkplan() {
		return this.workplan;
	}

	public void setWorkplan(final Collection<Phase> workplan) {
		this.workplan = workplan;
	}

	@Past
	public LocalDate getMoment() {
		return this.moment;
	}

	public void setMoment(final LocalDate moment) {
		this.moment = moment;
	}

	public Boolean getState() {
		return this.state;
	}

	public void setState(final Boolean state) {
		this.state = state;
	}

	@ElementCollection
	public Money getOffered() {
		return this.offered;
	}

	public void setOffered(final Money offered) {
		this.offered = offered;
	}

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

}
