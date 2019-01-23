
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.context.i18n.LocaleContextHolder;

@Entity
@Access(AccessType.PROPERTY)
public class Category extends DomainEntity {

	private String		nameES;
	private String		nameEN;
	private Category	parentCategory;


	@NotBlank
	public String getNameES() {
		return this.nameES;
	}

	public void setNameES(final String nameES) {
		this.nameES = nameES;
	}

	@NotBlank
	public String getNameEN() {
		return this.nameEN;
	}

	public void setNameEN(final String nameEN) {
		this.nameEN = nameEN;
	}

	@ManyToOne
	public Category getParentCategory() {
		return this.parentCategory;
	}

	public void setParentCategory(final Category parentCategory) {
		this.parentCategory = parentCategory;
	}

	@Override
	public String toString() {
		if (LocaleContextHolder.getLocale().getDisplayLanguage() == "English")
			return this.getNameEN();
		else
			return this.getNameES();
	}

}
