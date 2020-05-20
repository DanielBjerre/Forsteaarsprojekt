package logic;

import database.CreateCustomer;
import database.ReadCustomer;
import entities.Customer;
import entities.Offer;
import exception.CustomException;

public class CustomerController {
	public Customer findCustomer(String cprNumber) {
		try {
			ReadCustomer rc = new ReadCustomer();
			return rc.findCustomer(cprNumber);
		} catch (CustomException e) {
			throw e;
		}
	}
	public void createCustomer(Offer offer) {
		new CreateCustomer(offer);
	}
}