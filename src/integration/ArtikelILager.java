package integration;

import model.DTO.ArtikelDTO;

/**
 * Representerar ett objekts status i lagret, inklusive artikelinformation och mängd.
 */
public class ArtikelILager {
    private ArtikelDTO artikel;
    private int mängd;

    /**
     * Skapar en ny instans av en artikel i lagret.
     * 
     * @param artikel  Artikeln som lagret håller reda på
     * @param mängd    Antal av artikeln i lagret
     */
    public ArtikelILager(ArtikelDTO artikel, int mängd) {
        this.artikel = artikel;
        this.mängd = mängd;
    }
    
    /**
     * Hämtar artikeln.
     * 
     * @return ArtikelDTO med artikelns data
     */
    public ArtikelDTO getArtikel(){
        return artikel;
    }
    
    /**
     * Hämtar mängden av artikeln i lagret.
     * 
     * @return antal i lager
     */
    public int getMängd(){
        return mängd;
    }
    
    /**
     * Sätter mängden av artikeln i lagret.
     * 
     * @param mängd nytt antal i lager
     */
    public void setMängd(int mängd){
        this.mängd = mängd;
    }
}
