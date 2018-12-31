
package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import repositories.EndorsableRepository;
import domain.Endorsable;

@Component
@Transactional
public class StringToEndorsableConverter implements Converter<String, Endorsable> {

	@Autowired
	EndorsableRepository	endorsableRepository;


	@Override
	public Endorsable convert(final String text) {
		Endorsable result;
		int id;

		try {
			if (StringUtils.isEmpty(text)) {
				System.out.println("Error en StringToEndorsableConverter IF: " + text);
				result = null;
			} else {
				id = Integer.valueOf(text);
				result = this.endorsableRepository.findOne(id);
				System.out.println("Error en StringToEndorsableConverter ELSE: " + result);
			}
		} catch (final Throwable oops) {
			System.out.println("Error en StringToEndorsableConverter CATCH: " + oops);
			throw new IllegalArgumentException(oops);
		}
		return result;
	}
}
