
package domain;

import java.util.ArrayList;
import java.util.List;

public class Warranty extends DomainEntity {

	private String			title;
	private List<String>	terms;
	private List<String>	laws;


	public String getTitle() {
		return this.title;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

	public List<String> getTerms() {
		return new ArrayList<>(this.terms);
	}

	public void setTerms(final List<String> terms) {
		this.terms = new ArrayList<>(terms);
	}

	public List<String> getLaws() {
		return new ArrayList<>(this.laws);
	}

	public void setLaws(final List<String> laws) {
		this.laws = new ArrayList<>(laws);
	}

}
