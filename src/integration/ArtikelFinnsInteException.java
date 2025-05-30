package integration;

/**
 * Denna exception kastas när en artikel med ett angivet ID inte finns i lagret.
 */
public class ArtikelFinnsInteException extends Exception {
    private final int artikelID;
    
    /**
     * Skapar en ny instans med ett meddelande som anger
     * artikel-ID:t som inte finns i lagret.
     * 
     * @param artikelID Artikel-ID som inte hittades i lagret.
     */
    public ArtikelFinnsInteException(int artikelID){
        super("Artikel med ID: " + artikelID + " hittades inte i lagret");
        this.artikelID = artikelID;
    }

    /**
     * Hämtar artikel-ID:t som orsakade exception.
     * 
     * @return Artikel-ID som inte hittades.
     */
    public int hämtaFelArtikelID(){
        return this.artikelID;
    }
}
