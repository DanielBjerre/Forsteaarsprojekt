package logic;

import database.ReadKunde;
import entities.Kunde;

public class KundeLogic {
    public Kunde findKunde(String telefonnummer)
    {
        Kunde k;
        if(telefonnummer != "")
        {
            ReadKunde rk = new ReadKunde();
            k = rk.findKunde(telefonnummer);
        }
        else 
        {
            throw new ArithmeticException("Telefonnummer må ikke være tomt");
        }
        return k;
    }
}