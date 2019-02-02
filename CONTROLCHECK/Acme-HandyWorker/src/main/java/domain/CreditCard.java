
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;

@Embeddable
@Access(AccessType.PROPERTY)
public class CreditCard {

	private String	name;
	// cambio 18/12/2018
	private String	brand;
	private Integer	number;
	private Integer	cvv;
	private String	month;
	private Integer	year;


	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getBrand() {
		return this.brand;
	}

	public void setBrand(final String brand) {
		this.brand = brand;
	}

	public Integer getNumber() {
		return this.number;
	}

	public void setNumber(final Integer number) {
		this.number = number;
	}

	public Integer getCvv() {
		return this.cvv;
	}

	public void setCvv(final Integer cvv) {
		this.cvv = cvv;
	}

	public String getMonth() {
		return this.month;
	}

	public void setMonth(final String month) {
		this.month = month;
	}

	public Integer getYear() {
		return this.year;
	}

	public void setYear(final Integer year) {
		this.year = year;
	}

}