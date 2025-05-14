package integration;

public class RabattSystem {

    /**
     * Kontrollerar om en rabatt ska tillämpas baserat på kundens ID.
     * Om kundens ID är 123 eller 456, tillämpas en rabatt på 10% på totalpriset.
     * Om kundens ID inte matchar, tillämpas ingen rabatt.
     * 
     * @param totalPris Det ursprungliga totalpriset före rabatt.
     * @param kundID Kundens ID som används för att kontrollera rabatten.
     * @return Det nya priset efter eventuell rabatt.
     */
    public float rabattKontroll(float totalPris, int kundID) {
        float nyttPris = 0;
        
       
        if (kundID == 123 || kundID == 456) {
            nyttPris = totalPris * 0.9f; 
            return nyttPris;
        }
        
        
        nyttPris = totalPris;
        return nyttPris;
    }
}
