
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Endorsement;

@Repository
public interface EndorsementRepository extends JpaRepository<Endorsement, Integer> {

	@Query("select e from Endorsement e where e.endorsableSender.id=?1")
	Collection<Endorsement> getEndorsementBySender(int idCustomer);

	@Query("select e from Endorsement e where e.endorsableReceiver.id=?1")
	Collection<Endorsement> getEndorsementByReceiver(int idCustomer);
}
