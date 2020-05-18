package logic;

import entities.Offer;

public class CalculateLoan {
	Offer offer;
	Double monthlyRate;
	Double payment;
	public CalculateLoan(Offer offer) {
		this.offer = offer;
		calculateRate();
		calculateTermRate();
		calculatePayment();
		System.out.println("Rate: " + offer.getRate());
		System.out.println("Monthly Rate: " +monthlyRate);
		System.out.println("Payment: " + payment);
		
	}
	
	private void calculateRate() {
		Double rate = offer.getRateDouble();
		if(offer.getDownPaymentDouble() < (offer.getOfferCar().getPriceDouble()/2));
		rate +=1;
		switch(offer.getOfferCustomer().getCreditRating()) {
		case A:
			rate +=1;
			break;
		case B:
			rate +=2;
			break;
		case C:
			rate +=3;
			break;
		case D:
			break;
		}
		if(offer.getNumOfTermsDouble() > 36) {
			rate+=1;
		}
		offer.setRate(rate.toString());
	}
	private void calculateTermRate() {
		monthlyRate = Math.pow(1+(offer.getRateDouble()/100), 1.0/12)-1;
	}
	private void calculatePayment() {
		double løbetid = offer.getNumOfTermsDouble();
		double pris = offer.getOfferCar().getPriceDouble();
		double udbetaling = offer.getDownPaymentDouble();
		
		double potens = (løbetid-(løbetid*2));
		double lower = 1-Math.pow(1.0+monthlyRate, potens);
		payment = (pris-udbetaling)*(monthlyRate/lower);		
	}
}
