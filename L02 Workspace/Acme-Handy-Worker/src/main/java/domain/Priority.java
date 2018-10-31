
package domain;

import javax.validation.constraints.Pattern;

public class Priority {

	private String	value;


	@Pattern(regexp = "HIGH|NEUTRAL|LOW")
	public String getValue() {
		return this.value;
	}

	public void setValue(final String value) {
		this.value = value;
	}

}
