
package domain;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalTime;

public class Finder extends DomainEntity {

	private String		keyword;
	private LocalTime	date;
	///////////////////////////////
	private List<FixUp>	fixUps;


	public String getKeyword() {
		return this.keyword;
	}

	public void setKeyword(final String keyword) {
		this.keyword = keyword;
	}

	public LocalTime getDate() {
		return this.date;
	}

	public void setDate(final LocalTime date) {
		this.date = date;
	}

	public List<FixUp> getFixUps() {
		return new ArrayList<>(this.fixUps);
	}

	public void setFixUps(final List<FixUp> fixUps) {
		this.fixUps = fixUps;
	}
}
