package model.DTO;

import java.time.LocalDateTime;
import java.util.List;

import model.SåldArtikel;
import model.Betalning;

/**
 * Kvitto representerar ett kvitto för en genomförd försäljning.
 * 
 * Klassen innehåller detaljer såsom tidpunkt för försäljningen, totalpris,
 * total moms, betalat belopp, lista över sålda artiklar samt växel.
 * 
 * Används som ett Data Transfer Object (DTO) för att överföra försäljningsinformation
 * från modell till presentation.
 */
public class Kvitto {
    private LocalDateTime tidFörsäljning;         
    private float totalPris; 
    private float totalVAT;
    private List<SåldArtikel> listaMedArtiklar;
    private float betalatBelopp;
    private float växel;    
    
    /**
     * Skapar ett Kvitto-objekt med all nödvändig information om en försäljning.
     * 
     * @param skanningsDTO DTO som innehåller försäljningsdata inklusive sålda artiklar, tid, pris och moms
     * @param betalning    Betalningsinformation som innehåller det betalda beloppet
     */
    public Kvitto(SkanningsDTO skanningsDTO, Betalning betalning){
        this.totalPris = skanningsDTO.getTotalPris();
        this.betalatBelopp = betalning.getBelopp();
        this.totalVAT = skanningsDTO.getVAT();
        this.listaMedArtiklar = skanningsDTO.getSåldaArtiklar();
        this.tidFörsäljning = skanningsDTO.getTid();
        beräknaVäxel();
    }

    /**
     * Beräknar växeln som ska ges tillbaka till kunden.
     * Växeln är skillnaden mellan betalat belopp och totalpris.
     */
    private void beräknaVäxel(){
        this.växel = betalatBelopp - totalPris;
    }

    /**
     * Hämtar tiden då försäljningen ägde rum.
     * 
     * @return Tidpunkten för försäljningen
     */
    public LocalDateTime getTidFörsäljning() {
        return tidFörsäljning;
    }

    /**
     * Hämtar det totala priset för försäljningen.
     * 
     * @return Det totala priset inklusive moms
     */
    public float getTotalPris() {
        return totalPris;
    }

    /**
     * Hämtar den totala momsen för försäljningen.
     * 
     * @return Den totala momsen (VAT)
     */
    public float getTotalVAT() {
        return totalVAT;
    }

    /**
     * Hämtar det belopp som kunden har betalat.
     * 
     * @return Det betalda beloppet
     */
    public float getBetalatBelopp() {
        return betalatBelopp;
    }

    /**
     * Hämtar listan av sålda artiklar under försäljningen.
     * 
     * @return Listan av sålda artiklar
     */
    public List<SåldArtikel> getSåldaArtiklar() {
        return listaMedArtiklar;
    }

    /**
     * Hämtar växeln som ska ges tillbaka till kunden.
     * 
     * @return Växeln
     */
    public float getVäxel() {
        return växel;
    }
}
