
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

	// DASHBOARD

	//a) The average and standard deviation of the number of published XXXX per XXXX
	//a.1)
	@Query("select ((select count(q) from Quolet q where q.isFinal = 1)/count(q))*1.0 from Quolet q")
	Double getAverage();
	//a.2)
	@Query("select ((select count(q) from Quolet q where q.isFinal = 1)/count(q))*1.0 from Quolet q")
	Double getStandardDeviation();
	//b) The ratio of published XXXX versus total number of XXXX;
	@Query("select ((select count(q) from Quolet q where q.isFinal = 1)/count(q))*1.0 from Quolet q")
	Double getRatioPublished();
	//c) the ratio of unpublished XXXX versus total number of XXXX
	@Query("select ((select count(q) from Quolet q where q.isFinal = 0)/count(q))*1.0 from Quolet q")
	Double getRatioUnpublished();

	// DASHBOARD
}
