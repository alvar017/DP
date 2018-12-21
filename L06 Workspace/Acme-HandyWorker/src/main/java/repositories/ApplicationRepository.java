
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import domain.Application;

public interface ApplicationRepository extends JpaRepository<Application, Integer> {

	//	//	The ratio of pending applications
	//	@Query("select count(a.state), sum(case when a.state = null then 1 else 0 end) / sum(case when a.state =null or a.state =1 or a.state = 0 then 1 else 0 end)  * 100.0 from Application a")
	//	Double getRatioPending();
	//
	//	//	The ratio of accepted applications.
	//	@Query("select count(a.state), sum(case when a.state = 1 then 1 else 0 end) / sum(case when a.state =null or a.state =1 or a.state = 0 then 1 else 0 end)  * 100.0 from Application a;")
	//	Double getRatioAccepted();
	//
	//	//	The ratio of rejected applications.
	//	@Query("select count(a.state), sum(case when a.state = 0 then 1 else 0 end) / sum(case when a.state =null or a.state =1 or a.state = 0 then 1 else 0 end)  * 100.0 from Application a;")
	//	Double getRatioRejected();
	//
	//	//	The ratio of pending applications that cannot change its status because their time period’s elapsed.
	//	@Query("select count(a.state), sum(case when a.state = null and f.id = a.fixUp and current_timestamp > f.endDate then 1 else 0 end) /count(a)* 100.0 from Application a, FixUp f join a.fixUp f;")
	//	Double getRatioUnmodifiable();

	@Query("select a from Application a join a.fixUp f where f.customer.id=?1")
	Collection<Application> findAllByCustomer(int customer);

	@Query("select a from Application a join a.fixUp f where f.id=?1")
	Collection<Application> findAllByFixUp(int fixUp);

	//	The ratio of pending applications
	@Query("select sum(case when a.state = null then 1 else 0 end) / sum(case when a.state =null or a.state =1 or a.state = 0 then 1 else 0 end)  * 100.0 from Application a")
	Double getRatioPending();

	//	The ratio of accepted applications.
	@Query("select sum(case when a.state = 1 then 1 else 0 end) / sum(case when a.state =null or a.state =1 or a.state = 0 then 1 else 0 end)  * 100.0 from Application a")
	Double getRatioAccepted();

	//	The ratio of rejected applications.
	@Query("select sum(case when a.state = 0 then 1 else 0 end) / sum(case when a.state =null or a.state =1 or a.state = 0 then 1 else 0 end)  * 100.0 from Application a")
	Double getRatioRejected();

	//	The ratio of pending applications that cannot change its status because their time period’s elapsed.
	@Query("select sum(case when a.state = null and f.id = a.fixUp and current_timestamp > f.endDate then 1 else 0 end) /count(a)* 100.0 from Application a, FixUp f join a.fixUp f")
	Double getRatioUnmodifiable();

	@Query("select avg(f.applications.size) from FixUp f")
	Double avgPerFixUp();

	@Query("select sqrt (sum(f.applications.size*f.applications.size)/count(f.applications.size)-(avg(f.applications.size)*avg(f.applications.size))) from FixUp f")
	Double desviationPerFixUp();

	@Query("select min(f.applications.size) from FixUp f")
	Integer minFixUp();

	@Query("select max(f.applications.size) from FixUp f")
	Integer maxFixUp();

	@Query("select max(b.quantity) from Application a join a.offered b")
	Double maxPricePerApplication();

	@Query("select min(b.quantity) from Application a join a.offered b")
	Double minPricePerApplication();

	@Query("select avg(b.quantity) from Application a join a.offered b")
	Double averagePriceApp();

	@Query("select sqrt(sum(b.quantity*b.quantity)/count(b.quantity)-(avg(b.quantity)*avg(b.quantity))) from Application a join a.offered b")
	Double desviationPriceApp();

	@Query("select a from Application a join a.applier h where h.id=?1")
	Collection<Application> findAllByHandyWorker(int handyWorkerId);
}
