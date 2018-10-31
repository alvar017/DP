
package domain;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Past;

import org.joda.time.LocalDate;

public class FixUp extends DomainEntity {

	private String				ticker;
	private LocalDate			moment;
	private String				description;
	private String				address;
	private Money				maxPrice;
	private LocalDate			startDate;
	private LocalDate			endDate;
	/////////////////////////////////////////////
	private Warranty			warranty;
	private List<Application>	applications;
	private Category			category;
	private List<Complaint>		complaints;


	public List<Complaint> getComplaints() {
		return complaints;
	}

	public void setComplaints(final List<Complaint> complaints) {
		this.complaints = complaints;
	}

	public String getTicker() {
		return this.ticker;
	}

	public void setTicker(final String ticker) {
		this.ticker = ticker;
	}
	@Past
	public LocalDate getMoment() {
		return this.moment;
	}

	public void setMoment(final LocalDate moment) {
		this.moment = moment;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(final String address) {
		this.address = address;
	}

	public Money getMaxPrice() {
		return this.maxPrice;
	}

	public void setMaxPrice(final Money maxPrice) {
		this.maxPrice = maxPrice;
	}

	public LocalDate getStartDate() {
		return this.startDate;
	}

	public void setStartDate(final LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return this.endDate;
	}

	public void setEndDate(final LocalDate endDate) {
		this.endDate = endDate;
	}

	public Warranty getWarranty() {
		return this.warranty;
	}

	public void setWarranty(final Warranty warranty) {
		this.warranty = warranty;
	}

	public List<Application> getApplications() {
		return new ArrayList<Application>(this.applications);
	}

	public void setApplications(final List<Application> applications) {
		this.applications = applications;
	}

	public Category getCategory() {
		return this.category;
	}

	public void setCategory(final Category category) {
		this.category = category;
	}

}
