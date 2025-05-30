package model.DTO;

import java.time.LocalDateTime;
import java.util.List;

import model.SåldArtikel;

/**
 * SkanningsDTO representerar data som genereras vid skanning av artiklar i en försäljningsprocess.
 * 
 * Klassen fungerar som ett Data Transfer Object (DTO) som överför försäljningsdata
 * mellan modell och vy utan att exponera underliggande affärslogik.
 * Den innehåller information om sålda artiklar, tidpunkt för försäljning,
 * total moms (VAT) samt totalpris.
 */
public class SkanningsDTO {
    private final LocalDateTime tid;
    private final float VAT;
    private final float totalPris;
    private final List<SåldArtikel> listaMedSåldaArtiklar;

    /**
     * Skapar ett nytt SkanningsDTO-objekt som representerar en försäljning.
     * 
     * @param såldaArtiklar Lista med sålda artiklar och deras mängd.
     * @param tid Tidpunkten för försäljningen.
     * @param VAT Total moms för försäljningen.
     * @param totalPris Total pris för försäljningen inklusive moms.
     */
    public SkanningsDTO(List<SåldArtikel> såldaArtiklar, LocalDateTime tid, float VAT, float totalPris) {
        this.listaMedSåldaArtiklar = såldaArtiklar;
        this.tid = tid;
        this.VAT = VAT;
        this.totalPris = totalPris;
    }
    
    /**
     * Hämtar det totala priset för försäljningen.
     * 
     * @return Det totala priset inklusive moms.
     */
    public float getTotalPris(){
        return totalPris;
    }
    
    /**
     * Hämtar den totala momsen (VAT) för försäljningen.
     * 
     * @return Det totala momsbeloppet.
     */
    public float getVAT(){
        return VAT;
    }
    
    /**
     * Hämtar listan med sålda artiklar.
     * 
     * @return Lista över sålda artiklar och deras antal.
     */
    public List<SåldArtikel> getSåldaArtiklar(){
        return listaMedSåldaArtiklar;
    }
    
    /**
     * Hämtar den senaste sålda artikeln i listan.
     * 
     * @return Den senaste sålda artikeln, eller null om listan är tom.
     */
    public SåldArtikel getSenasteSåldaArtikel(){
        if(listaMedSåldaArtiklar == null || listaMedSåldaArtiklar.isEmpty()){
            return null;
        }
        return listaMedSåldaArtiklar.get(listaMedSåldaArtiklar.size()-1);
    }
    
    /**
     * Hämtar tidpunkten för försäljningen.
     * 
     * @return Tidpunkten då försäljningen ägde rum.
     */
    public LocalDateTime getTid(){
        return tid;
    }    
}
