
package domain;

import java.util.ArrayList;
import java.util.List;

public class Sponsor extends Actor {

	private List<Sponsorship>	sponsorships;


	public List<Sponsorship> getSponsorships() {
		return new ArrayList<Sponsorship>(this.sponsorships);
	}

	public void setSponsorships(final List<Sponsorship> sponsorships) {
		this.sponsorships = sponsorships;
	}
}
