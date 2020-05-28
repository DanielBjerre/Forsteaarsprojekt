package logic;

import entities.Employee;

public class ActiveEmployee {
	private static ActiveEmployee instance = null;
	private Employee employee;

	public static ActiveEmployee getInstance() {
		if (instance == null)
			instance = new ActiveEmployee();

		return instance;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Employee getEmployee() {
		return employee;
	}
}
