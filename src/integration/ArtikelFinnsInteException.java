package integration;

/**
 * Denna exception kastas när en artikel med ett angivet ID inte finns i lagret.
 */
public class ArtikelFinnsInteException extends Exception {

    private final String artikelID;

    /**
     * Skapar en ny instans av undantaget med ett felmeddelande som beskriver felet.
     *
     * @param artikelID Det artikel-ID som inte kunde hittas.
     */
    public ArtikelFinnsInteException(String artikelID) {
        super("Artikel med ID \"" + artikelID + "\" finns inte.");
        this.artikelID = artikelID;
    }

    /**
     * Hämtar det artikel-ID som inte kunde hittas.
     *
     * @return Det saknade artikel-ID:t.
     */
    public String getArtikelID() {
        return artikelID;
    }
}
