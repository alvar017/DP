
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	@Query("select c from Customer c join c.userAccount cua where cua.id=?1")
	Customer findByUserAccountId(int userAccountId);

	//cambiada
	@Query("select c from Customer c join c.fixUps fi where((c.fixUps.size) >=  ((select avg(cus.fixUps.size) from Customer cus) * 1.1)) group by c order by fi.applications.size desc")
	Collection<Customer> betterCustomer();

	@Query("select hwfc from HandyWorker hw join hw.fixUps hwf join hwf.customer hwfc where hw.id=?1")
	Collection<Customer> getAllCustomersByHandyWorkers(int customerId);

	@Query("select cu from Customer cu join cu.fixUps f where f.complaints.size > 0 order by f.complaints.size desc")
	Collection<Customer> getTopThreeCustomers();
}
