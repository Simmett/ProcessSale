package integration;

import model.DTO.Kvitto;

/**
 * Printer ansvarar för utskrift av kvitton.
 * 
 * Klassen hanterar kommunikationen med den fysiska eller virtuella
 * skrivaren för att skriva ut information om en genomförd försäljning.
 */
public class Printer {

    /**
     * Skapar en ny instans av Printer.
     */
    public Printer() {
        // Konstruktor utan särskild initialisering
    }

    /**
     * Skriver ut ett kvitto.
     * 
     * @param kvitto Kvittoobjekt som innehåller all information som ska skrivas ut
     */
    public void skrivUtKvitto(Kvitto kvitto) {
        // Metod för att skriva ut kvittot, kan implementeras senare
    }
}
