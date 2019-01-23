
package converters;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import repositories.MailBoxRepository;
import security.LoginService;
import security.UserAccount;
import services.ActorService;
import domain.Actor;
import domain.MailBox;

@Component
@Transactional
public class StringToMailBoxConverter implements Converter<String, MailBox> {

	@Autowired
	MailBoxRepository	mailBoxRepository;

	@Autowired
	ActorService		actorService;


	@Override
	public MailBox convert(final String text) {
		MailBox result = null;
		int id;

		try {
			if (StringUtils.isEmpty(text)) {
				System.out.println("Error en StringToMailBoxConverter IF: " + text);
				result = null;
			} else
				try {
					id = Integer.valueOf(text);
					result = this.mailBoxRepository.findOne(id);
					System.out.println("Error en StringToMailBoxConverter ELSE: " + result);
				} catch (final Throwable oops) {
					final UserAccount user = LoginService.getPrincipal();
					final Actor a = this.actorService.getActorByUserId(user.getId());

					System.out.println(a);

					final List<MailBox> boxes = (List<MailBox>) this.mailBoxRepository.getBoxWithName(text);

					System.out.println(boxes);

					for (int i = 0; i < boxes.size(); i++)
						if (a.getMailBoxes().contains(boxes.get(i)))
							result = boxes.get(i);

					System.out.println(boxes);

					System.out.println("EL SUPER NEW RESULT");
					System.out.println(result);

				}
		} catch (final Throwable oops) {
			System.out.println("Error en StringToMailBoxConverter CATCH: " + oops);
			throw new IllegalArgumentException(oops);
		}
		System.out.println("result");

		System.out.println(result);
		return result;
	}
}
