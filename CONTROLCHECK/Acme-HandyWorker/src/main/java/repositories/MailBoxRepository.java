
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.MailBox;

@Repository
public interface MailBoxRepository extends JpaRepository<MailBox, Integer> {

	//	@Query("select distinct m from Actor a join a.mailboxes m where m.isDefault=true and a.id = ?1")
	//	Collection<MailBox> getCustomMailBox(Integer id);

	@Query("select m from MailBox m where m.name = 'inBox'")
	Collection<MailBox> getInBox();

	@Query("select m from MailBox m where m.name = ?1")
	Collection<MailBox> getBoxWithName(String name);

	@Query("select m from MailBox m where m.name = 'spamBox'")
	Collection<MailBox> getspamBox();

	@Query("select m from Administrator a join a.mailBoxes m where a.id = ?1 and m.name = 'inBox'")
	Collection<MailBox> getAdminInBox(Integer id);

	@Query("select m from Actor a join a.mailBoxes m where a.id = ?1 and m.name = 'inBox'")
	Collection<MailBox> getInBoxActor(Integer id);

	@Query("select m from Actor a join a.mailBoxes m where a.id = ?1 and m.name = 'trashBox'")
	Collection<MailBox> getTrashBoxActor(Integer id);

	@Query("select m from Actor a join a.mailBoxes m where a.id = ?1 and m.name = 'outBox'")
	Collection<MailBox> getOutBoxActor(Integer id);

	@Query("select m from Actor a join a.mailBoxes m where a.id = ?1 and m.name = 'spamBox'")
	Collection<MailBox> getSpamBoxActor(Integer id);

	@Query("select m from MailBox m where m.isDefault = false")
	Collection<MailBox> getBoxDefault();

	@Query("select m from MailBox m where m.isDefault = false and m.id = ?1")
	MailBox getBoxDefaultId(Integer Id);

}
