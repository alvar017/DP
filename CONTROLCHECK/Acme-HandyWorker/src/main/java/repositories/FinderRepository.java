
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Finder;

@Repository
public interface FinderRepository extends JpaRepository<Finder, Integer> {

	// alvaro delete fixup
	@Query("select f from Finder f join f.fixUps ff where ff.id=?1")
	Collection<Finder> findFinderOfFixUp(Integer fixUpId);

}
