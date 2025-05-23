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
 * Den kopplar samman olika delar av systemet, såsom vy, kassa, lager och rabatt.
 * Klassen är navet för att initiera och genomföra en försäljning.
 */
public class Kontroller {
    private ArtikelRegister artikelRegister;
    private Printer printer;
    private KassaRegister kassaRegister;
    private Försäljning försäljning;
    private List<RevenueObserver> revenueObservers = new ArrayList<>();
    private BokföringsRegister bokföringsRegister;

    public Kontroller(BokföringsRegister bokföring, ArtikelRegister artikelRegister, Printer printer){
        this.artikelRegister = artikelRegister;
        this.kassaRegister = new KassaRegister(0);
        this.printer = printer;
        this.bokföringsRegister = bokföring;
    }

    public void startaFörsäljning(){
        försäljning = new Försäljning();
        försäljning.addRevenueObservers(revenueObservers);
    }

    public SkanningsDTO skannaArtikel(int artikelID) throws ArtikelFinnsInteException, SystemOperationFailureException{
        try {
            ArtikelDTO artikelDTO = artikelRegister.hämtaArtikelBeskrivning(artikelID);
            försäljning.läggTillArtiklar(artikelDTO);
            return försäljning.getSkanningsDTO();
        } catch (LagerDatabasException exc){
            throw new SystemOperationFailureException("Kan inte nå lagerdatabasen", exc);
        }

    }

    public SkanningsDTO angeMängd(int mängd){
        försäljning.justeraMängdAvSenasteArtikel(mängd);
        SkanningsDTO nuvarandeSkanningsDTO = försäljning.getSkanningsDTO();
        return nuvarandeSkanningsDTO;
    }

    public SkanningsDTO avslutaFörsäljning(){
        SkanningsDTO skanningsDTO = försäljning.getSkanningsDTO();
        return skanningsDTO;
    }

    public Kvitto betala(float belopp){
        Kvitto kvitto = försäljning.betala(belopp);
        SkanningsDTO skanningsDTO = försäljning.getSkanningsDTO();
        artikelRegister.uppdateraLager(skanningsDTO);
        kassaRegister.uppdateraKassaSaldo(belopp);
        bokföringsRegister.bokföra(kvitto);
        printer.skrivUtKvitto(kvitto);
        return kvitto;
    }

    public void addRevenueObserver(RevenueObserver observer){
        revenueObservers.add(observer);
    }



}