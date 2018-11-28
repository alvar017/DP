
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.FixUp;

@Repository
public interface FixUpRepository extends JpaRepository<FixUp, Integer> {

	@Query("select f from FixUp f join f.customer fc where fc.id=?1")
	Collection<FixUp> findFixUpsByCustomer(Integer id);

	//73.2 (CARMEN) --> Display the fix-up tasks in his or her finder.
	@Query("select f.fixUps from HandyWorker h join h.finder f where f.id=?1")
	Collection<FixUp> findFixUpsOfFinderByHandyWorker(Integer Fid);
	//(CARMEN)
}
