package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entity.Bruger;

public class ReadBruger {

    public static void main(String[] args) {
    ReadBruger rb = new ReadBruger();
    rb.checkLogin("SK", "KODe");    
    }


    public void checkLogin(String brugernavn, String password) {
        Bruger b = Bruger.getInstance();
        try (Connection con = new dbConnection().newConnection()) {
            String sql = "SELECT * from Bruger WHERE brugernavn=? AND adgangskode =?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, brugernavn);
            stmt.setString(2, password);

            try (ResultSet rs = stmt.executeQuery()) {
                if(rs.next())
                {
                    if (brugernavn.toUpperCase().equals(rs.getString("brugernavn").toUpperCase()) && password.equals(rs.getString("adgangskode")))
                    {
                        
                        b.setLoggedIn(true);
                        b.setBrugerID(rs.getString("id"));
                        b.setFornavn(rs.getString("fornavn"));
                        b.setEfternavn(rs.getString("efternavn"));
                        b.setPengeMax(rs.getDouble("beløbsgrænse"));
                        b.setTitle(rs.getString("titel"));
                        b.showBrugerInfo();
                    }
                }
            } catch (SQLException e) {

            }

        } catch (SQLException ex) {

        }
        if(!b.isLoggedIn())
        {
            throw new ArithmeticException("Brugernavn eller adgangskode er forkert");
        }
    }

}