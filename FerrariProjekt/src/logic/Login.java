package logic;

import database.ReadEmployee;
import entities.Employee;
import exception.CustomException;

public class Login {
	public void login(String username, String password) {
		try {
			ReadEmployee ru = new ReadEmployee();
			Employee employee = ru.login(username, password);
			ActiveEmployee au = ActiveEmployee.getInstance();
			au.setEmployee(employee);
		} catch (CustomException e) {
			throw e;
		}
	}
}