package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import entities.Offer;

public class UpdateOffer {
	public UpdateOffer(Offer offer) {
		try (Connection con = new dbConnection().newConnection()) {
			String sql = "UPDATE Offer SET customerID=?, employeeID=?, carID=?, customerAccept=?, managerAccept=?, rate=?, downpayment=?, numOfTerms=?, loanValue=? WHERE offerID=?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, offer.getOfferCustomer().getCustomerID());
			stmt.setString(2, offer.getOfferEmployee().getEmployeeID());
			stmt.setString(3, offer.getOfferCar().getSerialNumber());
			stmt.setBoolean(4, offer.isCustomerAccept());
			stmt.setBoolean(5, offer.isManagerAccept());
			stmt.setString(6, offer.getRate());
			stmt.setString(7, offer.getDownPayment());
			stmt.setInt(8, offer.getNumOfTerms());
			stmt.setString(9, offer.getLoanValue());
			stmt.setString(10, offer.getOfferID());
			stmt.executeUpdate();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
}