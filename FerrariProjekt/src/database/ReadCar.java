package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entities.Car;
import exception.CustomException;

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

	public void readCar(Car target)
	{
		try (Connection con = new dbConnection().newConnection()) {
			String sql = "SELECT * FROM car WHERE serialNumber = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, target.getSerialNumber());
			
			try (ResultSet rs = stmt.executeQuery()) {
				if(rs.next())
				{
					target.setUsed(rs.getBoolean("used"));
					target.setModel(rs.getString("model"));
					target.setModelYear(rs.getString("modelyear"));
					target.setColor(rs.getString("color"));
					target.setMileage(rs.getString("mileage"));
					target.setPrice(rs.getString("price"));
					target.setSold(rs.getBoolean("sold"));
				} else {
					throw new CustomException("Car findes ikke i databasen");
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}