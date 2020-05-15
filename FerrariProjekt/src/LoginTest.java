import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import database.ReadUser;
import entities.Employee;
import logic.DBFacade;

class LoginTest {
	@Test
	void test() {
		ReadUser ru = new ReadUser();
		Employee testEmployee = ru.login("Test", "Test");
		assertEquals("Test", testEmployee.getFirstName());
	}

}
