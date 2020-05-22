package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entities.Offer;

public class CreateOffer {
	private int offerID;

	public CreateOffer(Offer offer) {
		try (Connection con = new dbConnection().newConnection()) {
			String sql = "INSERT INTO Offer" + "(customerID, employeeID, carID, customerAccept, "
					+ "managerAccept, rate, downpayment, numOfTerms, loanValue) " + "values (?,?,?,?,?,?,?,?,?);";
			PreparedStatement stmt = con.prepareStatement(sql, new String[] { "" });
			stmt.setString(1, offer.getOfferCustomer().getCustomerID());
			stmt.setString(2, offer.getOfferEmployee().getEmployeeID());
			stmt.setString(3, offer.getOfferCar().getSerialNumber());
			stmt.setBoolean(4, offer.isCustomerAccept());
			stmt.setBoolean(5, offer.isManagerAccept());
			stmt.setString(6, offer.getRate());
			stmt.setString(7, offer.getDownPayment());
			stmt.setInt(8, offer.getNumOfTerms());
			stmt.setString(9, offer.getLoanValue());
			stmt.executeUpdate();
			try (ResultSet rs = stmt.getGeneratedKeys()) {
				if (rs.next()) {
					offerID = rs.getInt(1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		// Add terms from the offer to the database
		new CreateTerm(offerID, offer);
	}
}