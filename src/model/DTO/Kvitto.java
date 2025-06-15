package model.DTO;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import model.SåldArtikel;
import model.Betalning;

/**
 * DTO som representerar ett kvitto på en slutförd försäljning.
 */
public class Kvitto {
    private LocalDateTime tidFörsäljning;
    private float totalPris;
    private float totalVAT;
    private List<SåldArtikel> listaMedArtiklar;
    private float betalatBelopp;
    private float växel;

    /**
     * Skapar ett kvitto med information från skanning och betalning.
     * 
     * @param skanningsDTO DTO med försäljningsdata
     * @param betalning Objekt som innehåller betalningsdata
     */
    public Kvitto (SkanningsDTO skanningsDTO, Betalning betalning){
        this.totalPris = skanningsDTO.getTotalPris();
        this.betalatBelopp = betalning.getBelopp();
        this.totalVAT = skanningsDTO.getVAT();
        this.listaMedArtiklar = new ArrayList<>();
        for (SåldArtikelDTO dto : skanningsDTO.getSåldaArtikelDTOs()) {
            this.listaMedArtiklar.add(new SåldArtikel(new ArtikelDTO(dto.getNamn(),dto.getArtikelID(), dto.getPris(), dto.getVAT())));
        }
        this.tidFörsäljning = skanningsDTO.getTid();
        beräknaVäxel();
    }

    /**
     * Räknar ut växel som ska ges tillbaka till kund.
     */
    private void beräknaVäxel(){
        float växel = totalPris - betalatBelopp;
        this.växel = växel;
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
     * Hämtar växeln som ska ges tillbaka till kunden.
     * 
     * @return Växeln
     */ 
    public float getVäxel() { 
        return växel; 
    }

    /**
     * Returnerar en lista med sålda artiklar i form av DTO:er.
     * 
     * @return Lista av SåldArtikelDTO
     */
    public List<SåldArtikelDTO> getSåldaArtikelDTOs() {
        List<SåldArtikelDTO> dtoList = new ArrayList<>();
        for (SåldArtikel artikel : listaMedArtiklar) {
            dtoList.add(artikel.toDTO());
        }
        return dtoList;
    }
}