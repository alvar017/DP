
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {

	@Query("select m from MailBox ma join ma.message m where ma.name=spambox")
	Collection<Message> getMessageSpamBox();

	@Query("select ma.messae from Actor a join a.mailbox ma where ma.name = outbox and a.id=?1")
	Collection<Message> getMessageOutBoxFromActor();
}
