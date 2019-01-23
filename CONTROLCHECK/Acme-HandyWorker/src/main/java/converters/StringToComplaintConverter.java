
package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import repositories.ComplaintRepository;
import domain.Complaint;

@Component
@Transactional
public class StringToComplaintConverter implements Converter<String, Complaint> {

	@Autowired
	ComplaintRepository	complaintRepository;


	@Override
	public Complaint convert(final String text) {
		Complaint result;
		int id;

		try {
			if (StringUtils.isEmpty(text)) {
				System.out.println("Error en StringToComplaintConverter IF: " + text);
				result = null;
			} else {
				id = Integer.valueOf(text);
				result = this.complaintRepository.findOne(id);
				System.out.println("Error en StringToComplaintConverter ELSE: " + result);
			}
		} catch (final Throwable oops) {
			System.out.println("Error en StringToComplaintConverter CATCH: " + oops);
			throw new IllegalArgumentException(oops);
		}
		return result;
	}
}
