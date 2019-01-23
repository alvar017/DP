
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Note;

@Repository
public interface NoteRepository extends JpaRepository<Note, Integer> {

	@Query("select r.notes.size from Report r where (r.notes.size = ( select max(r1.notes.size) from  Report r1))")
	Integer getMaxNotesPerFixUp();

	@Query("select r.notes.size from Report r where (r.notes.size = ( select min(r1.notes.size) from  Report r1))))")
	Integer getMinNotesPerFixUp();

	@Query("select avg(r.notes.size) from Report r")
	Double getAvgNotesPerFixUp();

	@Query("select sqrt (sum(r.notes.size*r.notes.size)/count(r.notes.size)-(avg(r.notes.size)*avg(r.notes.size))) from Report r")
	Double getStandardDeviationNotesPerFixUp();

}
