
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Referee;

@Repository
public interface RefereeRepository extends JpaRepository<Referee, Integer> {

	@Query("select r from Referee r join r.userAccount cua where cua.id=?1")
	Referee findByUserAccountId(int userAccountId);

	//FRAN CARMEN
	@Query("select rc.referee from Report r join r.complaint rc where r.id = ?1")
	public Referee getRefereeByReport(final Integer idReport);
}
