
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.WarrantyRepository;
import domain.Warranty;

@Service
@Transactional
public class WarrantyService {

	//Managed Repository -------------------	

	@Autowired
	private WarrantyRepository	warrantyRepository;


	//Supporting services ------------------

	//Simple CRUD Methods ------------------

	public Warranty create() {

		final Warranty warranty = new Warranty();

		return warranty;

	}

	public Collection<Warranty> findAll() {
		return this.warrantyRepository.findAll();
	}

	public Warranty findOne(final int id) {
		return this.warrantyRepository.findOne(id);
	}
	public Warranty save(final Warranty warranty) {
		return this.warrantyRepository.save(warranty);
	}
	public void delete(final Warranty warranty) {
		Assert.notNull(this.warrantyRepository.findOne(warranty.getId()), "La Warranty no existe");
		this.warrantyRepository.delete(warranty);
	}

	public void update(final Warranty warranty) {
		this.warrantyRepository.save(warranty);
	}

	//Other Methods

}
