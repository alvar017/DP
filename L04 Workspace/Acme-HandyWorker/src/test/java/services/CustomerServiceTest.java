
package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import domain.Customer;

@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class CustomerServiceTest {

	//Services under Test

	@Autowired
	private CustomerService	customerService;


	@Test
	public void testSaveCustomer() {
		final Customer customer = this.customerService.create();
		customer.setName("Alvaro");
		customer.setSurname("alvaro");
		final Customer saveCustomer = this.customerService.save(customer);
		Assert.isTrue(this.customerService.findAll().contains(saveCustomer));
	}

}
