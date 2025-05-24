package integration;

import java.util.ArrayList;
import java.util.List;

import model.DTO.ArtikelDTO;
import model.DTO.SkanningsDTO;
import model.SåldArtikel;

/**
 * ArtikelRegister ansvarar för att tillhandahålla information om artiklar 
 * baserat på deras ID. Det fungerar som ett hårdkodat register över artiklar 
 * i systemet.
 */
public class ArtikelRegister {
    private final List<ArtikelILager> lager = new ArrayList<>();
    private static final ArtikelRegister ARTIKEL_REGISTER = new ArtikelRegister();


    private ArtikelRegister(){
        läggTillArtiklar();
    }

    public static ArtikelRegister getArtikelRegister(){
        return ARTIKEL_REGISTER;
    }

    private void läggTillArtiklar(){
        lager.add(new ArtikelILager(new ArtikelDTO("BigWheel Oatmeal", 123, (float) 29.90, 6), 50 ));
        lager.add(new ArtikelILager(new ArtikelDTO("YouGoGo BlueBerry", 456, (float) 14.9, 6), 50));
    }

    public ArtikelDTO hämtaArtikelBeskrivning(int artikelID) throws ArtikelFinnsInteException{
        if(artikelID == 999){
            throw new LagerDatabasException("Databasen kan inte nås");
        }
        for(ArtikelILager artikelILager : lager){
            if(artikelILager.getArtikel().matcharArtikelID(artikelID)){
                return artikelILager.getArtikel();
            }
        }
        throw new ArtikelFinnsInteException(artikelID);
    }

    public void uppdateraLager(SkanningsDTO säljInformation){
        if(säljInformation == null){
            return;
        }
        for(SåldArtikel såldArtikel: säljInformation.getSåldaArtiklar()){
            uppdateraLagerMedArtikel(såldArtikel);

        }
    }

    private void uppdateraLagerMedArtikel(SåldArtikel såldArtikel){
        ArtikelDTO såldArtikelDTO = såldArtikel.getArtikelDTO();
        int mängdSålt = såldArtikel.getMängdSålt();

        ArtikelILager artikelILager = hämtaArtikelMedID(såldArtikelDTO.getartikelID());
        if(artikelILager != null){
            minskaLagerMängd(artikelILager, mängdSålt);
        }
    }

    public ArtikelILager hämtaArtikelMedID(int artikelID){
        for(ArtikelILager artikel : lager){
            if(artikel.getArtikel().matcharArtikelID(artikelID)){
                return artikel;
            }
        }
        return null;
    }

    private void minskaLagerMängd(ArtikelILager artikel, int mängdSålt){
        int nyMängd = artikel.getMängd() - mängdSålt;
        artikel.setMängd(nyMängd);
    }
   
}
