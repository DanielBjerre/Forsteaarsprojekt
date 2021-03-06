package entities;

import java.util.ArrayList;

public class Offer {
	private String offerID;
	private Employee offerEmployee;
	private Customer offerCustomer;
	private Car offerCar;
	private boolean customerAccept;
	private boolean managerAccept;
	private int numOfTerms;
	private String downPayment;
	private String rate;
	private double bankRate;
	private String loanValue;
	private ArrayList<Term> periods;

	public String getOfferID() {
		return offerID;
	}

	public void setOfferID(String offerID) {
		this.offerID = offerID;
	}

	public Employee getOfferEmployee() {
		return offerEmployee;
	}

	public void setOfferEmployee(Employee offerEmployee) {
		this.offerEmployee = offerEmployee;
	}

	public Customer getOfferCustomer() {
		return offerCustomer;
	}

	public void setOfferCustomer(Customer offerCustomer) {
		this.offerCustomer = offerCustomer;
	}

	public Car getOfferCar() {
		return offerCar;
	}

	public void setOfferCar(Car offerCar) {
		this.offerCar = offerCar;
	}

	public boolean isCustomerAccept() {
		return customerAccept;
	}

	public void setCustomerAccept(boolean customerAccept) {
		this.customerAccept = customerAccept;
	}

	public boolean isManagerAccept() {
		return managerAccept;
	}

	public void setManagerAccept(boolean managerAccept) {
		this.managerAccept = managerAccept;
	}

	public int getNumOfTerms() {
		return numOfTerms;
	}

	public void setNumOfTerms(int numOfTerms) {
		this.numOfTerms = numOfTerms;
	}

	public String getDownPayment() {
		return downPayment;
	}

	public void setDownPayment(String downPayment) {
		this.downPayment = downPayment;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public ArrayList<Term> getPeriods() {
		return periods;
	}

	public void setPeriods(ArrayList<Term> periods) {
		this.periods = periods;
	}

	public double getDownPaymentDouble() {
		return Double.parseDouble(downPayment);
	}

//	public double getNumOfTermsDouble() {
//		return Double.parseDouble(numOfTerms);
//	}
	public double getRateDouble() {
		return Double.parseDouble(rate);
	}

	public String getLoanValue() {
		return loanValue;
	}

	public void setLoanValue(String loanValue) {
		this.loanValue = loanValue;
	}

	public double getLoanValueDouble() {
		return Double.parseDouble(loanValue);
	}

	public double getBankRate() {
		return bankRate;
	}

	public void setBankRate(double bankRate) {
		this.bankRate = bankRate;
	}
}