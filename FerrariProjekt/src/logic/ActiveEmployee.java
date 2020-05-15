package logic;

import entities.Employee;

public class ActiveEmployee {
	private static ActiveEmployee instance = null;
	private Employee employee;
	private Boolean loggedIn;
	
	public static ActiveEmployee getInstance() {
		if (instance == null)
			instance = new ActiveEmployee();
		
		return instance;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Boolean getLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(Boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	public Employee getEmployee() {
		return employee;
	}
}

