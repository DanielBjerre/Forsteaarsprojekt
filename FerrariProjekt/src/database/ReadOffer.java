package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entities.Offer;
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
}
