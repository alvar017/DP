
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Complaint;

@Repository
public interface ComplaintRepository extends JpaRepository<Complaint, Integer> {

	@Query("select c from Complaint c where c.referee = null")
	Collection<Complaint> getComplaintWithoutReferee();

	@Query("select c from Complaint c join c.referee r where r.id = ?1")
	Collection<Complaint> getComplaintRefereeId(Integer id);

	//	@Query("select f.complaint from HandyWorker h join h.finder f where h.id=?1")
	//	Collection<Complaint> getComplaintFixUpByHandyWorker(Integer id);

	@Query("select c from Complaint c join c.fixUp where c.fixUp.handyWorker.id = ?1")
	Collection<Complaint> getComplaintFixUpByHandyWorker2(Integer Hwid);

	@Query("select c from Complaint c where c.referee=null")
	Collection<Complaint> getComplaintWithoutReferee(Integer id);

	@Query("select c from Complaint c where c.referee.id=?1")
	Collection<Complaint> getComplaintByReferee(Integer idReferee);
}
