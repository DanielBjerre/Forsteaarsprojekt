package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entities.Offer;

public class CreateCustomer {
	public CreateCustomer(Offer offer) {
		try (Connection con = new dbConnection().newConnection()) {
			String sql = "INSERT INTO Customer" + "(phoneNumber, cprNumber, firstName, lastName, "
					+ "eMail, address, zipCode, city, badStanding) " + "values (?,?,?,?,?,?,?,?,?);";
			PreparedStatement stmt = con.prepareStatement(sql, new String[] { "" });
			stmt.setString(1, offer.getOfferCustomer().getPhoneNumber());
			stmt.setString(2, offer.getOfferCustomer().getCprNumber());
			stmt.setString(3, offer.getOfferCustomer().getFirstName());
			stmt.setString(4, offer.getOfferCustomer().getLastName());
			stmt.setString(5, offer.getOfferCustomer().geteMail());
			stmt.setString(6, offer.getOfferCustomer().getAddress());
			stmt.setString(7, offer.getOfferCustomer().getZipCode());
			stmt.setString(8, offer.getOfferCustomer().getCity());
			stmt.setBoolean(9, offer.getOfferCustomer().isBadStanding());
			stmt.executeUpdate();
			try (ResultSet rs = stmt.getGeneratedKeys()) {
				if (rs.next()) {
					offer.getOfferCustomer().setCustomerID(rs.getString(1));
					System.out.println(offer.getOfferCustomer().getCustomerID());
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}