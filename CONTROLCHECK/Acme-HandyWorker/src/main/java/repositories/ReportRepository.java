
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.Report;

@Repository
public interface ReportRepository extends JpaRepository<Report, Integer> {

	//	@Query("select c.reports from Complaint c join c.referee r where r.id= ?1")
	//	Collection<Report> getReportReferee(Integer id);
}
