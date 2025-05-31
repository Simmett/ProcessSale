package model;

/**
 * Representerar kassalådans saldo i ett kassasystem.
 * 
 * Klassen hanterar logiken för att hålla reda på den totala summan pengar i kassan.
 * Den används för att uppdatera kassasaldot när en försäljning genomförs och för att
 * hämta aktuellt saldo vid behov.
 * Klassen är avgränsad till det ekonomiska lagret i kassasystemet och hanterar
 * inte artiklar eller kvitton.
 */
public class KassaRegister {
    
    private float kassaSaldo;

    /**
     * Skapar ett nytt {@code KassaRegister}-objekt med ett angivet startbelopp.
     *
     * @param belopp Startbelopp för kassasaldot.
     */
    public KassaRegister(float belopp){
        this.kassaSaldo = belopp;
    }

    /**
     * Uppdaterar kassasaldot med ett angivet belopp.
     * Kan användas för att lägga till eller dra ifrån pengar i kassan.
     *
     * @param belopp Belopp att justera kassasaldot med. Positivt värde ökar saldot, 
     *               negativt värde minskar det.
     */
    public void uppdateraKassaSaldo(float belopp){
        this.kassaSaldo += belopp;
    }

    /**
     * Returnerar det aktuella saldot i kassaregistret.
     *
     * @return Nuvarande kassasaldo.
     */
    public float getSaldo(){
        return kassaSaldo;
    }
}
