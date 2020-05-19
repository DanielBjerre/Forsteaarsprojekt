package logic;

import database.CreateOffer;
import database.ReadOffer;
import entities.Offer;
import exception.CustomException;

public class OfferLogic {
	/**
	 * Method to create offer
	 * Sets current ActiveEmployee to the Offer
	 * Creates database entries for offer and terms
	 * Creates database entry for customer if customer doesn't already exist
	 */
    public void offerCreate(Offer offer)  {
    	// SET EMPLOYEE ON OFFER
    	offer.setOfferEmployee(ActiveEmployee.getInstance().getEmployee());
    	if (!offer.getOfferCustomer().isExists()) {
    		new CustomerController().createCustomer(offer);
    	}
    	new CreateOffer(offer);
    }
    /**
     * METHOD CHECKS IF CUSTOMER ALREADY HAS AN OFFER FOR CHOSEN CAR
     * THROWS EXCEPTION IF THAT IS TRUE
     */
    public void validateCar(Offer offer) {
    	try {
    	ReadOffer ro = new ReadOffer();
    	ro.validateCar(offer);
    	} catch (CustomException e) {
    		throw e;
    	}
    	
    }
}