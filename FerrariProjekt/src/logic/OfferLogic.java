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
    	// Set active employee on the offer
    	offer.setOfferEmployee(ActiveEmployee.getInstance().getEmployee());
    	new CreateOffer(offer);
    }
    /**
     * Method checks if customer already has an offer for the chosen car
     * Throws exception if that is true
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