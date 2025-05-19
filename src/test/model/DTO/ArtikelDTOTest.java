package test.model.DTO;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import model.DTO.ArtikelDTO;

class ArtikelDTOTest {

    @Test
    void testDefaultConstructorOchSettersOchGetters() {
        ArtikelDTO artikel = new ArtikelDTO();
        
        artikel.setArtikelID("abc123");
        artikel.setArtikelNamn("Testprodukt");
        artikel.setPris(49.90);
        artikel.setVAT(12);
        artikel.setBeskrivning("En testartikel");
        artikel.setAntal(5);

        assertEquals("abc123", artikel.getartikelID());
        assertEquals("Testprodukt", artikel.getArtikelNamn());
        assertEquals(49.90, artikel.getartikelPris());
        assertEquals(12, artikel.getVAT());
        assertEquals("En testartikel", artikel.getartikelBeskrivning());
        assertEquals(5, artikel.getantalAvArtikel());
    }

    @Test
    void testConstructorMedIDOchAntal() {
        ArtikelDTO artikel = new ArtikelDTO("def456", 3);

        assertEquals("def456", artikel.getartikelID());
        assertEquals(3, artikel.getantalAvArtikel());
    }

    @Test
    void testÖkaAntalMedPositivtVärde() {
        ArtikelDTO artikel = new ArtikelDTO("ghi789", 2);
        artikel.ökaAntal(3);

        assertEquals(5, artikel.getantalAvArtikel());
    }

    @Test
    void testÖkaAntalMedNegativtVärde() {
        ArtikelDTO artikel = new ArtikelDTO("ghi789", 2);
        artikel.ökaAntal(-1);

        assertEquals(2, artikel.getantalAvArtikel());
    }

    @Test
    void testÖkaAntalMedNoll() {
        ArtikelDTO artikel = new ArtikelDTO("ghi789", 2);
        artikel.ökaAntal(0);

       
        assertEquals(2, artikel.getantalAvArtikel());
    }
}
