package database;

import java.sql.DriverManager;
import java.sql.Connection;

public class dbConnection {
	private Connection connection;
	
	public Connection newConnection() {
		try {
		connection = DriverManager.getConnection("jdbc:sqlserver://87.49.229.150:1500;" + "instanceName=SQLEXPRESS;" + "databaseName=FF;", "ESS", "Kode");	
		System.out.println("Connected");

		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
		return connection;
	}
	
}