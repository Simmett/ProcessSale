package model;

/**
 * Klassen Betalning representerar en betalning i kassasystemet.
 * Den håller reda på det belopp som betalats.
 */
public class Betalning {
    private float belopp;

    /**
     * Skapar en ny betalning med angivet belopp.
     * 
     * @param belopp Det belopp som betalats.
     */
    public Betalning(float belopp) {
        this.belopp = belopp;
    }
    
    /**
     * Hämtar det betalade beloppet.
     * 
     * @return Det betalade beloppet.
     */
    public float getBelopp(){
        return belopp;
    }
}
