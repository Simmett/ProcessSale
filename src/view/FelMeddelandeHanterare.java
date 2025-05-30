package view;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * Denna klass är ansvarig för att visa felmeddelanden till användaren.
 * Felmeddelanden visas tillsammans med en tidsstämpel för när felet inträffade.
 */
public class FelMeddelandeHanterare {
    
    /**
     * Visar ett felmeddelande tillsammans med aktuell tid.
     *
     * @param msg Felmeddelandet som ska visas för användaren.
     */
    void visaFelMeddelande(String msg) {
        StringBuilder errorMsgBuilder = new StringBuilder();
        errorMsgBuilder.append(visaTid());
        errorMsgBuilder.append(", FEL: ");
        errorMsgBuilder.append(msg);
        System.out.println(errorMsgBuilder);
    }
    
    /**
     * Returnerar den aktuella tiden formaterad enligt lokal stil.
     *
     * @return En sträng med aktuell tid och datum.
     */
    private String visaTid() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
        return now.format(formatter);
    }
}
