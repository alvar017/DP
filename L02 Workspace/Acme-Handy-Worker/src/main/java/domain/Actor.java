
package domain;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

public class Actor extends DomainEntity {

	private String				name;
	private String				address;
	private String				surname;
	private String				middleName;
	private List<SocialProfile>	socialProfiles;
	private String				email;
	private String				photo;
	private List<MailBox>		mailBoxes;


	@NotBlank
	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(final String address) {
		this.address = address;
	}
	@NotBlank
	public String getSurname() {
		return this.surname;
	}

	public void setSurname(final String surname) {
		this.surname = surname;
	}

	public String getMiddleName() {
		return this.middleName;
	}

	public void setMiddleName(final String middleName) {
		this.middleName = middleName;
	}

	public List<SocialProfile> getSocialProfiles() {
		return this.socialProfiles;
	}

	public void setSocialProfiles(final List<SocialProfile> socialProfiles) {
		this.socialProfiles = new ArrayList<>(socialProfiles);
	}
	@Email
	public String getEmail() {
		return this.email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	public String getPhoto() {
		return this.photo;
	}

	public void setPhoto(final String photo) {
		this.photo = photo;
	}

	public List<MailBox> getMailBoxes() {
		return this.mailBoxes;
	}

	public void setMailBoxes(final List<MailBox> mailBoxes) {
		this.mailBoxes = mailBoxes;
	}

}
