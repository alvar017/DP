
package domain;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.URL;
import org.joda.time.LocalTime;

public class Tutorial extends DomainEntity {

	private String				title, summary, picture;
	private LocalTime			moment;
	/////////////////////////////////////////////
	private List<Sponsorship>	sponsorships;
	private List<Section>		sections;
	private HandyWorker handyWorker;
	
	


	public HandyWorker getHandyWorker() {
		return handyWorker;
	}

	public void setHandyWorker(HandyWorker handyWorker) {
		this.handyWorker = handyWorker;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

	public String getSummary() {
		return this.summary;
	}

	public void setSummary(final String summary) {
		this.summary = summary;
	}

	@URL
	public String getPicture() {
		return this.picture;
	}

	public void setPicture(final String picture) {
		this.picture = picture;
	}

	public LocalTime getMoment() {
		return this.moment;
	}

	public void setMoment(final LocalTime moment) {
		this.moment = moment;
	}

	public List<Sponsorship> getSponsorships() {
		return new ArrayList<Sponsorship>(this.sponsorships);
	}

	public void setSponsorships(final List<Sponsorship> sponsorships) {
		this.sponsorships = sponsorships;
	}

	public List<Section> getSections() {
		return new ArrayList<Section>(this.sections);
	}

	public void setSections(final List<Section> sections) {
		this.sections = sections;
	}

}
