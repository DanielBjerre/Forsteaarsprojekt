package logic;

import java.util.ArrayList;

import database.ReadCar;
import entities.Car;

public class prototypeListFillWhitoutDupllicates {

    ArrayList<String> model = new ArrayList<>();
    ArrayList<String> modelYear = new ArrayList<>();
    ArrayList<String> color = new ArrayList<>();

    public void run(ArrayList<Car> arrayList)
    {
        ArrayList<Car> car = new ReadCar().findCars();
        for (Car item : car) {
            fillList(model, item.getModel());
            fillList(modelYear, item.getModelYear());
            fillList(color, item.getColor());
        }
    }

    private void fillList(ArrayList<String> taget, String value)
    {
        if (!taget.contains(value)) { taget.add(value); }
    }

    public ArrayList<String> getModel() {
        return model;
    }

    public ArrayList<String> getModelYear() {
        return modelYear;
    }

    public ArrayList<String> getColor() {
        return color;
    }

}