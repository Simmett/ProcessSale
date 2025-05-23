package test.controller;

import controller.Kontroller;
import model.DTO.SkanningsDTO;
import model.DTO.Kvitto;
import integration.ArtikelFinnsInteException;
import model.DTO.ArtikelDTO;
import integration.ArtikelRegister;
import integration.RabattSystem;
import integration.Printer;
import model.Försäljning;
import model.KassaRegister;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class KontrollerTest {
    private ArtikelRegister artikelRegister;

    private Kontroller kontroller;

    @BeforeEach
    public void setUp() {
        
        ArtikelRegister artikelRegister = new ArtikelRegister(); // Ska innehålla artikel ID 1
        RabattSystem rabattSystem = new RabattSystem(); // Ska kunna hantera kund ID 42
        Printer printer = new Printer(); // Får ej krascha under test

        kontroller = new Kontroller(artikelRegister, rabattSystem, printer);
        kontroller.startaFörsäljning();
    }

    @Test
    public void testSkannaArtikel() throws Exception {
        SkanningsDTO dto = kontroller.skannaArtikel(1); // Artikel ID 1 måste finnas
        assertFalse(dto.getArtiklar().isEmpty());
    }

    @Test
    public void testAngeMängd() throws Exception {
        kontroller.skannaArtikel(1);
        SkanningsDTO dto = kontroller.angeMängd(3);
        assertEquals(3, dto.getArtiklar().get(0).getMängd());
    }

    @Test
    public void testAvslutaFörsäljning() throws Exception {
        kontroller.skannaArtikel(1);
        SkanningsDTO dto = kontroller.avslutaFörsäljning();
        assertNotNull(dto);
    }

    @Test
    public void testBegärRabatt() throws Exception {
        kontroller.skannaArtikel(1);
        kontroller.angeMängd(2);
        SkanningsDTO dto = kontroller.begärRabatt(42); // Kunden måste ha rabatt
        float pris = dto.getTotalPris();
        assertTrue(pris < 20); // Baserat på artikelpris 10 * 2
    }

    @Test
    public void testBetala() throws Exception {
        kontroller.skannaArtikel(1);
        kontroller.angeMängd(1);
        Kvitto kvitto = kontroller.betala(50f);
        assertNotNull(kvitto);
    }

    @Test
    public void testStartaFörsäljning() {
        assertDoesNotThrow(() -> kontroller.startaFörsäljning());
    }
}
