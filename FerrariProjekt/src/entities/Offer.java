package entities;

import java.util.ArrayList;

public class Offer {
    private String offerID;
    private Employee offerEmployee;
    private Customer offerCustomer;
    private Car offerCar;
    private boolean customerAccept;
    private boolean managerAccept;
    private String numOfTerms;
    private String downPayment;
    private String rate;
    private ArrayList<Period> periods;
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
	public String getNumOfTerms() {
		return numOfTerms;
	}
	public void setNumOfTerms(String numOfTerms) {
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
	public ArrayList<Period> getPeriods() {
		return periods;
	}
	public void setPeriods(ArrayList<Period> periods) {
		this.periods = periods;
	} 
}