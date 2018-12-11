
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.Valid;

@Entity
@Access(AccessType.PROPERTY)
public class MailBox extends DomainEntity {

	private String				name;
	private Boolean				isDefault;
	private Collection<Message>	messages;


	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public Boolean getIsDefault() {
		return this.isDefault;
	}

	public void setIsDefault(final Boolean isDefault) {
		this.isDefault = isDefault;
	}

	@ManyToMany(mappedBy = "mailBoxes")
	@Valid
	public Collection<Message> getMessages() {
		return this.messages;
	}

	public void setMessages(final Collection<Message> messages) {
		this.messages = messages;
	}

}
