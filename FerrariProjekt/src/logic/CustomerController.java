package logic;

import database.ReadCustomer;
import entities.Customer;
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
}