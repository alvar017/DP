
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
@Access(AccessType.PROPERTY)
public class Customer extends Endorsable {

	private Collection<FixUp>	fixUps;


	@OneToMany(mappedBy = "customer")
	public Collection<FixUp> getFixUps() {
		return this.fixUps;
	}

	public void setFixUps(final Collection<FixUp> fixUps) {
		this.fixUps = fixUps;
	}

	//	private Collection<Note>	notes;

	//	@Valid
	//	@OneToMany(mappedBy = "customer")
	//	public Collection<Note> getNotes() {
	//		return this.notes;
	//	}
	//
	//	public void setNotes(final Collection<Note> notes) {
	//		this.notes = notes;
	//	}
}
