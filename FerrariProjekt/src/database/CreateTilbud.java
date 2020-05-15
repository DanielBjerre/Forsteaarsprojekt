package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import entities.Offer;

public class CreateTilbud {
    public CreateTilbud(Offer data)
    {
        try (Connection con = new dbConnection().newConnection()) {
            String sql = "INSERT INTO Tilbud (bruger,kundetelefon,bil,accepteret,godkendt,løbetid,udbetaling,rente) values (?,?,?,?,?,?,?,?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, data.getBruger());
            stmt.setInt(2, data.getKundetelefon());
            stmt.setInt(3, data.getBil());
            stmt.setInt(4, data.getAccepteret());
            stmt.setInt(5, data.getGodkendt());
            stmt.setInt(6, data.getLoebetid());
            stmt.setDouble(7, data.getUdbetaling());
            stmt.setDouble(8, data.getRente());
            stmt.executeQuery();
        } catch (SQLException e) {
            
        }
    }
}