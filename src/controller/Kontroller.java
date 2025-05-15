package controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import model.KassaRegister;
import integration.Kassa;
import view.View;
import model.DTO.Kvitto;
import integration.RabattSystem;
import integration.LagerData;
import model.DTO.ArtikelDTO;

/**
 * Kontroller-klassen hanterar logiken för hela kassaflödet.
 * Den kopplar samman olika delar av systemet, såsom vy, kassa, lager och rabatt.
 * Klassen är navet för att initiera och genomföra en försäljning.
 */
public class Kontroller {
    private RabattSystem rabattSystem = new RabattSystem();
    private Kassa kassa = new Kassa();
    private View view = new View();
    private KassaRegister kassaRegister = new KassaRegister(kassa);
    private LagerData lagerData = new LagerData();

    /**
     * Hanterar hela kassa-flödet, inklusive att sätta tid för försäljning, 
     * lägga till artiklar, tillämpa rabatt, beräkna växel och skriva ut kvitto.
     * Metoden styr hela processen från att starta försäljningen till att avsluta den.
     */
    public void hanteraKassaFlöde() {
        LocalTime tid = kassaRegister.setTimeOfSale();
        view.startaFörsäljning(tid);

        List<ArtikelDTO> artikelInformation = view.artikelInformation();

        läggTillArtiklar(artikelInformation);

        int kundID = view.kundID();

        float nyttpris = flaggaRabatt(kundID);

        float betalatBelopp = view.betalatBelopp();

        float växel = processSale(nyttpris, betalatBelopp);

        Kvitto kvitto = skapaKvitto(betalatBelopp, nyttpris, växel);

        kassa.uppdateraKassaSaldo(växel);

        lagerData.uppdateraLager(kvitto);

        view.skrivUtKvitto(kvitto);

        view.avslutaFörsäljning();
    }

    private float processSale(float nyttPris, float betalatBelopp) {
        float växel = kassaRegister.beräknaVäxel(betalatBelopp, nyttPris);
        view.skrivUtVäxel(växel);
        return växel;
    }

    private float flaggaRabatt(int kundID) {
        float totalPris = (float) kassaRegister.getTotalPris(); 
        float nyttPris = rabattSystem.rabattKontroll(totalPris, kundID); 
    
        float rabatt = totalPris - nyttPris;
        if (rabatt > 0) {
            view.skrivUtRabatt(rabatt);
        } else {
            view.ingenRabatt();
        }
    
        return nyttPris;
    }

    private Kvitto skapaKvitto(float betalatBelopp, float nyttPris, float växel) {
        float totalPris = (float) kassaRegister.getTotalPris();
        LocalDate datum = LocalDate.now();
        float rabatt = totalPris - nyttPris;
        LocalTime tidförsäljning = LocalTime.now();

        return new Kvitto(tidförsäljning, totalPris, kassaRegister.calculateTotalVAT(),
                          betalatBelopp, kassaRegister.artiklarSomString(), växel,
                          datum, nyttPris, rabatt);
    }

    private void läggTillArtiklar(List<ArtikelDTO> artikelLista) {
        for (ArtikelDTO artikelDTO : artikelLista) {
            kassaRegister.artikelIDOchAntal(artikelDTO.getartikelID(), artikelDTO.getantalAvArtikel());
        }
    }
}
