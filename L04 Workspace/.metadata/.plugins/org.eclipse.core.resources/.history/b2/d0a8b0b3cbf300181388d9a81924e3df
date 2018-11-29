
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.MailBox;

@Repository
public interface MailBoxRepository extends JpaRepository<MailBox, Integer> {

	@Query("select distinct m from Actor a join a.mailboxes m where m.isDefault=true and a.id = ?1")
	Collection<MailBox> getCustomMailBox(Integer id);
}
