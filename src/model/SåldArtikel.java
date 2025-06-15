package model;

import model.DTO.ArtikelDTO;
import model.DTO.SåldArtikelDTO;

/**
 * Representerar en artikel som har sålts under en försäljning.
 * Innehåller referens till artikelns information och antal sålda enheter.
 */
public class SåldArtikel {
    private ArtikelDTO artikelDTO;
    private int mängdSålt;
    private final int STARTING_QUANTITY = 1;

    /**
     * Skapar ett nytt {@code SåldArtikel}-objekt med en såld enhet som startvärde.
     *
     * @param artikelDTO Objekt som innehåller information om artikeln.
     */
    public SåldArtikel(ArtikelDTO artikelDTO){
        this.artikelDTO = artikelDTO;
        this.mängdSålt = STARTING_QUANTITY;
    }

    /**
     * Returnerar artikelinformationen för den sålda artikeln.
     *
     * @return Ett {@code ArtikelDTO}-objekt med artikeldata.
     */
    public ArtikelDTO getArtikelDTO(){
        return artikelDTO;
    }

    /**
     * Returnerar hur många enheter av artikeln som har sålts.
     *
     * @return Antalet sålda enheter.
     */
    public int getMängdSålt(){
        return mängdSålt;
    }

    /**
     * Ökar antalet sålda enheter med 1.
     * Denna metod används när samma artikel skannas flera gånger.
     */
    public void ökaMängdSåltMedEn(){
        mängdSålt += 1;
    }

    /**
     * Ökar antalet sålda enheter med ett angivet belopp.
     *
     * @param belopp Antalet enheter som ska läggas till.
     */
    public void läggTillBelopp(int belopp){
        mängdSålt += belopp;
    }

    public SåldArtikelDTO toDTO() {
    return new SåldArtikelDTO(
        this.getArtikelDTO().getartikelID(),
        this.getArtikelDTO().getnamn(),
        this.getArtikelDTO().getartikelPris(),
        this.getArtikelDTO().getVAT(),
        this.getMängdSålt()
        );
    }



}
