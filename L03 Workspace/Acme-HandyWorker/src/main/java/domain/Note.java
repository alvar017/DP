
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.joda.time.LocalDate;

@Entity
@Access(AccessType.PROPERTY)
public class Note extends DomainEntity {

	private LocalDate	moment;
	private String		commentReferee, commentCustomer, commentHandyWorker;
	private Customer	customer;


	@ManyToOne
	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(final Customer customer) {
		this.customer = customer;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public LocalDate getMoment() {
		return this.moment;
	}

	public void setMoment(final LocalDate moment) {
		this.moment = moment;

	}

	public String getCommentReferee() {
		return this.commentReferee;
	}

	public void setCommentReferee(final String commentReferee) {
		this.commentReferee = commentReferee;
	}

	public String getCommentCustomer() {
		return this.commentCustomer;
	}

	public void setCommentCustomer(final String commentCustomer) {
		this.commentCustomer = commentCustomer;
	}

	public String getCommentHandyWorker() {
		return this.commentHandyWorker;
	}

	public void setCommentHandyWorker(final String commentHandyWorker) {
		this.commentHandyWorker = commentHandyWorker;
	}
}
