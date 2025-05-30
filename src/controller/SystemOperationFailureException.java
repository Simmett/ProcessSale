package controller;

/**
 * Kastas när en systemoperation inte kan utföras av kontrollern.
 */
public class SystemOperationFailureException extends Exception {
    
    /**
     * Skapar en ny instans som representerar felet beskrivet i det angivna meddelandet.
     * 
     * @param msg   Ett meddelande som beskriver felet.
     * @param cause Den exception som orsakade att systemoperationen misslyckades.
     */
    public SystemOperationFailureException(String msg, Exception cause){
        super(msg, cause);
    }
}
