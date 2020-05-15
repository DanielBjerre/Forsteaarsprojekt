package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entities.Customer;

public class ReadKunde {
    public Customer findKunde(String telefonnummer)
    {
        Customer k = new Customer();
        try (Connection con = new dbConnection().newConnection()) {
            String sql = "SELECT * FROM Customer WHERE phoneNumber = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, telefonnummer);

            try (ResultSet rs = stmt.executeQuery()) {
            	rs.next();
                k.setPhoneNumber(telefonnummer);
                k.setFirstName(rs.getString("firstName"));
                k.setLastName(rs.getString("lastName"));
                k.setCprNumber(rs.getString("cprnummer"));
                k.seteMail(rs.getString("eMail"));
                k.setCity(rs.getString("city"));
                k.setZipCode(rs.getString("zipCode"));
                k.setAdress(rs.getString("adress"));

            } catch (SQLException e) {
                //TODO: handle exception
            }
        } catch (SQLException e) {
            //TODO: handle exception
        }
        return k;
    }

}