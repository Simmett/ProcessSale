package test.model.DTO;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import model.DTO.SkanningsDTO;

class SkanningsDTOTest {

    @Test
    void testConstructorAndGetters() {
       
        String artikelID = "A123";
        int vat = 12;
        float pris = 29.90f;
        String beskrivning = "Ekologisk havregryn 1kg";
        int antal = 2;
        String namn = "BigWheel Oatmeal";

     
        SkanningsDTO dto = new SkanningsDTO(artikelID, vat, pris, beskrivning, antal, namn);

        
        assertEquals(artikelID, dto.getArtikelID());
        assertEquals(vat, dto.getVAT());
        assertEquals(pris, dto.getArtikelPris());
        assertEquals(beskrivning, dto.getArtikelBeskrivning());
        assertEquals(antal, dto.getAntalAvArtikel());
        assertEquals(namn, dto.getArtikelNamn());
    }
}


