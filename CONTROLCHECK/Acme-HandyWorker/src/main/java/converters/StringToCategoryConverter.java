
package converters;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
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
			} else
				try {
					id = Integer.valueOf(text);
					result = this.categoryRepository.findOne(id);
					System.out.println("Error en StringToCategoryConverter ELSE: " + result);
				} catch (final Throwable oops) {
					final String language = LocaleContextHolder.getLocale().getDisplayLanguage();
					if (language == "English") {
						final List<Category> category = (List<Category>) this.categoryRepository.getCategoryEnglish(text);
						System.out.println(category);
						result = category.get(0);
					} else {
						final List<Category> category = (List<Category>) this.categoryRepository.getCategorySpanish(text);
						System.out.println(category);
						result = category.get(0);
					}
				}
		} catch (final Throwable oops) {
			System.out.println("Error en StringToCategoryConverter CATCH: " + oops);
			throw new IllegalArgumentException(oops);
		}
		return result;
	}
}
