
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;

@Embeddable
@Access(AccessType.PROPERTY)
public class Money {

	private String	currency;
	private Double	quantity;


	public void add(final Double addition) {
		this.setQuantity(this.getQuantity() + addition);
	}

	public void substract(final Double substraction) {
		this.setQuantity(this.getQuantity() - substraction);
	}

	public String getCurrency() {
		return this.currency;
	}

	public void setCurrency(final String currency) {
		this.currency = currency;
	}

	public Double getQuantity() {
		return this.quantity;
	}

	public void setQuantity(final Double quantity) {
		this.quantity = quantity;
	}

}
