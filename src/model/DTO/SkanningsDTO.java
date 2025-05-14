package model.DTO;

public class SkanningsDTO {
    private String artikelID;
    private float artikelPris;
    private int VAT;
    private String artikelBeskrivning;
    private int antalAvArtikel;
    private String artikelNamn;

    /**
     * Konstruktor för att skapa ett SkanningsDTO-objekt.
     * @param artikelID Artikelns ID
     * @param VAT Moms för artikeln
     * @param artikelPris Pris för artikeln
     * @param artikelBeskrivning Beskrivning av artikeln
     * @param antalAvArtikel Antal artiklar som registrerats
     * @param artikelNamn Namn på artikeln
     */
    public SkanningsDTO(String artikelID,
                        int VAT,
                        float artikelPris,
                        String artikelBeskrivning,
                        int antalAvArtikel,
                        String artikelNamn) {
        this.artikelID = artikelID;
        this.VAT = VAT;
        this.artikelPris = artikelPris;
        this.artikelBeskrivning = artikelBeskrivning;
        this.antalAvArtikel = antalAvArtikel;
        this.artikelNamn = artikelNamn;
    }

    /**
     * Hämtar artikelns ID.
     * @return Artikelns ID
     */
    public String getArtikelID() {
        return artikelID;
    }

    /**
     * Hämtar artikelns pris.
     * @return Artikelns pris
     */
    public float getArtikelPris() {
        return artikelPris;
    }

    /**
     * Hämtar moms (VAT) för artikeln.
     * @return Moms för artikeln
     */
    public int getVAT() {
        return VAT;
    }

    /**
     * Hämtar artikelns beskrivning.
     * @return Artikelns beskrivning
     */
    public String getArtikelBeskrivning() {
        return artikelBeskrivning;
    }

    /**
     * Hämtar antal artiklar som registrerats.
     * @return Antal av artikeln
     */
    public int getAntalAvArtikel() {
        return antalAvArtikel;
    }

    /**
     * Hämtar artikelns namn.
     * @return Artikelns namn
     */
    public String getArtikelNamn() {
        return artikelNamn;
    }
}
