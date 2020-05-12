package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entities.Kunde;

public class ReadKunde {
    public Kunde findKunde(String telefonnummer)
    {
        Kunde k = new Kunde();
        try (Connection con = new dbConnection().newConnection()) {
            String sql = "SELECT * FROM kunde WHERE telefonnummer = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, telefonnummer);

            try (ResultSet rs = stmt.executeQuery()) {
                k.setTelefonnummer(telefonnummer);
                k.setFornavn(rs.getString("fornavn"));
                k.setEfternavn(rs.getString("efternavn"));
                k.setCprnummer(rs.getString("cprnummer"));
                k.setEmail(rs.getString("email"));
                k.setCity(rs.getString("city"));
                k.setPostnummer(rs.getString("postnummer"));

            } catch (SQLException e) {
                //TODO: handle exception
            }
        } catch (SQLException e) {
            //TODO: handle exception
        }
        return k;
    }

}