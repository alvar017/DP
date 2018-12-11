
package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Access(AccessType.PROPERTY)
public class Note extends DomainEntity {

	private Date		moment;
	private String		commentReferee, commentCustomer, commentHandyWorker;
	private Customer	customer;
	private Report		report;
	private HandyWorker	handyWorker;


	@ManyToOne
	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(final Customer customer) {
		this.customer = customer;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getMoment() {
		return this.moment;
	}

	public void setMoment(final Date moment) {
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

	@ManyToOne(optional = true, cascade = CascadeType.ALL)
	public Report getReport() {
		return this.report;
	}

	public void setReport(final Report report) {
		this.report = report;
	}

	@ManyToOne(optional = true)
	public HandyWorker getHandyWorker() {
		return this.handyWorker;
	}

	public void setHandyWorker(final HandyWorker handyWorker) {
		this.handyWorker = handyWorker;
	}
}
