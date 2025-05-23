package integration;

import model.DTO.ArtikelDTO;
/**
 * This class represents an items status in the inventory.
 * 
 */
public class ArtikelILager {
    private ArtikelDTO artikel;
    private int mängd;

    /**
    * Creates a new instance of an item in the inventory
    * 
    * @param item       The item in the inventory
    * @param quantity   The quantity of the item in the inventory
    */
    public ArtikelILager(ArtikelDTO artikel, int mängd) {
        this.artikel = artikel;
        this.mängd = mängd;
    }
    
    public ArtikelDTO getArtikel(){
        return artikel;
    }
    
    public int getMängd(){
        return mängd;
    }
    
    public void setMängd(int mängd){
        this.mängd = mängd;
    }
    
    
    
}