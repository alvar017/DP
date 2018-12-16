
package services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.EndorsementRepository;
import security.LoginService;
import security.UserAccount;
import domain.Customer;
import domain.Endorsable;
import domain.Endorsement;
import domain.HandyWorker;

@Service
@Transactional
public class EndorsementsService {

	private final List<String>		positive	= Arrays.asList("bueno", "rápido", "servicial");
	private final List<String>		negative	= Arrays.asList("malo", "lento", "rastrero");

	//Managed Repository -------------------    
	@Autowired
	private EndorsementRepository	endorsementRepository;
	@Autowired
	private CustomerService			customerService;
	@Autowired
	private HandyWorkerService		handyWorkerService;


	//Supporting services ------------------

	//Simple CRUD Methods ------------------        
	public Endorsement create() {
		// Creo el customer que creará la endorsment
		final UserAccount login = LoginService.getPrincipal();
		if (this.customerService.getCustomerByUserAccountId(login.getId()) != null) {
			Assert.isTrue(this.customerService.getCustomerByUserAccountId(login.getId()) != null);
			final Customer customer = this.customerService.getCustomerByUserAccountId(login.getId());
			final Endorsement endorsement = new Endorsement();
			// El customer será el sender y luego creo la endorsement
			endorsement.setEndorsableSender(customer);
			return endorsement;
		} else {
			Assert.isTrue(this.handyWorkerService.getHandyWorkerByUserAccountId(login.getId()) != null);
			final HandyWorker handyWorker = this.handyWorkerService.getHandyWorkerByUserAccountId(login.getId());
			final Endorsement endorsement = new Endorsement();
			endorsement.setEndorsableSender(handyWorker);
			return endorsement;
		}

	}

	public Endorsement save(final Endorsement toSaveEndorsement) {
		final UserAccount login = LoginService.getPrincipal();
		if (this.customerService.getCustomerByUserAccountId(login.getId()) != null) {
			Assert.isTrue(this.customerService.getCustomerByUserAccountId(login.getId()) != null);
			final Customer customer = this.customerService.getCustomerByUserAccountId(login.getId());
			final Collection<HandyWorker> hws = this.handyWorkerService.getAllHandyWorkersByCustomer(customer.getId());
			final Integer idEndorsable = toSaveEndorsement.getEndorsableRec().getId();
			Assert.isTrue(customer.getId() == toSaveEndorsement.getEndorsableSender().getId());
			Assert.isTrue(hws.contains(toSaveEndorsement.getEndorsableRec()) == true);
			return this.endorsementRepository.save(toSaveEndorsement);
		} else {
			Assert.isTrue(this.handyWorkerService.getHandyWorkerByUserAccountId(login.getId()) != null);
			final HandyWorker handyWorker = this.handyWorkerService.getHandyWorkerByUserAccountId(login.getId());
			final Collection<Customer> customers = this.customerService.getAllCustomersByHandyWorkers(handyWorker.getId());
			final Integer idEndorsable = toSaveEndorsement.getEndorsableRec().getId();
			Assert.isTrue(handyWorker.getId() == toSaveEndorsement.getEndorsableSender().getId());
			Assert.isTrue(customers.contains(toSaveEndorsement.getEndorsableRec()) == true);
		}
		//        if (this.customerService.findOne(toSaveEndorsement.getEndorsableSender().getId()) != null)
		//            Assert.isTrue(this.handyWorkerService.findOne(toSaveEndorsement.getEndorsableRec().getId()) != null);
		//        else {
		//            Assert.isTrue(this.handyWorkerService.findOne(toSaveEndorsement.getEndorsableSender().getId()) != null);
		//            Assert.isTrue(this.customerService.findOne(toSaveEndorsement.getEndorsableRec().getId()) != null);
		//        }
		return this.endorsementRepository.save(toSaveEndorsement);
	}

	public Collection<Endorsement> getEndorsementBySender(final int idCustomer) {
		return this.endorsementRepository.getEndorsementBySender(idCustomer);
	}

	public Collection<Endorsement> getEndorsementByReceiver(final int idCustomer) {
		return this.endorsementRepository.getEndorsementByReceiver(idCustomer);
	}

	public Collection<Endorsement> findAll() {
		return this.endorsementRepository.findAll();
	}

	public Endorsement findOne(final int idEndorsement) {
		return this.endorsementRepository.findOne(idEndorsement);
	}

