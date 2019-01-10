
package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import repositories.CategoryRepository;
import domain.Category;

@Component
@Transactional
public class StringToCategoryConverter implements Converter<String, Category> {

	@Autowired
	CategoryRepository	categoryRepository;


	@Override
	public Category convert(final String text) {
		Category result;
		int id;

		try {
			if (StringUtils.isEmpty(text)) {
				System.out.println("Error en StringToCategoryConverter IF: " + text);
				result = null;
			} else {
				id = Integer.valueOf(text);
				result = this.categoryRepository.findOne(id);
				System.out.println("Error en StringToCategoryConverter ELSE: " + result);
			}
		} catch (final Throwable oops) {
			System.out.println("Error en StringToCategoryConverter CATCH: " + oops);
			throw new IllegalArgumentException(oops);
		}
		return result;
	}
}
