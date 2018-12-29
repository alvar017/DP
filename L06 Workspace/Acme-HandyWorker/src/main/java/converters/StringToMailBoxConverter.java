
package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import repositories.MailBoxRepository;
import domain.MailBox;

@Component
@Transactional
public class StringToMailBoxConverter implements Converter<String, MailBox> {

	@Autowired
	MailBoxRepository	mailBoxRepository;


	@Override
	public MailBox convert(final String text) {
		MailBox result;
		int id;

		try {
			if (StringUtils.isEmpty(text)) {
				System.out.println("Error en StringToMailBoxConverter IF: " + text);
				result = null;
			} else {
				id = Integer.valueOf(text);
				result = this.mailBoxRepository.findOne(id);
				System.out.println("Error en StringToMailBoxConverter ELSE: " + result);
			}
		} catch (final Throwable oops) {
			System.out.println("Error en StringToMailBoxConverter CATCH: " + oops);
			throw new IllegalArgumentException(oops);
		}
		return result;
	}
}
