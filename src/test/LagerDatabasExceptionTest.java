package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import integration.LagerDatabasException;

public class LagerDatabasExceptionTest {

    @Test
    public void testExceptionMessage() {
        String artikelID = "ABC123";
        LagerDatabasException exception = new LagerDatabasException(artikelID);

        String expectedMessage = "Databasfel vid åtkomst till artikel med ID: ABC123";
        assertEquals(expectedMessage, exception.getMessage(), "Felmeddelandet är inte korrekt.");
    }

    @Test
    public void testExceptionIsInstanceOfException() {
        LagerDatabasException exception = new LagerDatabasException("XYZ999");
        assertTrue(exception instanceof Exception, "LagerDatabasException borde vara en subklass till Exception.");
    }
}
