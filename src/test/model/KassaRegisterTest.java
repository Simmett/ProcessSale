package test.model;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import integration.ArtikelFinnsInteException;
import integration.LagerDatabasException;
import integration.Kassa;
import model.DTO.SkanningsDTO;
import model.KassaRegister;

public class KassaRegisterTest {

    private KassaRegister kassaRegister;

    @BeforeEach
    public void setup() {
        // Mock eller riktig instans av Kassa, beroende på implementation
        Kassa kassa = new Kassa();
        kassaRegister = new KassaRegister(kassa);
    }

    @Test
    public void testArtikelRegistrering_LäggTillNyArtikel_SkallLyckas() throws ArtikelFinnsInteException, LagerDatabasException {
        SkanningsDTO dto = kassaRegister.artikelIDOchAntal("abc123", 2);
        assertEquals("abc123", dto.getArtikelID());
        assertEquals(2, dto.getAntalAvArtikel());
        assertEquals("BigWheel Oatmeal", dto.getArtikelNamn());
    }

    @Test
    public void testArtikelRegistrering_LäggTillSammaArtikel_UppdaterarAntal() throws ArtikelFinnsInteException, LagerDatabasException {
        kassaRegister.artikelIDOchAntal("abc123", 2);
        SkanningsDTO dto = kassaRegister.artikelIDOchAntal("abc123", 3);
        assertEquals(5, dto.getAntalAvArtikel());  
    }

    @Test
    public void testGetTotalPris_SkaGeKorrektSumma() throws ArtikelFinnsInteException, LagerDatabasException {
        kassaRegister.artikelIDOchAntal("abc123", 2);
        kassaRegister.artikelIDOchAntal("def456", 1); 
        double total = kassaRegister.getTotalPris();
        assertEquals(29.90 * 2 + 14.90, total, 0.001);
    }

    @Test
    public void testBeräknaVäxel_SkaGeKorrektVäxel() {
        float växel = kassaRegister.beräknaVäxel(100f, 75.50f);
        assertEquals(24.5f, växel, 0.001f);
    }

    @Test
    public void testCalculateTotalVAT_SkaGeKorrektMoms() throws ArtikelFinnsInteException, LagerDatabasException {
       
        kassaRegister.artikelIDOchAntal("abc123", 2); 
        kassaRegister.artikelIDOchAntal("def456", 1); 
        float expectedVAT = (29.90f * 2 + 14.90f) * 0.06f;
        float actualVAT = kassaRegister.calculateTotalVAT();
        assertEquals(expectedVAT, actualVAT, 0.001f);
    }

    @Test
    public void testArtikelFinnsInteException_SkaKastas() {
        assertThrows(ArtikelFinnsInteException.class, () -> {
            kassaRegister.artikelIDOchAntal("icke-existerande", 1);
        });
    }

    @Test
    public void testLagerDatabasException_SkaKastas() {
        assertThrows(LagerDatabasException.class, () -> {
            kassaRegister.artikelIDOchAntal("999", 1); 
        });
    }
}
