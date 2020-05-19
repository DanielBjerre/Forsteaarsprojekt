package entities;

import FFL.Rating;

public class Customer {
	private String customerID;
    private String phoneNumber;
    private String cprNumber;
    private String firstName;
    private String lastName;
    private String eMail;
    private String address;
    private String zipCode;
    private String city;
    private boolean badStanding;
    private boolean exists;
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
	public String getAddress() {
		return address;
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
	public void setAddress(String address) {
		this.address = address;
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
	public boolean isBadStanding() {
		return badStanding;
	}
	public void setBadStanding(boolean badStanding) {
		this.badStanding = badStanding;
	}
	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}
	public boolean isExists() {
		return exists;
	}
	public void setExists(boolean exists) {
		this.exists = exists;
	}
}