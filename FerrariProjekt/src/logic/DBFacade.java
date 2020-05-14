package logic;

import database.ReadKunde;
import database.ReadUser;
import entities.Employee;
import entities.Kunde;

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
		ReadUser ru = new ReadUser();
		Employee employee = ru.checkLogin(username, password);
	}
}