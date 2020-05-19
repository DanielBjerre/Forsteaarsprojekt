package logic;

import database.CreateOffer;
import entities.Offer;

public class OfferLogic {
    public OfferLogic(Offer offer)  {
    	// SET EMPLOYEE ON OFFER
    	offer.setOfferEmployee(ActiveEmployee.getInstance().getEmployee());
    	
    	new CreateOffer(offer);
    }
}