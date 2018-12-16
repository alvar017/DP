
package services;

import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

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
		final Date moment = new Date();

		report.setAttachment(attachment);
		report.setDescription(description);
		report.setIsFinal(isFinal);
		report.setMoment(moment);

		return report;
	}

	//CARMEN
	public Report save(final Report report) {

		final UserAccount login = LoginService.getPrincipal();
		final Referee re = this.refereeService.findByUserAccountId(login.getId());

		Assert.isTrue(report.getComplaint() != null);

		Assert.isTrue(report.getComplaint().getReferee() == re);

		return this.reportRepository.save(report);
	}

	public Report update(final Report report) {
		Assert.isTrue(report.getIsFinal() == false);
		final Report saveReport = this.save(report);
		return saveReport;
	}

	//CARMEN
	//	public Collection<Report> allReportByReferee(final int refereeId) {
	//		return this.reportRepository.getReportReferee(refereeId);
	//	}

	//CARMEN
	public Collection<Report> findAll() {
		return this.reportRepository.findAll();
	}

}
