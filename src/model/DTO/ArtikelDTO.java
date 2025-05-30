package model.DTO;

/**
 * ArtikelDTO innehåller information om en enskild artikel.
 * 
 * Klassen fungerar som ett Data Transfer Object (DTO) för att
 * överföra artikeldata mellan olika delar av systemet.
 */
public class ArtikelDTO {
    private final String namn;
    private final int artikelID;
    private final float artikelPris;
    private final float VAT;

    /**
     * Skapar en ny instans av ArtikelDTO.
     * 
     * @param namn       Artikelns namn
     * @param artikelID  Artikelns unika identifierare
     * @param artikelPris Artikelns pris
     * @param VAT        Artikelns moms (VAT) som procenttal (t.ex. 25.0 för 25%)
     */
    public ArtikelDTO(String namn, int artikelID, float artikelPris, float VAT) {
        this.namn = namn;
        this.artikelID = artikelID;
        this.artikelPris = artikelPris;
        this.VAT = VAT;
    }

    /**
     * Hämtar artikelns namn.
     * 
     * @return Artikelns namn
     */
    public String getnamn(){
        return namn;
    }

    /**
     * Hämtar artikelns ID.
     * 
     * @return Artikelns identifierare
     */
    public int getartikelID(){
        return artikelID;
    }

    /**
     * Hämtar artikelns moms (VAT).
     * 
     * @return Artikelns moms i procent
     */
    public float getVAT(){
        return VAT;
    }

    /**
     * Hämtar artikelns pris.
     * 
     * @return Artikelns pris
     */
    public float getartikelPris(){
        return artikelPris;
    }

    /**
     * Kontrollerar om ett sökt artikel-ID matchar detta objekts artikel-ID.
     * 
     * @param söktArtikelID Artikel-ID som ska jämföras
     * @return true om artikel-ID är samma, annars false
     */
    public boolean matcharArtikelID(int söktArtikelID){
        return this.artikelID == söktArtikelID;
    }
}
