package domain;

import java.util.List;

public class Endorsable extends Actor{
	
	private List<Endorsement> endorsements;

	public List<Endorsement> getEndorsements() {
		return endorsements;
	}

	public void setEndorsements(List<Endorsement> endorsements) {
		this.endorsements = endorsements;
	}
	
	

}
