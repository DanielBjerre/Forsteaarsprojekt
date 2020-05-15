package logic;

import database.ReadKunde;
import database.ReadUser;
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
	public void login(String username, String password) {
		ReadUser ru = new ReadUser();
		Employee employee = ru.login(username, password);
	}
}