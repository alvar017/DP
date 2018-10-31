
package domain;

import java.util.ArrayList;
import java.util.List;

public class Category extends DomainEntity {

	private String			nameES;
	private String nameEN;
	private List<Category>	subCategories;
	
	


	public String getNameES() {
		return nameES;
	}

	public void setNameES(String nameES) {
		this.nameES = nameES;
	}

	public String getNameEN() {
		return nameEN;
	}

	public void setNameEN(String nameEN) {
		this.nameEN = nameEN;
	}


	public List<Category> getSubCategories() {
		return new ArrayList<>(this.subCategories);
	}

	public void setSubCategories(final List<Category> subCategories) {
		this.subCategories = new ArrayList<>(subCategories);
	}

}
