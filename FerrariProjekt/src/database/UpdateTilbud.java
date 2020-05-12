package database;

import java.sql.Connection;
import java.sql.PreparedStatement;

import entities.Tilbud;

public class UpdateTilbud {
    public UpdateTilbud(Tilbud data)
    {
        try (Connection con = new dbConnection().newConnection()) {
            String sql = "";
            PreparedStatement stmt = con.prepareStatement(sql);
        } catch (Exception e) {
           
        }
    }
}