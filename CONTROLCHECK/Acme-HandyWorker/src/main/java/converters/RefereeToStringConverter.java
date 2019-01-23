
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Referee;

@Component
@Transactional
public class RefereeToStringConverter implements Converter<Referee, String> {

	@Override
	public String convert(final Referee Referee) {

		String res;

		if (Referee == null)
			res = null;
		else
			res = String.valueOf(Referee.getId());

		return res;
	}

}
