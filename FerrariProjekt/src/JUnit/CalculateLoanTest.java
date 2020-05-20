package JUnit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import FFL.Rating;
import entities.Car;
import entities.Customer;
import entities.Offer;
import logic.CalculateLoan;

class CalculateLoanTest {
	Customer customerA, customerB, customerC;
	Offer offerAOver, offerAUnder, offerBOver, offerBUnder, offerCOver, offerCUnder, offerMiddle;
	Car car;

	@BeforeEach
	void setUp() throws Exception {	
		car = new Car();
		car.setPrice("1000000");
			
		offerAOver = createOffer(car, "400000", "600000", "40", Rating.A);
		offerAUnder = createOffer(car, "600000", "400000", "20", Rating.A);
		offerBOver = createOffer(car, "400000", "600000", "40", Rating.B);
		offerBUnder = createOffer(car, "600000", "400000", "20", Rating.B);
		offerCOver = createOffer(car, "400000", "600000", "40", Rating.C);
		offerCUnder = createOffer(car, "600000", "400000", "20", Rating.C);
		offerMiddle = createOffer(car, "500000", "500000", "36", Rating.A);
	}
	@Test
	void testLoan() {
		new CalculateLoan(offerAOver);
		assertEquals("7.0", offerAOver.getRate());
		assertEquals(11201.0, offerAOver.getPeriods().get(1).getPaymentDouble(), 1.0);
		assertEquals("0.0", offerAOver.getPeriods().get(offerAOver.getPeriods().size()-1).getNewBalance());
		
		new CalculateLoan(offerAUnder);
		assertEquals("7.0", offerAUnder.getRate());
		assertEquals(31812.0, offerAUnder.getPeriods().get(1).getPaymentDouble(), 1.0);
		assertEquals("0.0", offerAUnder.getPeriods().get(offerAUnder.getPeriods().size()-1).getNewBalance());

		new CalculateLoan(offerBOver);
		assertEquals("8.0", offerBOver.getRate());
		assertEquals(11373.0, offerBOver.getPeriods().get(1).getPaymentDouble(), 1.0);
		assertEquals("0.0", offerBOver.getPeriods().get(offerBOver.getPeriods().size()-1).getNewBalance());
		
		new CalculateLoan(offerBUnder);
		assertEquals("8.0", offerBUnder.getRate());
		assertEquals(32067.0, offerBUnder.getPeriods().get(1).getPaymentDouble(), 1.0);
		assertEquals("0.0", offerBUnder.getPeriods().get(offerBUnder.getPeriods().size()-1).getNewBalance());
		
		new CalculateLoan(offerCOver);
		assertEquals("9.0", offerCOver.getRate());
		assertEquals(11546.0, offerCOver.getPeriods().get(1).getPaymentDouble(), 1.0);
		assertEquals("0.0", offerCOver.getPeriods().get(offerCOver.getPeriods().size()-1).getNewBalance());
		
		new CalculateLoan(offerCUnder);
		assertEquals("9.0", offerCUnder.getRate());
		assertEquals(32321.0, offerCUnder.getPeriods().get(1).getPaymentDouble(), 1.0);
		assertEquals("0.0", offerCUnder.getPeriods().get(offerCUnder.getPeriods().size()-1).getNewBalance());
		
		new CalculateLoan(offerMiddle);
		assertEquals("6.0", offerMiddle.getRate());
		assertEquals(15174.0, offerMiddle.getPeriods().get(1).getPaymentDouble(), 1.0);
		assertEquals("0.0", offerMiddle.getPeriods().get(offerMiddle.getPeriods().size()-1).getNewBalance());
	
	}
	private Offer createOffer(Car car, String loanValue, String downPayment, String numOfTerms, Rating rating ) {
		Offer o = new Offer();
		o.setOfferCustomer(new Customer());
		o.setOfferCar(car);
		o.setLoanValue(loanValue);
		o.setDownPayment(downPayment);
		o.setRate("5");
		o.setNumOfTerms(numOfTerms);
		o.getOfferCustomer().setCreditRating(rating);
		return o;
	}
	
	

}
