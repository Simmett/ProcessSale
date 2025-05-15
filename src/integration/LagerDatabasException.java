package integration;

/**
 * Kastas när databasanslutningen misslyckas (simulerat).
 */
public class LagerDatabasException extends RuntimeException {
    public LagerDatabasException(String artikelID) {
        super("Databasfel vid åtkomst till artikel med ID: " + artikelID);
    }
}
