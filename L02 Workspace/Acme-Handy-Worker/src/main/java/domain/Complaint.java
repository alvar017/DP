
package domain;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.URL;
import org.joda.time.LocalDate;

public class Complaint extends DomainEntity {

	private String			ticker, description, attachment;
	private LocalDate		moment;
	////////////////////////////////////
	private List<Report>	reports;


	public String getTicker() {
		return this.ticker;
	}

	public void setTicker(final String ticker) {
		this.ticker = ticker;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	@URL
	public String getAttachment() {
		return this.attachment;
	}

	public void setAttachment(final String attachment) {
		this.attachment = attachment;
	}

	public LocalDate getMoment() {
		return this.moment;
	}

	public void setMoment(final LocalDate moment) {
		this.moment = moment;
	}

	public List<Report> getReports() {
		return new ArrayList<Report>(this.reports);
	}

	public void setReports(final List<Report> reports) {
		this.reports = reports;
	}

}