	public Collection<Endorsement> listing() {
		final UserAccount login = LoginService.getPrincipal();
		Assert.isTrue(this.customerService.getCustomerByUserAccountId(login.getId()) != null);
		final Customer customer = this.customerService.getCustomerByUserAccountId(login.getId());
		return this.endorsementRepository.getEndorsementBySender(customer.getId());
	}

	public Endorsement showing(final int idEndorsement) {
		final UserAccount login = LoginService.getPrincipal();
		Assert.isTrue(this.customerService.getCustomerByUserAccountId(login.getId()) != null);
		final Customer customer = this.customerService.getCustomerByUserAccountId(login.getId());
		Assert.isTrue(this.endorsementRepository.findOne(idEndorsement) != null);
		Assert.isTrue(this.endorsementRepository.findOne(idEndorsement).getEndorsableSender().equals(customer));
		return this.endorsementRepository.findOne(idEndorsement);
	}

	public Endorsement update(final Endorsement endorsement) {
		final UserAccount login = LoginService.getPrincipal();
		Assert.isTrue(this.customerService.getCustomerByUserAccountId(login.getId()) != null);
		final Customer customer = this.customerService.getCustomerByUserAccountId(login.getId());
		Assert.isTrue(this.endorsementRepository.findOne(endorsement.getId()) != null);
		final Endorsement oldEndorsement = this.endorsementRepository.findOne(endorsement.getId());
		Assert.isTrue(oldEndorsement.getEndorsableSender() == customer);
		return this.endorsementRepository.save(endorsement);
	}
	public void delete(final Endorsement endorsement) {
		final UserAccount login = LoginService.getPrincipal();
		Assert.isTrue(this.customerService.getCustomerByUserAccountId(login.getId()) != null);
		final Customer customer = this.customerService.getCustomerByUserAccountId(login.getId());
		Assert.isTrue(this.endorsementRepository.findOne(endorsement.getId()) != null);
		final Endorsement oldEndorsement = this.endorsementRepository.findOne(endorsement.getId());
		Assert.isTrue(oldEndorsement.getEndorsableSender() == customer);
		this.endorsementRepository.delete(endorsement.getId());
	}

	public Collection<Endorsement> getEndorsementSendToMe() {
		final UserAccount login = LoginService.getPrincipal();
		Assert.isTrue(this.customerService.getCustomerByUserAccountId(login.getId()) != null);
		final Customer customer = this.customerService.getCustomerByUserAccountId(login.getId());
		final Collection<Endorsement> endorsementSendToMe = this.endorsementRepository.getEndorsementByReceiver(customer.getId());
		return endorsementSendToMe;
	}

	public void calificateUser(final Endorsement endorsement) {
		Assert.isTrue(endorsement.getEndorsableRec() != null);
		final Endorsable endorsable = endorsement.getEndorsableRec();
		Assert.isTrue(endorsement.getComments() != null);
		final String message = endorsement.getComments();
		//        final List<String> positiveWords = Arrays.asList("bueno", "rápido", "servicial");
		final List<String> positiveWords = this.positive;
		//        final List<String> negativeWords = Arrays.asList("malo", "lento", "rastrero");
		final List<String> negativeWords = this.negative;
		message.trim();
		message.replace(",", "");
		message.replace(".", "");
		final List<String> listaMensaje = Arrays.asList(message.split(" "));
		final Integer sizeOriginal = listaMensaje.size();
		final List<String> positivas = new ArrayList<String>(listaMensaje);
		final List<String> negativas = new ArrayList<String>(listaMensaje);
		positivas.removeAll(positiveWords);
		negativas.removeAll(negativeWords);
		final Double contadorPositivo = (sizeOriginal - positivas.size()) * 1.;
		final Double contadorNegativo = (sizeOriginal - negativas.size()) * 1.;
		final Double totalPalabras = contadorPositivo + contadorNegativo;
		final Double res = ((contadorPositivo) / totalPalabras) - (contadorNegativo / totalPalabras);
		final Double calificacionActual = endorsement.getEndorsableRec().getCalification();
		final Double nuevaCalificacion = (calificacionActual + res) / 2.;
		Assert.isTrue(nuevaCalificacion > -1. && nuevaCalificacion < 1.);
		endorsable.setCalification(nuevaCalificacion);
	}
}
