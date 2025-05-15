package integration;

/**
 * Denna exception kastas när en artikel med ett angivet ID inte finns i lagret.
 */
public class ArtikelFinnsInteException extends Exception {

    /**
     * Skapar en ny instans av undantaget med ett felmeddelande som beskriver felet.
     *
     * @param artikelID Det artikel-ID som inte kunde hittas.
     */
    public ArtikelFinnsInteException(String artikelID) {
        super("Artikel med ID \"" + artikelID + "\" finns inte.");
    }
}
