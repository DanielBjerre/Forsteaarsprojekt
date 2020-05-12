package logic;

import database.CreateTilbud;
import entities.Tilbud;

public class TilbudLogic {
    public void lavTilbud(int bruger, int kundetelefon, int bil, int accepteret, int godkendt, int loebetid, double udbetaling, double rente)
    {
        Tilbud t = new Tilbud();
        t.setBruger(bruger);
        t.setKundetelefon(kundetelefon);
        t.setBil(bil);
        t.setAccepteret(accepteret);
        t.setGodkendt(godkendt);
        t.setLoebetid(loebetid);
        t.setUdbetaling(udbetaling);
        t.setRente(rente);
        new CreateTilbud(t);
    }
}