package database;

import java.sql.Connection;
import java.sql.PreparedStatement;

import entities.Offer;

public class UpdateTilbud {
    public UpdateTilbud(Offer data)
    {
        try (Connection con = new dbConnection().newConnection()) {
            String sql = "";
            PreparedStatement stmt = con.prepareStatement(sql);
        } catch (Exception e) {
           
        }
    }
}