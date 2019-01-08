
package services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class WelcomeService {

	String	e		= "Welcome to Acme Handy Worker! Price, quality, and trust in a single place";

	String	s		= "�Bienvenidos a Acme Handy Worker! Precio, calidad y confianza en el mismo sitio!";

	String	system	= "Acme Handy Worker";

	Integer	phone	= 34;

	String	country	= "Espa�a/Spain";

	String	logo	= "https://tinyurl.com/acme-handy-worker-logo";


	public String getLogo() {
		return this.logo;
	}

	public String newLogo(final String newLogo) {
		this.logo = newLogo;
		return this.logo;
	}

	public String newE(final String newE) {
		this.e = newE;
		return this.e;
	}

	public String newS(final String newS) {
		this.s = newS;
		return this.s;
	}

	public String getE() {
		return this.e;
	}

	public String getS() {
		return this.s;
	}

	public String getSystem() {
		return this.system;
	}

	public String newSystem(final String newSystem) {
		this.system = newSystem;
		return this.system;
	}

	public Integer getPhone() {
		return this.phone;
	}

	public Integer newPhone(final Integer phoneNew) {
		this.phone = phoneNew;
		return this.phone;
	}

	public String getCountry() {
		return this.country;
	}

	public void newCountry(final String country) {
		this.country = country;
	}
}