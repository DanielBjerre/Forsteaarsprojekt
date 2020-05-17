package logic;

import java.util.ArrayList;

import database.ReadCar;
import entities.Car;

public class CarLogic {
	private ArrayList<Car> originalList;

	public CarLogic() {
		ReadCar rc = new ReadCar();
		originalList = rc.findCars();
	}

	public ArrayList<Car> getOriginalList() {
		return originalList;
	}
}
