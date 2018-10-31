
package domain;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Past;

import org.joda.time.LocalDate;

public class Application extends DomainEntity {

	private LocalDate	moment;
	private Boolean		state;
	private Money		offered;
	private String		comments;
	/////////////////////////////////////
	private HandyWorker	applier;
	private FixUp		fixUp;
	private List<Phase>	workplan;	// workplan = phases;


	public List<Phase> getWorkplan() {
		return new ArrayList<Phase>(this.workplan);
	}

	public void setWorkplan(final List<Phase> workplan) {
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

	public HandyWorker getApplier() {
		return this.applier;
	}

	public void setApplier(final HandyWorker applier) {
		this.applier = applier;
	}

	public FixUp getFixUp() {
		return this.fixUp;
	}

	public void setFixUp(final FixUp fixUp) {
		this.fixUp = fixUp;
	}

}
