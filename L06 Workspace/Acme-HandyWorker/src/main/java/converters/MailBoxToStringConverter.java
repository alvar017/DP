
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.MailBox;

@Component
@Transactional
public class MailBoxToStringConverter implements Converter<MailBox, String> {

	@Override
	public String convert(final MailBox mailBolx) {
		String result;

		if (mailBolx == null)
			result = null;
		else
			result = String.valueOf(mailBolx.getId());
		System.out.println("CONVERTIDOR mailBolxToStringConverter.java " + result);
		return result;
	}
}
