package logic;

import entities.Employee;

public class ActiveUser {
	private static ActiveUser instance = null;
	private Employee employee;
	
	public static ActiveUser getActiveUser() {
		if (instance == null)
			instance = new ActiveUser();
		
		return instance;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
}

