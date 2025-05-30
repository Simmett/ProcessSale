package test.model;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import model.Försäljning;
import model.DTO.ArtikelDTO;
import model.DTO.Kvitto;
import model.DTO.SkanningsDTO;


import java.util.List;

public class FörsäljningTest {

    private Försäljning försäljning;
    private ArtikelDTO artikel1;
    private ArtikelDTO artikel2;

    @BeforeEach
    public void setup() {
        försäljning = new Försäljning();
        artikel1 = new ArtikelDTO("Testartikel", 1, 100.0f, 25.0f);
        artikel2 = new ArtikelDTO("Testartikel", 1, 100.0f, 25.0f);

    }

    @Test
    public void testLäggTillArtiklar_OchTotalPris() {
        försäljning.läggTillArtiklar(artikel1);
        försäljning.läggTillArtiklar(artikel2);
        försäljning.läggTillArtiklar(artikel1);  

        SkanningsDTO dto = försäljning.getSkanningsDTO();
        float expectedTotal = 2 * artikel1.getartikelPris() + artikel2.getartikelPris();

        assertEquals(expectedTotal, dto.getTotalPris(), 0.01f);
        assertEquals(3, dto.getSåldaArtiklar().stream().mapToInt(a -> a.getMängdSålt()).sum());
    }

    @Test
    public void testJusteraMängdAvSenasteArtikel() {
        försäljning.läggTillArtiklar(artikel1);
        försäljning.läggTillArtiklar(artikel2);

    
        försäljning.justeraMängdAvSenasteArtikel(4);

        SkanningsDTO dto = försäljning.getSkanningsDTO();

      
        float expectedTotal = artikel1.getartikelPris() + 4 * artikel2.getartikelPris();

        assertEquals(expectedTotal, dto.getTotalPris(), 0.01f);
        assertEquals(5, dto.getSåldaArtiklar().stream().mapToInt(a -> a.getMängdSålt()).sum());
    }

    @Test
    public void testBetala_SkaparKvittoMedRättBelopp() {
        försäljning.läggTillArtiklar(artikel1);
        försäljning.läggTillArtiklar(artikel2);
        försäljning.justeraMängdAvSenasteArtikel(3); 

        SkanningsDTO dtoFöreBetalning = försäljning.getSkanningsDTO();

        float betaltBelopp = 250f;
        Kvitto kvitto = försäljning.betala(betaltBelopp);

        assertNotNull(kvitto);
        assertEquals(dtoFöreBetalning.getTotalPris(), kvitto.getTotalPris(), 0.01f);
        assertEquals(betaltBelopp, kvitto.getBetalatBelopp(), 0.01f);
        assertEquals(betaltBelopp - kvitto.getTotalPris(), kvitto.getVäxel(), 0.01f);

        
        List<?> artiklar = kvitto.getSåldaArtiklar();
        int totalMängd = artiklar.stream()
            .mapToInt(a -> ((model.SåldArtikel) a).getMängdSålt())
            .sum();
        assertEquals(4, totalMängd);
    }
}
