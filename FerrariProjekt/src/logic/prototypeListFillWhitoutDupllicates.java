package logic;

import java.util.ArrayList;

import database.ReadCar;
import entities.Car;

public class prototypeListFillWhitoutDupllicates {

    ArrayList<String> model = new ArrayList<>();
    ArrayList<String> modelYear = new ArrayList<>();
    ArrayList<String> color = new ArrayList<>();
    public static void main(String[] args) {
        prototypeListFillWhitoutDupllicates pfwd = new prototypeListFillWhitoutDupllicates();
        pfwd.run();    
    }

    public void run()
    {
        ArrayList<Car> car = new ReadCar().findCars();
        for (Car item : car) {
            fillList(model, item.getModel());
            fillList(modelYear, item.getModelYear());
            fillList(color, item.getColor());
        }

        String test = "";
    }

    private void fillList(ArrayList<String> taget, String value)
    {
        if (!taget.contains(value)) { taget.add(value); }
    }

}