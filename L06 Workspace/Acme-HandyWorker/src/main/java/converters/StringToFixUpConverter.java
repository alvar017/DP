
package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import repositories.FixUpRepository;
import domain.FixUp;

@Component
@Transactional
public class StringToFixUpConverter implements Converter<String, FixUp> {

	@Autowired
	FixUpRepository	fixUpRepository;


	@Override
	public FixUp convert(final String text) {
		FixUp result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = this.fixUpRepository.findOne(id);
			}
		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}
		return result;
	}
}
