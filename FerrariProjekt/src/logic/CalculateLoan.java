package logic;

import java.util.ArrayList;

import entities.Offer;
import entities.Term;

public class CalculateLoan {
	Offer offer;
	Double monthlyRate;
	Double payment;
	Double totalPayment;
	double numOfTerms;

	public CalculateLoan(Offer offer) {
		this.offer = offer;
		this.numOfTerms = offer.getNumOfTermsDouble();
		calculateRate();
		calculateTermRate();
		calculatePayment();
		calculateTerms();
		System.out.println("Rate: " + offer.getRate());
		System.out.println("Monthly Rate: " + monthlyRate);
		System.out.println("Payment: " + payment);
		for(Term term : offer.getPeriods()) {
			System.out.print(term.getTermNumber()+"  ");
			System.out.print(term.getPreviousBalance()+"  ");
			System.out.print(term.getPayment()+"  ");
			System.out.print(term.getInterest()+"  ");
			System.out.print(term.getPrincipal()+"  ");
			System.out.print(term.getNewBalance()+"  ");
			System.out.println();
		}

	}

	private void calculateRate() {
		Double rate = offer.getRateDouble();
		if (offer.getDownPaymentDouble() < (offer.getOfferCar().getPriceDouble() / 2))
			;
		rate += 1;
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
		if (offer.getNumOfTermsDouble() > 36) {
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
		totalPayment = numOfTerms * payment;
	}
	
	private void calculateTerms() {
		double previousBalance = offer.getLoanValueDouble();
		this.offer.setPeriods(new ArrayList<Term>());
		for( int i = 1; i <= numOfTerms; i++) {	
			double interest = previousBalance*monthlyRate;
			double principal = payment-interest;
			double newBalance = previousBalance-principal;
			Term t = new Term();
			t.setTermNumber(TOS(i));
			t.setPreviousBalance(TOS(previousBalance));
			t.setPayment(TOS(payment));
			t.setInterest(TOS(interest));
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
