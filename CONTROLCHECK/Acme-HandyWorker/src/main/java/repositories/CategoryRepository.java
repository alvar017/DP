
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import domain.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

	@Query("select c from Category c where c.nameEN = ?1")
	Collection<Category> getCategoryEnglish(String name);

	@Query("select c from Category c where c.nameES = ?1")
	Collection<Category> getCategorySpanish(String name);
}
