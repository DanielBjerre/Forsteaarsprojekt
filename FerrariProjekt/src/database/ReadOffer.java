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
import entities.Term;
import exception.CustomException;

public class ReadOffer {
	public void validateCar(Offer offer) {
		try (Connection con = new dbConnection().newConnection()) {
			String sql = "SELECT * FROM Offer WHERE customerID = ? AND carID = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, offer.getOfferCustomer().getCustomerID());
			stmt.setString(2, offer.getOfferCar().getSerialNumber());
			
			try (ResultSet rs = stmt.executeQuery()) {
				if(rs.next()) {
					throw new CustomException("Kunden har allerede et tilbud p� den valgte bil");
				}
				
			}catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public ArrayList<Offer> fullOfferList()
	{
		ArrayList<Offer> offerList = new ArrayList<>();
		try (Connection con = new dbConnection().newConnection()) {
			String sql = "SELECT * FROM Offer";
			PreparedStatement stmt = con.prepareStatement(sql);

			
			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					Offer o = new Offer();
					o.setOfferID(rs.getString("offerID"));
					//Employee
					o.setOfferEmployee(new Employee());
					o.getOfferEmployee().setEmployeeID(rs.getString("employeeID"));
					//Customer
					o.setOfferCustomer(new Customer());
					o.getOfferCustomer().setCustomerID(rs.getString("customerID"));
					//Car
					o.setOfferCar(new Car());
					o.getOfferCar().setSerialNumber(rs.getString("carID"));

					o.setCustomerAccept(rs.getBoolean("customerAccept"));
					o.setManagerAccept(rs.getBoolean("managerAccept"));
					o.setNumOfTerms(rs.getString("numOfTerms"));
					o.setDownPayment(rs.getString("downPayment"));
					o.setRate(rs.getString("rate"));
					o.setLoanValue(rs.getString("loanvalue"));
					o.setPeriods(new ArrayList<Term>());
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
