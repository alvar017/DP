
package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import repositories.FinderRepository;
import domain.Finder;

@Component
@Transactional
public class StringToFinderConverter implements Converter<String, Finder> {

	@Autowired
	FinderRepository	FinderRepository;


	@Override
	public Finder convert(final String text) {
		Finder result;
		int id;

		try {
			if (StringUtils.isEmpty(text)) {
				System.out.println("Error en StringToFinderConverter IF: " + text);
				result = null;
			} else {
				id = Integer.valueOf(text);
				result = this.FinderRepository.findOne(id);
				System.out.println("Error en StringToFinderConverter ELSE: " + result);
			}
		} catch (final Throwable oops) {
			System.out.println("Error en StringToFinderConverter CATCH: " + oops);
			throw new IllegalArgumentException(oops);
		}
		return result;
	}
}
