package startup;

import controller.Kontroller;

public class Main {
    /**
     * Huvudmetoden som startar applikationen.
     * Skapar nödvändiga objekt och anropar metoden för att hantera kassa-flödet.
     * @param args Kommando-radsargument (ej använda i denna implementation)
     */
    public static void main(String[] args) {
        
        Kontroller controller = new Kontroller();  
        
        controller.hanteraKassaFlöde();  

    }
}
