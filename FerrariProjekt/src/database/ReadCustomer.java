package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entities.Customer;
import exception.CustomException;

public class ReadCustomer {
	public Customer findCustomer(String cprNumber) {
		try (Connection con = new dbConnection().newConnection()) {
			String sql = "SELECT * FROM Customer WHERE cprNumber = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, cprNumber);

			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					Customer c = new Customer();
					c.setCustomerID(rs.getString("customerID"));
					c.setPhoneNumber(rs.getString("phoneNumber"));
					c.setCprNumber(cprNumber);
					c.setFirstName(rs.getString("firstName"));
					c.setLastName(rs.getString("lastName"));
					c.seteMail(rs.getString("eMail"));
					c.setAdress(rs.getString("adress"));
					c.setZipCode(rs.getString("zipCode"));
					c.setCity(rs.getString("city"));
					c.setBadStanding(rs.getBoolean("badStanding"));
					return c;
				}

			} catch (SQLException e) {
			}
		} catch (SQLException e) {
		}
		throw new CustomException("Kunde findes ikke i databasen");
	}

}