package logic;

import FFL.CreditRator;
import FFL.Rating;

public class APIController {
	public Rating findRating(String cprNumber) {
		CreditRator cr = CreditRator.i();
		return cr.rate(cprNumber);
	}
}
