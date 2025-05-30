package model;

import model.DTO.ArtikelDTO;
import model.DTO.SkanningsDTO;
import model.DTO.Kvitto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Klassen Försäljning hanterar en pågående försäljning i kassasystemet.
 * 
 * Den ansvarar för att:
 * - Lägga till artiklar till försäljningen
 * - Justera mängd för senaste artikeln
 * - Beräkna totalpris och moms (VAT)
 * - Skapa ett kvitto vid betalning
 * - Informera observerare om ny totalintäkt efter betalning
 */
public class Försäljning {
    private List<SåldArtikel> listaMedSåldaArtiklar;
    private List<RevenueObserver> revenueObservers = new ArrayList<>();
    private LocalDateTime tidFörsäljning;
    private float nuvarandeVAT;
    private float nuvarandePris;
    private final double CONVERT_TO_PERCENT = 0.01;

     /**
     * Skapar en ny försäljning och sätter tidpunkten för försäljningen.
     * Initierar även listan med sålda artiklar.
     */
    public Försäljning(){
        tidFörFörsäljning();
        this.listaMedSåldaArtiklar = new ArrayList<>();
    }

    private void tidFörFörsäljning(){
        this.tidFörsäljning = LocalDateTime.now();
    }

    private void uppdateraNuvarandePris(ArtikelDTO artikelDTO){
        nuvarandePris += artikelDTO.getartikelPris();
    }

    private void uppdateraNuvarandeVAT(ArtikelDTO artikelDTO){
        nuvarandeVAT += artikelDTO.getartikelPris () * (artikelDTO.getVAT() * CONVERT_TO_PERCENT);
    }

    /**
     * Lägger till en artikel i försäljningen.
     * Om artikeln redan finns ökas dess mängd med en.
     * Pris och moms uppdateras.
     * 
     * @param artikelDTO Dataöverföringsobjekt för artikeln som ska läggas till.
     */
    public void läggTillArtiklar(ArtikelDTO artikelDTO){
        for(SåldArtikel artikel : listaMedSåldaArtiklar){
            if(artikel.getArtikelDTO().getartikelID() == artikelDTO.getartikelID()){
                artikel.ökaMängdSåltMedEn();
                uppdateraNuvarandePris(artikelDTO);
                uppdateraNuvarandeVAT(artikelDTO);
                return;
            }
        }
        listaMedSåldaArtiklar.add(new SåldArtikel(artikelDTO));
        uppdateraNuvarandePris(artikelDTO);
        uppdateraNuvarandeVAT(artikelDTO);
    }

    /**
     * Justerar mängden av den senaste artikeln i försäljningen.
     * Uppdaterar pris och moms baserat på ny mängd.
     * 
     * @param mängd Ny total mängd av den senaste artikeln.
     */
    public void justeraMängdAvSenasteArtikel(int mängd){
        SåldArtikel senasteVara = getSenasteSåldaArtikel();
        int redanSkannad = 1;
        int mängdAttLäggaTill = mängd-redanSkannad;
        senasteVara.läggTillBelopp(mängdAttLäggaTill);

        justeraVAT(mängdAttLäggaTill, senasteVara.getArtikelDTO());
        justeraPris(mängdAttLäggaTill, senasteVara.getArtikelDTO());

    }

    private void justeraPris(int mängdAttLäggaTill, ArtikelDTO artikelDTO){
        nuvarandePris += artikelDTO.getartikelPris()*mängdAttLäggaTill;
    }

    private void justeraVAT(int mängdAttLäggaTill, ArtikelDTO artikelDTO){
        double artikelVAT = artikelDTO.getVAT()*CONVERT_TO_PERCENT;
        nuvarandeVAT += artikelDTO.getartikelPris()* artikelVAT * mängdAttLäggaTill;
    }
    
    /**
     * Hämtar ett DTO-objekt som innehåller information om försäljningen,
     * inklusive listan över sålda artiklar, tidpunkt, moms och pris.
     * 
     * @return Ett SkanningsDTO-objekt som representerar försäljningens data.
     */
    public SkanningsDTO getSkanningsDTO(){
        SkanningsDTO skanningsDTO = new SkanningsDTO(listaMedSåldaArtiklar, tidFörsäljning, nuvarandeVAT, nuvarandePris);
        return skanningsDTO;
    }

    private SåldArtikel getSenasteSåldaArtikel(){
        if(listaMedSåldaArtiklar.isEmpty()){
            return null;
        }
        return listaMedSåldaArtiklar.get(listaMedSåldaArtiklar.size()-1);
    }

    /**
     * Slutför försäljningen genom att ta emot ett betalningsbelopp,
     * skapar ett kvitto och notifierar observerare om ny totalintäkt.
     * 
     * @param belopp Det belopp som betalats av kunden.
     * @return Ett kvitto som sammanfattar försäljningen.
     */
    public Kvitto betala(float belopp){
        Betalning betalning = new Betalning(belopp);
        SkanningsDTO skanningsDTO = getSkanningsDTO();
        Kvitto kvitto = new Kvitto(skanningsDTO, betalning);
        notifyRevenueObservers();

        return kvitto;

    }

    private void addRevenueObserver(RevenueObserver observer){
        revenueObservers.add(observer);
    }
 
    /**
     * Lägger till flera RevenueObserver-objekt som vill bevaka
     * förändringar i totalintäkten.
     * 
     * @param observers Lista med observerare som ska läggas till.
     */   
    public void addRevenueObservers(List<RevenueObserver> observers){
        for(RevenueObserver observer : observers){
            addRevenueObserver(observer);
        }
    }
    
    private void notifyRevenueObservers(){
        for(RevenueObserver observer : revenueObservers){
            observer.newRevenue(nuvarandePris);
        }
    }

    
}

