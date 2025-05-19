package test.integration;

import model.DTO.ArtikelDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import integration.ArtikelRegister;
import integration.ArtikelFinnsInteException;
import integration.LagerDatabasException;
/**
 * Testklass för ArtikelRegister.
 */
public class ArtikelRegisterTest {

    private ArtikelRegister artikelRegister;

    @BeforeEach
    public void setUp() {
        artikelRegister = new ArtikelRegister();
    }

    @Test
    public void testHamtaArtikelInformation_abc123() throws ArtikelFinnsInteException {
        ArtikelDTO artikel = artikelRegister.hämtaArtikelInformation("abc123", 2);
        assertEquals("abc123", artikel.getartikelID());
        assertEquals("BigWheel Oatmeal", artikel.getArtikelNamn());
        assertEquals(29.90, artikel.getartikelPris());
        assertEquals(6, artikel.getVAT());
        assertEquals("BigWheel Oatmeal 500 g, whole grain oats, high fiber, gluten free", artikel.getartikelBeskrivning());
        assertEquals(2, artikel.getantalAvArtikel());
    }

    @Test
    public void testHamtaArtikelInformation_def456() throws ArtikelFinnsInteException {
        ArtikelDTO artikel = artikelRegister.hämtaArtikelInformation("def456", 1);
        assertEquals("def456", artikel.getartikelID());
        assertEquals("YouGoGo Blueberry", artikel.getArtikelNamn());
        assertEquals(14.90, artikel.getartikelPris());
        assertEquals(6, artikel.getVAT());
        assertEquals("YouGoGo Blueberry 240 g, low sugar yoghurt, blueberry flavour", artikel.getartikelBeskrivning());
        assertEquals(1, artikel.getantalAvArtikel());
    }

    @Test
    public void testHamtaArtikelInformation_invalidID_throwsArtikelFinnsInteException() {
        assertThrows(ArtikelFinnsInteException.class, () -> {
            artikelRegister.hämtaArtikelInformation("invalidID", 1);
        });
    }

    @Test
    public void testHamtaArtikelInformation_dbError_throwsLagerDatabasException() {
        assertThrows(LagerDatabasException.class, () -> {
            artikelRegister.hämtaArtikelInformation("999", 1);
        });
    }
}
