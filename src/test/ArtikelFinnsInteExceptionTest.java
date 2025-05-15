package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import integration.ArtikelFinnsInteException;

public class ArtikelFinnsInteExceptionTest {

    @Test
    public void testExceptionMessage() {
        String artikelID = "123";
        ArtikelFinnsInteException exception = new ArtikelFinnsInteException(artikelID);
        String expectedMessage = "Artikel med ID \"123\" finns inte.";

        assertEquals(expectedMessage, exception.getMessage());
    }
}
