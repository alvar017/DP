
package domain;

import java.util.List;

public class MailBox extends DomainEntity {

	private String			name;
	private Boolean			isDefault;
	private List<Message>	messages;


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

	public List<Message> getMessages() {
		return this.messages;
	}

	public void setMessages(final List<Message> messages) {
		this.messages = messages;
	}

}
