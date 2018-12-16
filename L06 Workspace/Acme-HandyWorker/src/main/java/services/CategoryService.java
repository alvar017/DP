
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.CategoryRepository;
import security.LoginService;
import domain.Administrator;
import domain.Category;

@Service
@Transactional
public class CategoryService {

	//Managed Repository -------------------	

	@Autowired
	private CategoryRepository		categoryRepository;

	@Autowired
	private AdministratorService	administratorService;


	public Category create() {
		final Administrator a = this.administratorService.findByUserAccount(LoginService.getPrincipal().getId());
		Assert.notNull(a);
		final Category category = new Category();

		return category;

	}

	public Collection<Category> findAll() {
		return this.categoryRepository.findAll();
	}

	public Category findOne(final int id) {
		return this.categoryRepository.findOne(id);
	}
	public Category save(final Category category) {
		final Administrator a = this.administratorService.findByUserAccount(LoginService.getPrincipal().getId());
		Assert.notNull(a);
		return this.categoryRepository.save(category);
	}
	public void delete(final Category category) {
		final Administrator a = this.administratorService.findByUserAccount(LoginService.getPrincipal().getId());
		Assert.notNull(a);
		Assert.notNull(this.categoryRepository.findOne(category.getId()), "La category no existe");
		this.categoryRepository.delete(category);
	}

	public Category update(final Category category) {
		final Administrator a = this.administratorService.findByUserAccount(LoginService.getPrincipal().getId());
		Assert.notNull(a);
		final Category result = this.categoryRepository.save(category);
		return result;
	}

}
