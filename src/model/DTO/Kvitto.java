package model.DTO;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Kvitto representerar ett kvitto för en genomförd försäljning. 
 * Det innehåller detaljer såsom tid och datum för försäljningen, 
 * totalpris före och efter rabatt, total moms, betalat belopp, 
 * lista av sålda artiklar, växel samt eventuell rabatt.
 * 
 * Används som en Data Transfer Object (DTO) mellan modell och presentation.
 */

public class Kvitto {
    private LocalTime tidsförsäljning;         
    private float totalpris; 
    private float totalVat;
    private float betalatBelopp;
    private String listaAvArtiklar;
    private float växel;
    private LocalDate datum; 
    private float nyttPris;  
    private float rabatt;      
    
        /**
     * Skapar ett Kvitto-objekt med all nödvändig information om en försäljning.
     *
     * @param tidsförsäljning Tiden då försäljningen ägde rum
     * @param totalpris Det totala priset före eventuell rabatt
     * @param totalVat Den totala momsen för försäljningen
     * @param betalatBelopp Det belopp kunden betalade
     * @param listaAvArtiklar En textrepresentation av alla artiklar i försäljningen
     * @param växel Den växel som ska ges tillbaka till kunden
     * @param datum Datum för försäljningen
     * @param nyttPris Priset efter att rabatt har tillämpats
     * @param rabatt Den rabatt som har dragits från totalpriset
     */


    public Kvitto(  LocalTime tidsförsäljning,          
                    float totalpris,
                    float totalVat,
                    float betalatBelopp,
                    String listaAvArtiklar,
                    float växel,
                    LocalDate datum,
                    float nyttPris,
                    float rabatt) {

        this.tidsförsäljning = tidsförsäljning;
        this.totalpris = totalpris;
        this.totalVat = totalVat;
        this.betalatBelopp = betalatBelopp;
        this.listaAvArtiklar = listaAvArtiklar;
        this.växel = växel;
        this.datum = datum;
        this.nyttPris = nyttPris;
        this.rabatt = rabatt;
    }

    /**
     * Hämtar tiden för försäljningen.
     * @return Tiden för försäljningen
     */
    public LocalTime gettidFörsäljning() {
        return tidsförsäljning;
    }

    /**
     * Hämtar det totala priset för försäljningen.
     * @return Det totala priset
     */
    public float gettotalPris() {
        return totalpris;
    }

    /**
     * Hämtar den totala momsen för försäljningen.
     * @return Den totala momsen
     */
    public float gettotalVat() {
        return totalVat;
    }

    /**
     * Hämtar det belopp som kunden har betalat.
     * @return Det betalda beloppet
     */
    public float getbetalatBelopp() {
        return betalatBelopp;
    }

    /**
     * Hämtar listan av artiklar som köpts under försäljningen.
     * @return Listan av artiklar
     */
    public String getlistaAvArtiklar() {
        return listaAvArtiklar;
    }

    /**
     * Hämtar växeln som ska ges tillbaka till kunden.
     * @return Växeln
     */
    public float getväxel() {
        return växel;
    }

    /**
     * Hämtar datumet för försäljningen.
     * @return Datumet för försäljningen
     */
    public LocalDate getdatum() {
        return datum;
    }

    /**
     * Hämtar det nya priset efter tillämplig rabatt.
     * @return Det nya priset
     */
    public float getnyttPris() {
        return nyttPris;
    }

    /**
     * Hämtar rabatten som tillämpats på försäljningen.
     * @return Rabatten
     */
    public float getrabatt() {
        return rabatt;
    }
}
