package database;

import java.sql.Connection;
import java.sql.DriverManager;

public class dbConnection {
	private Connection connection;
	
	public Connection newConnection() {
		try {
		connection = DriverManager.getConnection("jdbc:sqlserver://87.49.229.150:1500;" + "instanceName=SQLEXPRESS;" + "databaseName=FF;", "ESS", "Kode");	
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
		return connection;
	}
	
}