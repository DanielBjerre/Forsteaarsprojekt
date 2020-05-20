package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entities.Car;

public class ReadCar {
	public ArrayList<Car> findCars() {
		ArrayList<Car> alCar = new ArrayList<Car>();
		try (Connection con = new dbConnection().newConnection()) {
			String sql = "SELECT * FROM car";
			PreparedStatement stmt = con.prepareStatement(sql);
			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					Car c = new Car();
					c.setSerialNumber(rs.getString("serialNumber"));
					c.setUsed(rs.getBoolean("used"));
					c.setModel(rs.getString("model"));
					c.setModelYear(rs.getString("modelYear"));
					c.setColor(rs.getString("color"));
					c.setMileage(rs.getString("mileage"));
					c.setPrice(rs.getString("price"));
					c.setSold(rs.getBoolean("sold"));
					alCar.add(c);
				}

			} catch (SQLException e) {
			}
		} catch (SQLException e) {
		}
		return alCar;
	}

}