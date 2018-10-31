
package domain;

import org.hibernate.validator.constraints.NotBlank;
import org.joda.time.LocalDate;

public class Note extends DomainEntity {

	private LocalDate	moment;
	private String		comment, extraComment;
	private Customer	customer;


	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(final Customer customer) {
		this.customer = customer;
	}

	public LocalDate getMoment() {
		return this.moment;
	}

	public void setMoment(final LocalDate moment) {
		this.moment = moment;
	}

	@NotBlank
	public String getComment() {
		return this.comment;
	}

	public void setComment(final String comment) {
		this.comment = comment;
	}

	public String getExtraComment() {
		return this.extraComment;
	}

	public void setExtraComment(final String extraComment) {
		this.extraComment = extraComment;
	}
}
