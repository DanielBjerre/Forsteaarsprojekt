package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entities.Employee;

public class ReadUser {
	public Employee login(String username, String password) {
		Employee e = new Employee();
		try (Connection con = new dbConnection().newConnection()) {
			String sql = "SELECT * from Employee WHERE username=? AND employeePassword =?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, username);
			stmt.setString(2, password);

			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					if (username.toUpperCase().equals(rs.getString("username").toUpperCase())
							&& password.equals(rs.getString("employeePassword"))) {
						e.setEmployeeID(rs.getString("employeeID"));
						e.setFirstName(rs.getString("firtsName"));
						e.setLastName(rs.getString("lastName"));
						e.setLimit(rs.getString("limit"));
						e.setTitle(rs.getString("title"));
					}
				}
			} catch (SQLException exc) {
			}
		} catch (SQLException ex) {

		}
		return e;
	}
}