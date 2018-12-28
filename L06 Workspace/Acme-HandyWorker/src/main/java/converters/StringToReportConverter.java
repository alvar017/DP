
package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import repositories.ReportRepository;
import domain.Report;

@Component
@Transactional
public class StringToReportConverter implements Converter<String, Report> {

	@Autowired
	ReportRepository	reportRepository;


	@Override
	public Report convert(final String text) {
		Report result;
		int id;

		try {
			if (StringUtils.isEmpty(text)) {
				System.out.println("Error en StringToReportConverter IF: " + text);
				result = null;
			} else {
				id = Integer.valueOf(text);
				result = this.reportRepository.findOne(id);
				System.out.println("Error en StringToReportConverter ELSE: " + result);
			}
		} catch (final Throwable oops) {
			System.out.println("Error en StringToReportConverter CATCH: " + oops);
			throw new IllegalArgumentException(oops);
		}
		return result;
	}
}
