package entities;

import FFL.Rating;

public class Customer {
	private String customerID;
    private String phoneNumber;
    private String cprNumber;
    private String firstName;
    private String lastName;
    private String eMail;
    private String adress;
    private String city;
    private String zipCode;
    private Rating creditRating;
	public String getCustomerID() {
		return customerID;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public String getCprNumber() {
		return cprNumber;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public String geteMail() {
		return eMail;
	}
	public String getAdress() {
		return adress;
	}
	public String getCity() {
		return city;
	}
	public String getZipCode() {
		return zipCode;
	}
	public Rating getCreditRating() {
		return creditRating;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public void seteMail(String eMail) {
		this.eMail = eMail;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public void setCreditRating(Rating creditRating) {
		this.creditRating = creditRating;
	}

	public void setCprNumber(String cprNumber) {
		this.cprNumber = cprNumber;
	}
}