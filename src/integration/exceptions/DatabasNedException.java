package integration.exceptions;

/**
 * Kastas när databasanslutningen misslyckas (simulerat).
 */
public class DatabasNedException extends Exception {
    public DatabasNedException(String artikelID) {
        super("Databasfel vid åtkomst till artikel med ID: " + artikelID);
    }
}
