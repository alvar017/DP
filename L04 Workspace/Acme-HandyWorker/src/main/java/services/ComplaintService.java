
package services;

import java.util.Collection;
import java.util.Date;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ComplaintRepository;
import security.LoginService;
import security.UserAccount;
import domain.Complaint;
import domain.Referee;

@Service
@Transactional
public class ComplaintService {

	@Autowired
	private ComplaintRepository	complaintRepository;
	@Autowired
	private FixUpService		fixUpService;
	@Autowired
	private RefereeService		refereeService;


	public Complaint create() {

		final Complaint res = new Complaint();

		final Date moment = LocalDate.now().toDate();
		final String ticker = this.fixUpService.randomTicker();

		res.setMoment(moment);
		res.setTicker(ticker);

		return res;
	}

	public Complaint save(final Complaint c) {
		Assert.isTrue(c.getFixUp() != null);
		return this.complaintRepository.save(c);
	}

	public Complaint update(final Complaint c) {
		Assert.isTrue(c.getFixUp() != null);
		return this.complaintRepository.save(c);
	}

	public Collection<Complaint> findAll() {
		return this.complaintRepository.findAll();
	}

	public Complaint findOne(final int idComlaint) {
		return this.complaintRepository.findOne(idComlaint);
	}

	public Collection<Complaint> getComplaintWithoutReferee() {
		final UserAccount login = LoginService.getPrincipal();
		Assert.isTrue(this.refereeService.findByUserAccountId(login.getId()) != null);
		return this.complaintRepository.getComplaintWithoutReferee();
	}

	public Complaint setReefereeToAComplaint(final Complaint c) {
		final UserAccount login = LoginService.getPrincipal();
		Assert.isTrue(this.refereeService.findByUserAccountId(login.getId()) != null);
		final Referee referee = this.refereeService.findByUserAccountId(login.getId());
		c.setReferee(referee);
		return this.update(c);
	}

	public Collection<Complaint> getComplaintByReferee(final Integer idReferee) {
		final UserAccount login = LoginService.getPrincipal();
		Assert.isTrue(this.refereeService.findByUserAccountId(login.getId()) != null);
		final Referee referee = this.refereeService.findByUserAccountId(login.getId());
		return this.complaintRepository.getComplaintByReferee(referee.getId());
	}

	//37.3 (CARMEN )--> List and show the complaints regarding the fix-up tasks in which he or shes been in-volved.(test)
	public Collection<Complaint> getAllComplaintsByHandyWorker(final int hw) {
		return this.complaintRepository.getComplaintFixUpByHandyWorker2(hw);
	}
	//CARMEN

	// 35.1 (FRAN)
	public Collection<Complaint> getComplaintsByCustomer(final int id) {

		return this.complaintRepository.findComplaintsByCustomer(id);
	}
	//FRAN

}
