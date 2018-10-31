
package domain;

import java.util.ArrayList;
import java.util.List;

public class Referee extends Actor {

	private List<Report>	Reports;


	public List<Report> getReports() {
		return new ArrayList<Report>(this.Reports);
	}

	public void setReports(final List<Report> Reports) {
		this.Reports = Reports;
	}
}
