package entities;

import javafx.beans.property.SimpleStringProperty;

public class Employee {
	private String employeeID;
	private String firstName;
	private String lastName;
	private double Limit;
	private String title;

	public String getEmployeeID() {
		return employeeID;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public double getLimit() {
		return Limit;
	}

	public String getTitle() {
		return title;
	}

	public void setEmployeeID(String employeeID) {
		this.employeeID = employeeID;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setLimit(double Limit) {
		this.Limit = Limit;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public SimpleStringProperty getEmployeeIDProperty() {
		return new SimpleStringProperty(employeeID);
	}
}
