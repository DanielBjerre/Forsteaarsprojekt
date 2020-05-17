package JUnit;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import FFL.Rating;
import logic.APIController;

class APIControllerTest {

	@Test
	void testFindRating() {
		APIController ac = new APIController();
		assertEquals(Rating.A, ac.findRating("0000000000"));
		assertEquals(Rating.B, ac.findRating("0000000001"));
		assertEquals(Rating.C, ac.findRating("0000000002"));
		assertEquals(Rating.D, ac.findRating("0000000003"));
	}
}
