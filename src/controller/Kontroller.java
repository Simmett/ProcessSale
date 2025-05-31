package controller;

import java.util.ArrayList;
import java.util.List;
import model.KassaRegister;
import model.RevenueObserver;
import integration.ArtikelFinnsInteException;
import model.DTO.Kvitto;
import model.DTO.SkanningsDTO;
import integration.LagerDatabasException;
import model.DTO.ArtikelDTO;
import integration.Printer;
import integration.ArtikelRegister;
import integration.BokföringsRegister;
import model.Försäljning;

/**
 * Kontroller-klassen hanterar logiken för hela kassaflödet.
 * Den kopplar samman olika delar av systemet, såsom vy, kassa, och lager. 
 * Klassen är navet för att initiera och genomföra en försäljning.
 */
public class Kontroller {
    private ArtikelRegister artikelRegister;
    private Printer printer;
    private KassaRegister kassaRegister;
    private Försäljning försäljning;
    private List<RevenueObserver> revenueObservers = new ArrayList<>();
    private BokföringsRegister bokföringsRegister;

    /**
     * Skapar en ny Kontroller-instans med de nödvändiga beroendena.
     * 
     * @param bokföring       Bokföringssystemet som ska användas.
     * @param artikelRegister  Register med artikelinformation.
     * @param printer         Printer för utskrift av kvitton.
     */
    public Kontroller(BokföringsRegister bokföring, ArtikelRegister artikelRegister, Printer printer){
        this.artikelRegister = artikelRegister;
        this.kassaRegister = new KassaRegister(0);
        this.printer = printer;
        this.bokföringsRegister = bokföring;
    }

    /**
     * Startar en ny försäljning.
     */
    public void startaFörsäljning(){
        försäljning = new Försäljning();
        försäljning.addRevenueObservers(revenueObservers);
    }

    /**
     * Skannar en artikel baserat på dess ID.
     * 
     * @param artikelID ID för artikeln som ska skannas.
     * @return En DTO med aktuell försäljningsinformation efter skanningen.
     * @throws ArtikelFinnsInteException Om artikeln inte finns i registret.
     * @throws SystemOperationFailureException Om ett systemfel uppstår, exempelvis databasfel.
     */
    public SkanningsDTO skannaArtikel(int artikelID) throws ArtikelFinnsInteException, SystemOperationFailureException{
        try {
            ArtikelDTO artikelDTO = artikelRegister.hämtaArtikelBeskrivning(artikelID);
            försäljning.läggTillArtiklar(artikelDTO);
            return försäljning.getSkanningsDTO();
        } catch (LagerDatabasException exc){
            throw new SystemOperationFailureException("Kan inte nå lagerdatabasen", exc);
        }
    }

    /**
     * Anger mängd för den senaste skannade artikeln.
     * 
     * @param mängd Den mängd som ska sättas.
     * @return En DTO med aktuell försäljningsinformation efter uppdateringen.
     */
    public SkanningsDTO angeMängd(int mängd){
        försäljning.justeraMängdAvSenasteArtikel(mängd);
        return försäljning.getSkanningsDTO();
    }

    /**
     * Avslutar försäljningen och returnerar en DTO med försäljningsinformationen.
     * 
     * @return En DTO med aktuell försäljningsinformation.
     */
    public SkanningsDTO avslutaFörsäljning(){
        return försäljning.getSkanningsDTO();
    }

    /**
     * Hanterar betalning för försäljningen, uppdaterar lager och kassa, bokför samt skriver ut kvitto.
     * 
     * @param belopp Det belopp kunden betalar.
     * @return Ett kvitto för försäljningen.
     */
    public Kvitto betala(float belopp){
        Kvitto kvitto = försäljning.betala(belopp);
        SkanningsDTO skanningsDTO = försäljning.getSkanningsDTO();
        artikelRegister.uppdateraLager(skanningsDTO);
        kassaRegister.uppdateraKassaSaldo(belopp);
        bokföringsRegister.bokföra(kvitto);
        printer.skrivUtKvitto(kvitto);
        return kvitto;
    }

    /**
     * Lägger till en RevenueObserver som observerar totalomsättning.
     * 
     * @param observer Observer som ska läggas till.
     */
    public void addRevenueObserver(RevenueObserver observer){
        revenueObservers.add(observer);
    }
}
