
package domain;

import java.util.ArrayList;
import java.util.List;

public class HandyWorker extends Endorsable{

	private String				make;
	///////////////////////////////////
	private List<Application>	applications;
	private List<Endorsement>	endorsements;
	private List<Note>			notes;
	private List<Tutorial>		tutorials;
	private List<Finder>		finders;
	private Curriculum			curriculum;


	public List<Note> getNotes() {
		return new ArrayList<>(this.notes);
	}

	public void setNotes(final List<Note> notes) {
		this.notes = notes;
	}

	public List<Tutorial> getTutorials() {
		return new ArrayList<>(this.tutorials);
	}

	public void setTutorials(final List<Tutorial> tutorials) {
		this.tutorials = tutorials;
	}

	public List<Finder> getFinders() {
		return new ArrayList<>(this.finders);
	}

	public void setFinders(final List<Finder> finders) {
		this.finders = finders;
	}

	public Curriculum getCurriculum() {
		return this.curriculum;
	}

	public void setCurriculum(final Curriculum curriculum) {
		this.curriculum = curriculum;
	}

	public List<Endorsement> getEndorsements() {
		return new ArrayList<Endorsement>(this.endorsements);
	}

	public void setEndorsements(final List<Endorsement> endorsements) {
		this.endorsements = endorsements;
	}

	public List<Application> getApplications() {
		return new ArrayList<>(this.applications);
	}

	public void setApplications(final List<Application> applications) {
		this.applications = applications;
	}

	public String getMake() {
		return this.make;
	}

	public void setMake(final String make) {
		this.make = make;
	}

}
