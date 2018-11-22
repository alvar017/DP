
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.FixUp;

@Repository
public interface FixUpRepository extends JpaRepository<FixUp, Integer> {

}
