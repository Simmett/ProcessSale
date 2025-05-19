package test.integration;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import integration.ArtikelFinnsInteException;

public class ArtikelFinnsInteExceptionTest {

    @Test
    public void testExceptionMessage() {
        String artikelID = "ABC123";
        ArtikelFinnsInteException exception = new ArtikelFinnsInteException(artikelID);
        String expectedMessage = "Artikel med ID \"ABC123\" finns inte.";
        assertEquals(expectedMessage, exception.getMessage(), "Felmeddelandet ska matcha förväntad text.");
    }

    @Test
    public void testGetArtikelID() {
        String artikelID = "XYZ789";
        ArtikelFinnsInteException exception = new ArtikelFinnsInteException(artikelID);
        assertEquals("XYZ789", exception.getArtikelID(), "ArtikelID ska kunna hämtas från undantaget.");
    }

    @Test
    public void testInstanceOfException() {
        ArtikelFinnsInteException exception = new ArtikelFinnsInteException("testID");
        assertTrue(exception instanceof Exception, "Undantaget ska ärva från java.lang.Exception");
    }
}
