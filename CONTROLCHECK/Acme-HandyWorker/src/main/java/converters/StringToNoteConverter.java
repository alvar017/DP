
package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import repositories.NoteRepository;
import domain.Note;

@Component
@Transactional
public class StringToNoteConverter implements Converter<String, Note> {

	@Autowired
	NoteRepository	noteRepository;


	@Override
	public Note convert(final String text) {
		Note result;
		int id;

		try {
			if (StringUtils.isEmpty(text)) {
				System.out.println("Error en StringToNoteConverter IF: " + text);
				result = null;
			} else {
				id = Integer.valueOf(text);
				result = this.noteRepository.findOne(id);
				System.out.println("Error en StringToNoteConverter ELSE: " + result);
			}
		} catch (final Throwable oops) {
			System.out.println("Error en StringToNoteConverter CATCH: " + oops);
			throw new IllegalArgumentException(oops);
		}
		return result;
	}
}
