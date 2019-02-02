
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
	@Query("select avg((select count(q) from FixUp f1 join f1.quolets q where q.isFinal=true)/(select count(f2)from FixUp f2)) from FixUp f")
	Double getAverage();
	//a.2)
	@Query("select sqrt(sum((select count(q1) from FixUp f1 join f1.quolets q1 where f1.id=f.id and q1.isFinal=true)*(select count(q2) from FixUp f2 join f2.quolets q2 where f2.id=f.id and q2.isFinal=true))/(select count(f) from FixUp f)-(((select count(q3) from FixUp f3 join f3.quolets q3 where q3.isFinal=true) / (select count(f) from FixUp f)) * ((select count(q4) from FixUp f4 join f4.quolets q4 where q4.isFinal=true) / (select count(f) from FixUp f)))) from FixUp f")
	Double getStandardDeviation();
	//b) The ratio of published XXXX versus total number of XXXX;
	@Query("select ((select count(q) from Quolet q where q.isFinal = 1)/count(q))*1.0 from Quolet q")
	Double getRatioPublished();
	//c) the ratio of unpublished XXXX versus total number of XXXX
	@Query("select ((select count(q) from Quolet q where q.isFinal = 0)/count(q))*1.0 from Quolet q")
	Double getRatioUnpublished();

	// DASHBOARD
}
