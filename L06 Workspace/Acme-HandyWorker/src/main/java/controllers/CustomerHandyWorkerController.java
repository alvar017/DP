
package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CustomerService;
import domain.Customer;
import domain.FixUp;

@Controller
@RequestMapping("/customer/handyWorker")
public class CustomerHandyWorkerController extends AbstractController {

	@Autowired
	CustomerService	customerService;


	@RequestMapping("/show")
	public ModelAndView show(@RequestParam("customerId") final int customerId) {

		final ModelAndView result;
		final Customer customer = this.customerService.findOne(customerId);
		final Collection<FixUp> fixUps = customer.getFixUps();
		result = new ModelAndView();
		result.addObject("customer", customer);
		result.addObject("fixUps", fixUps);
		result.addObject("requestURI", "customer/handyWorker/show.do");
		return result;
	}
}
