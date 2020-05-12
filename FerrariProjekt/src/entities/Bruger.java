package entities;

public class Bruger {
    private static Bruger single_instance = null;
    private boolean isLoggedIn = false;
    private String brugerID;
    private String fornavn;
    private String efternavn;
    private double pengeMax;
    private String title;


    public static Bruger getInstance()
    {
        if (single_instance == null)
        {
            single_instance = new Bruger();
        }
        return single_instance;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(boolean isLoggedIn) {
        this.isLoggedIn = isLoggedIn;
    }

    public String getBrugerID() {
        return brugerID;
    }

    public void setBrugerID(String brugerID) {
        this.brugerID = brugerID;
    }

    public String getFornavn() {
        return fornavn;
    }

    public void setFornavn(String fornavn) {
        this.fornavn = fornavn;
    }

    public String getEfternavn() {
        return efternavn;
    }

    public void setEfternavn(String efternavn) {
        this.efternavn = efternavn;
    }

    public double getPengeMax() {
        return pengeMax;
    }

    public void setPengeMax(double pengeMax) {
        this.pengeMax = pengeMax;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Giver et syso på alle info i entities
     */
    public void showBrugerInfo()
    {
        System.out.println("BRUGER INFO DUMP");
        System.out.println("Bruger loggetind: "+isLoggedIn);
        System.out.println("Bruger ID: "+brugerID);
        System.out.println("Fornavn: "+fornavn);
        System.out.println("Efternavn: "+efternavn);
        System.out.println("Max beløb: "+pengeMax);
        System.out.println("Titel: "+title);
    }

    public void clearInfo()
    {
        isLoggedIn = false;
        brugerID = "";
        fornavn = "";
        efternavn = "";
        pengeMax = 0;
        title = "";
    }
}