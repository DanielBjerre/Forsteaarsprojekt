package logic;

import database.ReadKunde;
import database.ReadEmployee;
import entities.Employee;

public class DBFacade {
//    public Kunde findKunde(String telefonnummer)
//    {
//        Kunde k;
//        if(telefonnummer != "")
//        {
//            ReadKunde rk = new ReadKunde();
//            k = rk.findKunde(telefonnummer);
//        }
//        return k;
//    }
//}
	public void readEmployee(String username, String password) {
		ReadEmployee ru = new ReadEmployee();
		Employee employee = ru.login(username, password);
		ActiveEmployee au = new ActiveEmployee();
		au.setEmployee(employee);
		au.setLoggedIn(true);
	}
}