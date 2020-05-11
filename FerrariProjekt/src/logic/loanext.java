package logic;

import FFL.Rating;
import logic.loan.Stats;

public class loanext {
    public static void main(String[] args) {
        loan l = new loan(1000000, 600000, 24, Rating.A, 10);
        System.out.println("ÅOP: "+l.getAaop());
        System.out.println("Ydelse "+l.getYdelse());
        System.out.println("Total ydelse "+l.getTotalYdelse());

        for (Stats itemStats : l.getStats()) {
            System.out.println(itemStats.getMdr());
            System.out.println(itemStats.getAfbetalt());
            System.out.println(itemStats.getResterende());
        }
    }
}