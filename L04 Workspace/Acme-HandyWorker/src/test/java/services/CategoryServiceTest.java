
package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import domain.Category;

@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class CategoryServiceTest {

	//Services under Test

	@Autowired
	private CategoryService	categoryService;


	@Test
	public void testSaveCustomer() {
		final Category category = this.categoryService.create();
		category.setNameEN("Carpinteria");
		category.setNameES("carpinteria");
		final Category saveCategory = this.categoryService.save(category);
		Assert.isTrue(this.categoryService.findAll().contains(saveCategory));
	}
}
