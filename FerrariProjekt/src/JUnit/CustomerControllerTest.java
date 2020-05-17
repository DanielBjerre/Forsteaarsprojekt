package JUnit;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import entities.Customer;
import exception.CustomException;
import logic.CustomerController;

class CustomerControllerTest {

	@Test
	void testFindCustomer() {
		CustomerController cc = new CustomerController();
		Customer customer = cc.findCustomer("0123456789");
		assertEquals("testFirstName", customer.getFirstName());
		assertThrows(CustomException.class, () -> {
			cc.findCustomer("9876543210");
		});
	}

}
