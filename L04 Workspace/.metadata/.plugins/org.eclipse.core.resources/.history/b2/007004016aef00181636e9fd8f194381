
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

	@Query("select f.fixUp from HandyWorker h join h.finder f where h.id=?1")
	Collection<FixUp> findFixUpsOfFinderByHandyWorker(Integer id);
}
