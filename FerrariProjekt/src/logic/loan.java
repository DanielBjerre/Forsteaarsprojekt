package logic;

import java.util.ArrayList;



import FFL.Rating;

public class loan {
    private int aaop;
    private double pris;
    private double udbetaling;
    private int loebetid;
    private Rating creditRate;
    private int bankRate = 4;
    private double terminRent;
    private double ydelse;
    private double totalYdelse;
    private ArrayList<Stats> stats = new ArrayList<>();

    public loan(double pris, double udbetaling, int loebetid, Rating kreditrate, int bankRate) 
    {
        if (pris < udbetaling)
        {
            throw new ArithmeticException("Udbetaling er højere end prisen");
        }
        this.bankRate = bankRate;
        init(pris, udbetaling, loebetid, kreditrate);
    }

    public loan() {

    }
    
    /**
     * Init
     * @param pris  prisen på bilen
     * @param udbetaling  hvad kunden giver af udbetaling på bilen da han laver lånet
     * @param loebetid  hvor mange måneder lånet skal gå over
     * @param creditRate  hvad kunden har fået af kreditvurdering fra RKI
     */
    private void init(double pris, double udbetaling, int loebetid, Rating creditRate) {
        this.pris = pris;
        this.udbetaling = udbetaling;
        this.loebetid = loebetid;
        this.creditRate = creditRate;
        calcAaop();
        calcTerminRent();
        calcYdelse();
        fillStats();
    }

    private void calcAaop() {
        int rent = bankRate;
        rent += udbetalingsRate(udbetaling, pris);

        if (loebetid > 36) {
            rent++;
        }

        switch (creditRate) {
            case A:
                rent += 1;
                break;
            case B:
                rent += 2;
                break;
            case C:
                rent += 3;
                break;
            case D:
                throw new ArithmeticException("Kunder med kredit vurdering D, supporteres ikke");
        }
        this.aaop = rent;
    }

    private int udbetalingsRate(double udbetaling, double pris)
    {
        if (udbetaling < (pris / 2)) {
            return 1;
        } else {
            return 0;
        }

    }

    //evt jUnit
    public void testUdbetalingsRate()
    {
        assertEquals(0, 0);
    }
    



    private void calcTerminRent()
    {
        terminRent = Math.pow(1+((double) aaop/100), 1.0/12)-1;
    }

    private void calcYdelse()
    {
        double potensen = (loebetid-(loebetid*2));
        double mellemstykke = 1-Math.pow((1+terminRent), potensen);
        ydelse = (pris-udbetaling)*(terminRent/mellemstykke);
        totalYdelse = ydelse*loebetid;
    }

    private void fillStats()
    {
        double afbetalt = 0;

        for (int i = 1; i < loebetid+1; i++) {
            Stats s = new Stats();
            s.setMdr(i);
            s.setAfbetalt(afbetalt += ydelse);
            s.setResterende((ydelse*loebetid)-(i*ydelse));
            stats.add(s);
        }        
    }

    public class Stats {
    
        private int mdr;
        private double resterende;
        private double afbetalt;

        public int getMdr() {
            return mdr;
        }

        public void setMdr(int mdr) {
            this.mdr = mdr;
        }

        public double getResterende() {
            return resterende;
        }

        public void setResterende(double resterende) {
            this.resterende = resterende;
        }

        public double getAfbetalt() {
            return afbetalt;
        }

        public void setAfbetalt(double afbetalt) {
            this.afbetalt = afbetalt;
        }
        
    }

    public int getAaop() {
        return aaop;
    }

    public double getYdelse() {
        return ydelse;
    }

    public ArrayList<Stats> getStats() {
        return stats;
    }

    public double getTotalYdelse() {
        return totalYdelse;
    }
}