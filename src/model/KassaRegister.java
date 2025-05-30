package model;

/**
 * KassaRegister hanterar försäljningslogik för ett kassasystem.
 * 
 * Denna klass ansvarar för att:
 * - Skanna och registrera artiklar
 * - Hålla reda på alla artiklar som är del av en försäljning
 * - Beräkna totalpris, moms och växel
 * - Sammanställa artikellista till kvittot
 * - Hantera tidsstämpling av försäljningen
 * 
 */
public class KassaRegister {
    
    private float kassaSaldo;

    /**
     * Skapar ett nytt KassaRegister med ett initialt kassasaldo.
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
     * @param belopp Belopp att justera kassasaldot med. Positivt för insättning, negativt för uttag.
     */
    public void uppdateraKassaSaldo(float belopp){
        this.kassaSaldo += belopp;
    }

    /**
     * Hämtar det aktuella kassasaldot.
     * 
     * @return Det aktuella saldot i kassan.
     */
    public float getSaldo(){
        return kassaSaldo;
    }
}
