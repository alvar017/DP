
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.joda.time.LocalDate;

@Entity
@Access(AccessType.PROPERTY)
public class Phase extends DomainEntity {

	private String		title;
	private String		description;
	private LocalDate	startDate;
	private LocalDate	endDate;


	public String getTitle() {
		return this.title;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}
	@Temporal(TemporalType.DATE)
	public LocalDate getStartDate() {
		return this.startDate;
	}

	public void setStartDate(final LocalDate startDate) {
		this.startDate = startDate;
	}
	@Temporal(TemporalType.DATE)
	public LocalDate getEndDate() {
		return this.endDate;
	}

	public void setEndDate(final LocalDate endDate) {
		this.endDate = endDate;
	}

}
