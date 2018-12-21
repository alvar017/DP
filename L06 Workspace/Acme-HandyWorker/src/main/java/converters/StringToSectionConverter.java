
package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import repositories.SectionRepository;
import domain.Section;

@Component
@Transactional
public class StringToSectionConverter implements Converter<String, Section> {

	@Autowired
	SectionRepository	sectionRepo;


	@Override
	public Section convert(final String text) {
		Section result;
		int id;
		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = this.sectionRepo.findOne(id);
			}
		} catch (final Throwable error) {
			throw new IllegalArgumentException(error);
		}
		return result;
	}
}
