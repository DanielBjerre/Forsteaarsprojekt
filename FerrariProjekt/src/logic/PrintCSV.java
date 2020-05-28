package logic;

import java.io.FileWriter;
import java.io.IOException;

import entities.Offer;
import entities.Term;

public class PrintCSV {
    private Offer offer;
    public PrintCSV(Offer offer) {
        this.offer = offer;
        csvOffer();
        csvTerm();
    }

    private void csvOffer() {

        try (FileWriter csvWrite = new FileWriter("offer" + offer.getOfferID() + ".csv");) {

            csvWrite.append("offerID");
            csvWrite.append(",");
            csvWrite.append("customerAccept");
            csvWrite.append(",");
            csvWrite.append("managerAccept");
            csvWrite.append(",");
            csvWrite.append("numOfTerms");
            csvWrite.append(",");
            csvWrite.append("downPayment");
            csvWrite.append(",");
            csvWrite.append("rate");
            csvWrite.append(",");
            csvWrite.append("bankRate");
            csvWrite.append(",");
            csvWrite.append("loanValue");
            csvWrite.append(",");
            csvWrite.append("Employee.employeeID");
            csvWrite.append(",");
            csvWrite.append("Employee.firstName");
            csvWrite.append(",");
            csvWrite.append("Employee.lastName");
            csvWrite.append(",");
            csvWrite.append("Employee.Limit");
            csvWrite.append(",");
            csvWrite.append("Employee.title");
            csvWrite.append(",");
            csvWrite.append("customer.customerID");
            csvWrite.append(",");
            csvWrite.append("customer.phoneNumber");
            csvWrite.append(",");
            csvWrite.append("customer.cprNumber");
            csvWrite.append(",");
            csvWrite.append("customer.firstName");
            csvWrite.append(",");
            csvWrite.append("customer.lastName");
            csvWrite.append(",");
            csvWrite.append("customer.eMail");
            csvWrite.append(",");
            csvWrite.append("customer.address");
            csvWrite.append(",");
            csvWrite.append("customer.zipCode");
            csvWrite.append(",");
            csvWrite.append("customer.city");
            csvWrite.append(",");
            csvWrite.append("customer.badStanding");
            csvWrite.append(",");
            csvWrite.append("customer.exists");
            csvWrite.append(",");
            csvWrite.append("customer.creditRating");
            csvWrite.append(",");
            csvWrite.append("Car.serialNumber");
            csvWrite.append(",");
            csvWrite.append("Car.used");
            csvWrite.append(",");
            csvWrite.append("Car.model");
            csvWrite.append(",");
            csvWrite.append("Car.modelYear");
            csvWrite.append(",");
            csvWrite.append("Car.color");
            csvWrite.append(",");
            csvWrite.append("Car.mileage");
            csvWrite.append(",");
            csvWrite.append("Car.price");
            csvWrite.append(",");
            csvWrite.append("Car.sold");
            csvWrite.append("\n");

            csvWrite.append(offer.getOfferID());
            csvWrite.append(",");
            csvWrite.append(b2s(offer.isCustomerAccept()));
            csvWrite.append(",");
            csvWrite.append(b2s(offer.isManagerAccept()));
            csvWrite.append(",");
            csvWrite.append(Integer.toString(offer.getNumOfTerms()));
            csvWrite.append(",");
            csvWrite.append(offer.getDownPayment());
            csvWrite.append(",");
            csvWrite.append(offer.getRate());
            csvWrite.append(",");
            csvWrite.append(Double.toString(offer.getBankRate()));
            csvWrite.append(",");
            csvWrite.append(offer.getLoanValue());
            csvWrite.append(",");
            csvWrite.append(offer.getOfferEmployee().getEmployeeID());
            csvWrite.append(",");
            csvWrite.append(offer.getOfferEmployee().getFirstName());
            csvWrite.append(",");
            csvWrite.append(offer.getOfferEmployee().getLastName());
            csvWrite.append(",");
            csvWrite.append(Double.toString(offer.getOfferEmployee().getLimit()));
            csvWrite.append(",");
            csvWrite.append(offer.getOfferEmployee().getTitle());
            csvWrite.append(",");
            csvWrite.append(offer.getOfferCustomer().getCustomerID());
            csvWrite.append(",");
            csvWrite.append(offer.getOfferCustomer().getPhoneNumber());
            csvWrite.append(",");
            csvWrite.append(offer.getOfferCustomer().getCprNumber());
            csvWrite.append(",");
            csvWrite.append(offer.getOfferCustomer().getFirstName());
            csvWrite.append(",");
            csvWrite.append(offer.getOfferCustomer().getLastName());
            csvWrite.append(",");
            csvWrite.append(offer.getOfferCustomer().geteMail());
            csvWrite.append(",");
            csvWrite.append(offer.getOfferCustomer().getAddress());
            csvWrite.append(",");
            csvWrite.append(offer.getOfferCustomer().getZipCode());
            csvWrite.append(",");
            csvWrite.append(offer.getOfferCustomer().getCity());
            csvWrite.append(",");
            csvWrite.append(b2s(offer.getOfferCustomer().isBadStanding()));
            csvWrite.append(",");
            csvWrite.append(b2s(offer.getOfferCustomer().isExists()));
            csvWrite.append(",");
            csvWrite.append("N/A");
            csvWrite.append(",");
            csvWrite.append(offer.getOfferCar().getSerialNumber());
            csvWrite.append(",");
            csvWrite.append(b2s(offer.getOfferCar().isUsed()));
            csvWrite.append(",");
            csvWrite.append(offer.getOfferCar().getModel());
            csvWrite.append(",");
            csvWrite.append(offer.getOfferCar().getModelYear());
            csvWrite.append(",");
            csvWrite.append(offer.getOfferCar().getColor());
            csvWrite.append(",");
            csvWrite.append(offer.getOfferCar().getMileage());
            csvWrite.append(",");
            csvWrite.append(offer.getOfferCar().getPrice());
            csvWrite.append(",");
            csvWrite.append(b2s(offer.getOfferCar().isSold()));
            csvWrite.append("\n");
            csvWrite.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void csvTerm()
    {
        try (FileWriter csvWrite = new FileWriter("Terms-Offer" + offer.getOfferID() + ".csv");) {

            csvWrite.append("termNumber");
            csvWrite.append(",");
            csvWrite.append("previousBalance");
            csvWrite.append(",");
            csvWrite.append("payment");
            csvWrite.append(",");
            csvWrite.append("interest");
            csvWrite.append(",");
            csvWrite.append("principal");
            csvWrite.append(",");
            csvWrite.append("newBalance");
            csvWrite.append("\n");

            for (Term term : offer.getPeriods()) {
                csvWrite.append(Integer.toString(term.getTermNumber()));
                csvWrite.append(",");
                csvWrite.append(term.getPreviousBalance());
                csvWrite.append(",");
                csvWrite.append(term.getPayment());
                csvWrite.append(",");
                csvWrite.append(term.getInterest());
                csvWrite.append(",");
                csvWrite.append(term.getPrincipal());
                csvWrite.append(",");
                csvWrite.append(term.getNewBalance());
                csvWrite.append("\n");
            }

            csvWrite.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String b2s(boolean indput) {
        return indput ? "True" : "False";
    }
}