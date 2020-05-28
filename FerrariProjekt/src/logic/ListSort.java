package logic;

import java.util.ArrayList;

import entities.Car;
import entities.Offer;

public class ListSort {

	public ArrayList<Offer> sortOffer(ArrayList<Offer> arrayList, String criteria, String value) {
		ArrayList<Offer> tempList = new ArrayList<Offer>();

		for (Offer offer : arrayList) {
			switch (criteria) {
			case "S�lger":
				if (offer.getOfferEmployee().getEmployeeID().equals(value)) {
					tempList.add(offer);
				}
				break;
			case "Kunde":
				if (offer.getOfferCustomer().getCprNumber().equals(value)) {
					tempList.add(offer);
				}
				break;
			case "Bil":
				if (offer.getOfferCar().getSerialNumber().equals(value)) {
					tempList.add(offer);
				}
				break;
			case "ManagerAccept":
				if (!offer.isManagerAccept()) {
					tempList.add(offer);
				}
				break;
			case "CustomerAccept":
				if (!offer.isCustomerAccept()) {
					tempList.add(offer);
				}
				break;
			case "C-and-M-Accept":
				if (offer.isCustomerAccept() && offer.isManagerAccept()) {
					tempList.add(offer);
				}
				break;
			case "":
				tempList.add(offer);
				break;
			}
		}
		return tempList;
	}

	/**
	 * Takes an arraylist of cars Returns an arrayList of either used or new cars
	 * Used cars if condition TRUE New cars if condition FALSE
	 * 
	 * @param arrayList
	 * @param condition
	 * @return
	 */
	public ArrayList<Car> sortCar(ArrayList<Car> arrayList, Boolean condition) {
		ArrayList<Car> tempList = new ArrayList<>();
		for (Car car : arrayList) {
			if (car.isUsed() == condition) {
				tempList.add(car);
			}
		}
		return tempList;
	}
}
