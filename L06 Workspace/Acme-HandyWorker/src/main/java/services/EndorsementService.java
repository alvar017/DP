
package services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.EndorsementRepository;
import security.LoginService;
import domain.Customer;
import domain.Endorsable;
import domain.Endorsement;
import domain.HandyWorker;

@Service
@Transactional
public class EndorsementService {

	private final List<String>		positive	= Arrays.asList("bueno", "r�pido", "servicial");
	private final List<String>		negative	= Arrays.asList("malo", "lento", "rastrero");

	@Autowired
	private EndorsementRepository	endorsementRepository;

	@Autowired
	private EndorsableService		endorsableService;

	@Autowired
	private ActorService			actorService;

	@Autowired
	private HandyWorkerService		handyWorkerService;

	@Autowired
	private CustomerService			customerService;


	public Endorsement create() {
		final Endorsement endorsement = new Endorsement();
		return endorsement;
	}

	public Endorsement save(final Endorsement endorsement) {
		final Endorsement endorsementSaved;
		System.out.println("Entro en el save de endorsement");
		Assert.isTrue(endorsement != null, "endorsement.null");
		final Integer userAccountId = LoginService.getPrincipal().getId();
		Assert.notNull(userAccountId, "endorsement.userAccountId.null");
		System.out.println("userAccountId: " + userAccountId);
		final Endorsable endorsable = this.endorsableService.getendorsableByUserAccountId(userAccountId);
		Assert.notNull(endorsable);
		Assert.isTrue(endorsement.getEndorsableSender().equals(endorsable));
		endorsementSaved = this.endorsementRepository.save(endorsement);
		return endorsementSaved;
	}
	public Collection<Endorsement> findAll() {
		return this.endorsementRepository.findAll();
	}

	public Endorsement findOne(final Integer id) {
		return this.endorsementRepository.findOne(id);
	}

	public void delete(final Endorsement endorsement) {
		Assert.isTrue(endorsement != null, "endorsement.null");
		final Integer userAccountId = LoginService.getPrincipal().getId();
		Assert.notNull(userAccountId, "endorsement.userAccountId.null");
		final Customer customer = this.customerService.getCustomerByUserAccountId(userAccountId);
		final HandyWorker handyWorker = this.handyWorkerService.getHandyWorkerByUserAccountId(userAccountId);
		if (customer != null) {
			Assert.isTrue(endorsement.getEndorsableSender().getId() == customer.getId());
			endorsement.getEndorsableSender().getEndorsementSender().remove(endorsement);
			endorsement.getendorsableReceiver().getEndorsementReceiver().remove(endorsement);
			System.out.println("CustomerID: " + customer.getId() + " EndorsableID: " + endorsement.getEndorsableSender().getId());
		} else {
			Assert.isTrue(endorsement.getEndorsableSender().getId() == handyWorker.getId());
			endorsement.getEndorsableSender().getEndorsementSender().remove(endorsement);
			endorsement.getendorsableReceiver().getEndorsementReceiver().remove(endorsement);
			System.out.println("HandyWorkerID: " + handyWorker.getId() + " EndorsableID: " + endorsement.getEndorsableSender().getId());
		}
		this.endorsementRepository.delete(endorsement);
	}

	public Collection<Endorsement> getEndorsementReceiver(final int idEndorsable) {
		final Integer idUserAccount = LoginService.getPrincipal().getId();
		Assert.notNull(idUserAccount, "endorsement.idUserAccountNull");
		final Customer customer = this.customerService.getCustomerByUserAccountId(idUserAccount);
		final HandyWorker handyWorker = this.handyWorkerService.getHandyWorkerByUserAccountId(idUserAccount);
		if (customer != null)
			Assert.isTrue(idEndorsable == customer.getId(), "endorsement.NotEqualsIdEndorsableCustomer");
		else
			Assert.isTrue(idEndorsable == handyWorker.getId(), "endorsement.NotEqualsIdEndorsableHandyWorker");
		return this.endorsementRepository.getEndorsementByReceiver(idEndorsable);
	}

	public Collection<Endorsement> getEndorsementSender(final int idEndorsable) {
		final Integer idUserAccount = LoginService.getPrincipal().getId();
		Assert.notNull(idUserAccount, "endorsement.idUserAccountNull");
		final Customer customer = this.customerService.getCustomerByUserAccountId(idUserAccount);
		final HandyWorker handyWorker = this.handyWorkerService.getHandyWorkerByUserAccountId(idUserAccount);
		if (customer != null)
			Assert.isTrue(idEndorsable == customer.getId(), "endorsement.NotEqualsIdEndorsableCustomer");
		else
			Assert.isTrue(idEndorsable == handyWorker.getId(), "endorsement.NotEqualsIdEndorsableHandyWorker");
		return this.endorsementRepository.getEndorsementBySender(idEndorsable);
	}

	public Endorsement findOne(final int idEndorsement) {
		return this.endorsementRepository.findOne(idEndorsement);
	}

	public void calificateUser(final Endorsement endorsement) {
		Assert.isTrue(endorsement.getendorsableReceiver() != null, "endorsement.endorsableReceiver.null");
		final Endorsable endorsable = endorsement.getendorsableReceiver();
		Assert.isTrue(endorsement.getComments() != null, "endorsement.comments.null");
		final String message = endorsement.getComments();
		//        final List<String> positiveWords = Arrays.asList("bueno", "r�pido", "servicial");
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
		final Double calificacionActual = endorsement.getendorsableReceiver().getCalification();
		final Double nuevaCalificacion = (calificacionActual + res) / 2.;
		Assert.isTrue(nuevaCalificacion > -1. && nuevaCalificacion < 1.);
		endorsable.setCalification(nuevaCalificacion);
	}

}
