
package services;

import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.ReportRepository;
import security.LoginService;
import security.UserAccount;
import domain.Referee;
import domain.Report;

@Service
@Transactional
public class ReportService {

	@Autowired
	private ReportRepository	reportRepository;

	@Autowired
	private ComplaintService	complaintService;

	@Autowired
	private RefereeService		refereeService;


	//CARMEN
	public Report create() {

		final Report report = new Report();

		final String attachment = "";
		final String description = "";
		final Boolean isFinal = false;
		final Date moment = LocalDate.now().toDate();

		return report;
	}
	//CARMEN

	//CARMEN
	public Report save(final Report report) {

		final UserAccount login = LoginService.getPrincipal();
		final Referee re = this.refereeService.findByUserAccountId(login.getId());

		Assert.isTrue(report.getComplaint() != null);

		Assert.isTrue(report.getComplaint().getReferee() == re);

		return this.reportRepository.save(report);
	}
	//CARMEN

	//CARMEN
	public Collection<Report> findAll() {
		return this.reportRepository.findAll();
	}
	//CARMEN
}
