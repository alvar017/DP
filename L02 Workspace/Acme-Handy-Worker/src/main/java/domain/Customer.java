
package domain;

import java.util.ArrayList;
import java.util.List;

public class Customer extends Endorsable {

	private List<FixUp>			fixUps;
	private List<Note>			notes;
	private List<Endorsement>	endorsements;


	public List<Endorsement> getEndorsements() {
		return new ArrayList<>(this.endorsements);
	}

	public void setEndorsements(final List<Endorsement> endorsements) {
		this.endorsements = endorsements;
	}

	public List<Note> getNotes() {
		return new ArrayList<Note>(this.notes);
	}

	public void setNotes(final List<Note> notes) {
		this.notes = notes;
	}

	public List<FixUp> getFixUps() {
		return this.fixUps;
	}

	public void setFixUps(final List<FixUp> fixUps) {
		this.fixUps = new ArrayList<>(fixUps);
	}

}
