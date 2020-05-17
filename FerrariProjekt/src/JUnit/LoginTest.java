package JUnit;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import database.ReadEmployee;
import entities.Employee;
import exception.CustomException;
import logic.ActiveEmployee;
import logic.Login;

class LoginTest {
	/**
	 * Tests the login from the data package, tests for both a successfull and failed login attempt
	 */
	@Test
	void testLoginData() {
		ReadEmployee ru = new ReadEmployee();
		Employee testEmployee = ru.login("username", "password");
		assertEquals("testFirstName", testEmployee.getFirstName());
		assertThrows(CustomException.class, () -> {
			ru.login("failed", "failed");
		});
	
	}
	/**
	 * Tests the login from the logic package, tests for both a successfull and failed login attempt
	 */
	@Test
	void testLoginLogic() {
		Login login = new Login();
		login.login("username", "password");
		ActiveEmployee ae = ActiveEmployee.getInstance();
		assertEquals(true, ae.getLoggedIn());
		assertThrows(CustomException.class, () -> {
			login.login("failed", "failed");
		});
	}
}
