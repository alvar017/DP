
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Quolet;

@Repository
public interface QuoletRepository extends JpaRepository<Quolet, Integer> {

	@Query("select q from Quolet q where q.customer.id=?1")
	Collection<Quolet> listQuoletsByCustomer(Integer id);

	@Query("select q from Quolet q where q.isFinal=1")
	Collection<Quolet> listQuoletsFinal();
}
