package test.model.DTO;
import model.DTO.SkanningsDTO;
import model.DTO.SåldArtikelDTO;
import model.SåldArtikel;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SkanningsDTOTest {

    @Test
    void testSkanningsDTOMedTomLista() {
        List<SåldArtikel> tomLista = new ArrayList<>();
        LocalDateTime tid = LocalDateTime.now();
        float vat = 5.0f;
        float totalPris = 100.0f;

        SkanningsDTO dto = new SkanningsDTO(tomLista, tid, vat, totalPris);

        assertEquals(totalPris, dto.getTotalPris());
        assertEquals(vat, dto.getVAT());
        assertEquals(tid, dto.getTid());

        // Listan med SåldArtikelDTO ska vara tom
        List<SåldArtikelDTO> artiklar = dto.getSåldaArtikelDTOs();
        assertTrue(artiklar.isEmpty());

        // Senaste sålda artikel ska vara null när listan är tom
        assertNull(dto.getSenasteSåldaArtikelDTO());
    }
}
