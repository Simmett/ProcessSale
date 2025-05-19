package startup;

import controller.Kontroller;
import util.TotalRevenueFileOutput;
import view.TotalRevenueView;

/**
 * Main-klassen representerar startpunkten för POS-applikationen.
 * 
 * Den ansvarar för att skapa nödvändiga objekt och starta försäljningsflödet
 * genom att anropa relevant metod i kontrollern.
 * 
 * Denna klass innehåller endast main-metoden och används för att initiera programkörningen.
 */

public class Main {
    /**
     * Huvudmetoden som startar applikationen.
     * Skapar nödvändiga objekt och anropar metoden för att hantera kassa-flödet.
     * @param args Kommando-radsargument (ej använda i denna implementation)
     */
    public static void main(String[] args) {

        Kontroller controller = new Kontroller();  

        controller.addRevenueObserver(new TotalRevenueView());
        controller.addRevenueObserver(new TotalRevenueFileOutput());
        
        controller.hanteraKassaFlöde();  

    }
}
