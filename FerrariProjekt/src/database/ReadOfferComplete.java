package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entities.Car;
import entities.Customer;
import entities.Employee;
import entities.Offer;

public class ReadOfferComplete {

    public ArrayList<Offer> readOfferComplete() {
        ArrayList<Offer> offerList = new ArrayList<>();
        try (Connection con = new dbConnection().newConnection()) {
            String sql = "SELECT * FROM Offer JOIN Car on Offer.carID = Car.serialNumber JOIN Employee on Offer.employeeID = Employee.employeeID JOIN Customer on Offer.customerID = Customer.customerID";
            PreparedStatement stmt = con.prepareStatement(sql);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Offer o = new Offer();
                    o.setOfferID(rs.getString("offerID"));
                    // Employee
                    Employee e = new Employee();
                    e.setFirstName(rs.getString("firstName"));
                    e.setLastName(rs.getString("lastName"));
                    e.setLimit(rs.getDouble("limit"));
                    e.setTitle(rs.getString("title"));
                    e.setEmployeeID(rs.getString("employeeID"));
                    o.setOfferEmployee(e);

                    // Customer
                    Customer c = new Customer();
                    c.setCustomerID(rs.getString("customerID"));
                    c.setPhoneNumber(rs.getString("phoneNumber"));
                    c.setCprNumber(rs.getString("cprNumber"));
                    c.setFirstName(rs.getString("firstName"));
                    c.setLastName(rs.getString("lastName"));
                    c.seteMail(rs.getString("eMail"));
                    c.setAddress(rs.getString("address"));
                    c.setZipCode(rs.getString("zipCode"));
                    c.setCity(rs.getString("city"));
                    c.setBadStanding(rs.getBoolean("badStanding"));
                    o.setOfferCustomer(c);

                    // Car
                    Car car = new Car();
                    car.setUsed(rs.getBoolean("used"));
                    car.setModel(rs.getString("model"));
                    car.setModelYear(rs.getString("modelyear"));
                    car.setColor(rs.getString("color"));
                    car.setMileage(rs.getString("mileage"));
                    car.setPrice(rs.getString("price"));
                    car.setSold(rs.getBoolean("sold"));
                    car.setSerialNumber(rs.getString("carID"));
                    o.setOfferCar(car);

                    o.setCustomerAccept(rs.getBoolean("customerAccept"));
                    o.setManagerAccept(rs.getBoolean("managerAccept"));
                    o.setNumOfTerms(rs.getString("numOfTerms"));
                    o.setDownPayment(rs.getString("downPayment"));
                    o.setRate(rs.getString("rate"));
                    o.setLoanValue(rs.getString("loanvalue"));
                    o.setPeriods(new ReadTerm().findTerm(o));
                    offerList.add(o);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return offerList;
    }
}