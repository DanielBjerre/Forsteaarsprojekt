package logic;

import java.util.ArrayList;

import entities.Offer;
import entities.Term;

public class CalculateLoan {
	private Offer offer;
	private Double monthlyRate;
	private Double payment;
	private int numOfTerms;

	public CalculateLoan(Offer offer) {
		this.offer = offer;
		this.numOfTerms = offer.getNumOfTerms();
		calculateRate();
		calculateTermRate();
		calculatePayment();
		calculateTerms();

	}

	private void calculateRate() {
		Double rate = offer.getBankRate();
		if (offer.getDownPaymentDouble() < (offer.getOfferCar().getPriceDouble() / 2)) {
			rate += 1;
		}
		switch (offer.getOfferCustomer().getCreditRating()) {
		case A:
			rate += 1;
			break;
		case B:
			rate += 2;
			break;
		case C:
			rate += 3;
			break;
		case D:
			break;
		}
		if (offer.getNumOfTerms() > 36) {
			rate += 1;
		}
		offer.setRate(rate.toString());
	}

	private void calculateTermRate() {
		monthlyRate = Math.pow(1 + (offer.getRateDouble() / 100), 1.0 / 12) - 1;
	}

	private void calculatePayment() {
		double potens = (numOfTerms - (numOfTerms * 2));
		double lower = 1 - Math.pow((1.0 + monthlyRate), potens);
		payment = (offer.getLoanValueDouble()) * (monthlyRate / lower);
	}

	private void calculateTerms() {
		double previousBalance = offer.getLoanValueDouble();
		this.offer.setPeriods(new ArrayList<Term>());
		for (int i = 1; i <= numOfTerms; i++) {
			double interest = previousBalance * monthlyRate;
			double termPayment;
			if (i != numOfTerms) {
				termPayment = payment;
			} else {
				termPayment = previousBalance + interest;
			}
			double principal = termPayment - interest;
			double newBalance = previousBalance - principal;

			Term t = new Term();
			t.setTermNumber(i);
			t.setPreviousBalance(TOS(previousBalance));
			t.setInterest(TOS(interest));
			t.setPayment(TOS(termPayment));
			t.setPrincipal(TOS(principal));
			t.setNewBalance(TOS(newBalance));
			offer.getPeriods().add(t);
			previousBalance = newBalance;
		}
	}

	private String TOS(double value) {
		return Double.toString(value);
	}
}
