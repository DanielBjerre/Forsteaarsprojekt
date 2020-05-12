package entities;

public class Tilbud {
    private int id;
    private int bruger;
    private int kundetelefon;
    private int bil;
    private boolean accepteret;
    private boolean godkendt;
    private int loebetid;
    private double udbetaling;
    private double rente;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBruger() {
        return bruger;
    }

    public void setBruger(int bruger) {
        this.bruger = bruger;
    }

    public int getKundetelefon() {
        return kundetelefon;
    }

    public void setKundetelefon(int kundetelefon) {
        this.kundetelefon = kundetelefon;
    }

    public int getBil() {
        return bil;
    }

    public void setBil(int bil) {
        this.bil = bil;
    }

    public boolean isAccepteret() {
        return accepteret;
    }

    public void setAccepteret(boolean accepteret) {
        this.accepteret = accepteret;
    }

    public boolean isGodkendt() {
        return godkendt;
    }

    public void setGodkendt(boolean godkendt) {
        this.godkendt = godkendt;
    }

    public int getLoebetid() {
        return loebetid;
    }

    public void setLoebetid(int loebetid) {
        this.loebetid = loebetid;
    }

    public double getUdbetaling() {
        return udbetaling;
    }

    public void setUdbetaling(double udbetaling) {
        this.udbetaling = udbetaling;
    }

    public double getRente() {
        return rente;
    }

    public void setRente(double rente) {
        this.rente = rente;
    }
}