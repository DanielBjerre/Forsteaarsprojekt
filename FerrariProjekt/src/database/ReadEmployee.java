package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entities.Employee;
import exception.CustomException;

public class ReadEmployee {
	public Employee login(String username, String password) {
		try (Connection con = new dbConnection().newConnection()) {
			String sql = "SELECT * from Employee WHERE username=? AND employeePassword =?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, username);
			stmt.setString(2, password);

			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					if (username.toUpperCase().equals(rs.getString("username").toUpperCase())
							&& password.equals(rs.getString("employeePassword"))) {
						Employee e = new Employee();
						e.setEmployeeID(rs.getString("employeeID"));
						e.setFirstName(rs.getString("firstName"));
						e.setLastName(rs.getString("lastName"));
						e.setLimit(rs.getDouble("limit"));
						e.setTitle(rs.getString("title"));
						return e;
					}

				}
			} catch (SQLException exc) {
			}
		} catch (SQLException ex) {
		}
		throw new CustomException("Brugernavn eller adgangskode forkert");
	}

}