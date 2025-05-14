package integration;

import model.DTO.Kvitto;

/* 
Hanterar uppdatering av lagret baserat på en försäljning.
*/

public class LagerData {

    /**
     * Uppdaterar lagret baserat på den information som finns i kvittot.
     * Denna metod tar emot ett kvitto och uppdaterar lagret med artiklarna från kvittot.
     * Om kvittot är null kastas ett IllegalArgumentException.
     * 
     * @param kvitto Kvitto som innehåller artiklar som ska uppdatera lagret
     * @throws IllegalArgumentException Om kvitto är null
     */
    public void uppdateraLager(Kvitto kvitto) {
     
        if (kvitto == null) {
            throw new IllegalArgumentException("Kvitto kan inte vara null.");
        }

        
        System.out.println("Lager uppdaterat baserat på: " + kvitto.getlistaAvArtiklar());
    }
}
