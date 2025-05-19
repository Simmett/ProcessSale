package test.model.DTO;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import model.DTO.Kvitto;

import static org.junit.jupiter.api.Assertions.*;

class KvittoTest {

    @Test
    void testKvittoKonstruktorOchGetters() {
        // Arrange
        LocalTime tid = LocalTime.of(14, 30);
        float totalpris = 100.0f;
        float totalVat = 12.0f;
        float betalat = 150.0f;
        String artiklar = "1 x BigWheel Oatmeal á 29.90 kr\n2 x YouGoGo Blueberry á 14.90 kr";
        float växel = 50.0f;
        LocalDate datum = LocalDate.of(2025, 5, 19);
        float nyttPris = 90.0f;
        float rabatt = 10.0f;

       
        Kvitto kvitto = new Kvitto(tid, totalpris, totalVat, betalat, artiklar, växel, datum, nyttPris, rabatt);

        // Assert
        assertEquals(tid, kvitto.gettidFörsäljning());
        assertEquals(totalpris, kvitto.gettotalPris());
        assertEquals(totalVat, kvitto.gettotalVat());
        assertEquals(betalat, kvitto.getbetalatBelopp());
        assertEquals(artiklar, kvitto.getlistaAvArtiklar());
        assertEquals(växel, kvitto.getväxel());
        assertEquals(datum, kvitto.getdatum());
        assertEquals(nyttPris, kvitto.getnyttPris());
        assertEquals(rabatt, kvitto.getrabatt());
    }
}
