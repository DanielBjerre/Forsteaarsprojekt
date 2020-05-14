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
}