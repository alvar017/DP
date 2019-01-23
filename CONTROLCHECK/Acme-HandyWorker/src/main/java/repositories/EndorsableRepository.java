
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Endorsable;

@Repository
public interface EndorsableRepository extends JpaRepository<Endorsable, Integer> {

	@Query("select e from Endorsable e join e.userAccount cua where cua.id=?1")
	Endorsable findByUserAccountId(int userAccountId);
}
