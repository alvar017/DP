
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.Valid;

@Entity
@Access(AccessType.PROPERTY)
public class HandyWorker extends Endorsable {

	private String					make;
	///////////////////////////////////
	private Collection<Application>	applications;
	private Collection<Note>		notes;
	private Collection<Tutorial>	tutorials;
	private Collection<Finder>		finders;
	private Collection<FixUp>		fixUps;
	private Curriculum				curriculum;


	@Valid
	@OneToMany(mappedBy = "handyWorker")
	public Collection<FixUp> getFixUps() {
		return this.fixUps;
	}

	public void setFixUps(final Collection<FixUp> fixUps) {
		this.fixUps = fixUps;
	}

	@OneToMany
	@Valid
	public Collection<Note> getNotes() {
		return this.notes;
	}

	public void setNotes(final Collection<Note> notes) {
		this.notes = notes;
	}
	@OneToMany
	@Valid
	public Collection<Tutorial> getTutorials() {
		return this.tutorials;
	}

	public void setTutorials(final Collection<Tutorial> tutorials) {
		this.tutorials = tutorials;
	}

	@OneToMany
	@Valid
	public Collection<Finder> getFinders() {
		return this.finders;
	}

	public void setFinders(final Collection<Finder> finders) {
		this.finders = finders;
	}

	@OneToOne(optional = true)
	public Curriculum getCurriculum() {
		return this.curriculum;
	}

	public void setCurriculum(final Curriculum curriculum) {
		this.curriculum = curriculum;
	}

	@OneToMany(mappedBy = "applier")
	@Valid
	public Collection<Application> getApplications() {
		return this.applications;
	}

	public void setApplications(final Collection<Application> applications) {
		this.applications = applications;
	}

	public String getMake() {
		return this.make;
	}

	public void setMake(final String make) {
		this.make = make;
	}

}
