package logic;

import java.util.function.Consumer;

import FFL.CreditRator;
import FFL.InterestRate;
import FFL.Rating;
import entities.Offer;

public class APIController {
	public void findRating(String cprNumber, Consumer<Rating> callback) {
		Runnable runner = new Runnable() {
			@Override
			public void run() {
				CreditRator cr = CreditRator.i();
				callback.accept(cr.rate(cprNumber));
			}
		};

		new Thread(runner).start();
	}

	public void findDailyRate(Offer offer) {
		Runnable runner = new Runnable() {
			@Override
			public void run() {
				InterestRate ir = InterestRate.i();
				offer.setRate(Double.toString(ir.todaysRate()));
			}
		};
		new Thread(runner).start();
	}
}
