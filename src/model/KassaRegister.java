package model;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import integration.ArtikelFinnsInteException;
import integration.ArtikelRegister;
import integration.Kassa;
import integration.LagerDatabasException;
import model.DTO.ArtikelDTO;
import model.DTO.SkanningsDTO;

/**
 * KassaRegister hanterar försäljningslogik för ett kassasystem.
 * 
 * Denna klass ansvarar för att:
 * - Skanna och registrera artiklar
 * - Hålla reda på alla artiklar som är del av en försäljning
 * - Beräkna totalpris, moms och växel
 * - Sammanställa artikellista till kvittot
 * - Hantera tidsstämpling av försäljningen
 * 
 * Klassen fungerar som en central nod i modellen (MVC) och använder både ArtikelRegister och Kassa.
 */

public class KassaRegister {
    
    private LocalTime tidförsäljning;
    private List<ArtikelDTO> artiklellista = new ArrayList<>();
    private ArtikelRegister artikelRegister = new ArtikelRegister();
    private Kassa kassa;
    private RevenueNotifier revenueNotifier = new RevenueNotifier();

    /**
     * Konstruktor som initialiserar kassa och printer för KassaRegister.
     * @param kassa Kassa för uppdatering av saldo
     */
    public KassaRegister(Kassa kassa){
        this.kassa = kassa;
    }

    /**
     * Sätter tiden för försäljningen.
     * @return Tiden då försäljningen genomfördes.
     */
    public LocalTime setTimeOfSale() {
        tidförsäljning = LocalTime.now();
        return tidförsäljning;
    }

    /**
     * Lägg till artikel i kassa och returnera SkanningsDTO för den.
     * Om artikeln redan finns, uppdateras antalet.
     * @param artikelID Artikelns ID
     * @param antalAvArtikel Antal av artikeln som läggs till
     * @return SkanningsDTO för artikeln
     */
    public SkanningsDTO artikelIDOchAntal(String artikelID, int antalAvArtikel) 
    throws ArtikelFinnsInteException, LagerDatabasException {

    ArtikelDTO artikelInfo = artikelRegister.hämtaArtikelInformation(artikelID, antalAvArtikel);

    for (ArtikelDTO artikel : artiklellista) {
        if (artikel.getartikelID().equals(artikelInfo.getartikelID())) {
            artikel.ökaAntal(antalAvArtikel);
            return skapaSkanningsDTO(artikel);
        }
    }

    artiklellista.add(artikelInfo);
    return skapaSkanningsDTO(artikelInfo);
   }



    /**
     * Skapar ett SkanningsDTO från en artikel.
     * @param artikel Artikel att skapa DTO för
     * @return SkanningsDTO för artikeln
     */
    private SkanningsDTO skapaSkanningsDTO(ArtikelDTO artikel) {
        return new SkanningsDTO(
            artikel.getartikelID(),
            artikel.getVAT(),
            (float) artikel.getartikelPris(),  
            artikel.getartikelBeskrivning(),
            artikel.getantalAvArtikel(),
            artikel.getArtikelNamn()
        );
    }

    /**
     * Beräknar och hämtar det totala priset för alla artiklar i kassan.
     * @return Totalt pris för alla artiklar i kassan
     */
    public double getTotalPris(){
        float totalPris = 0;
        for(ArtikelDTO artikel : artiklellista){
            totalPris += artikel.getartikelPris() * artikel.getantalAvArtikel();
        }
        return totalPris;
    }

    /**
     * Beräknar växel som ska ges tillbaka till kunden.
     * @param betalatBelopp Beloppet kunden betalat
     * @param nyttPris Det nya priset efter rabatt
     * @return Växel att ge tillbaka
     */
    public float beräknaVäxel(float betalatBelopp, float nyttPris) {
        return betalatBelopp - nyttPris;
    }
   
    /**
     * Beräknar den totala momsen (VAT) för alla artiklar i kassan.
     * @return Total moms (VAT) för artiklarna
     */
    public float calculateTotalVAT() {
        float totalVAT = 0;
        for (ArtikelDTO artikel : artiklellista) {
            totalVAT += artikel.getartikelPris() * (artikel.getVAT() / 100.0f);  // Beräkna korrekt VAT
        }
        return totalVAT;
    }

    /**
     * Skapar en sträng med information om alla artiklar i kassan.
     * @return Sträng med alla artiklar i kassan
     */
    public String artiklarSomString() {
        StringBuilder sb = new StringBuilder();
        for (ArtikelDTO artikel : artiklellista) {
            sb.append(artikel.getartikelID()).append(" x").append(artikel.getantalAvArtikel()).append(", ");
        }
        return sb.toString();
    }

    /**
     * Avslutar försäljningen och returnerar det nya priset.
     * @param nyttPris Det nya priset efter eventuell rabatt
     * @return Det nya priset
     */
    public float försäljningsAvslut(float nyttPris){
        revenueNotifier.notifyObservers(nyttPris);  
        return nyttPris;
    }
   
    /**
     * Registrerar en ny intäktsobservatör som kommer att notifieras vid varje försäljningsavslut.
     *
     * @param observer Ett objekt som implementerar {@link RevenueObserver}-interfacet.
     */
    public void addRevenueObserver(RevenueObserver observer) {
    revenueNotifier.addObserver(observer);
}
    

    

}