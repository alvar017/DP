
package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import repositories.PhaseRepository;
import domain.Phase;

@Component
@Transactional
public class StringToPhaseConverter implements Converter<String, Phase> {

	@Autowired
	PhaseRepository	phaseRepository;


	@Override
	public Phase convert(final String id) {
		Phase result;
		if (StringUtils.isEmpty(id))
			result = null;
		else
			result = this.phaseRepository.findOne(Integer.valueOf(id));

		return result;
	}

}
