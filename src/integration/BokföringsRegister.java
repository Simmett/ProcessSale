package integration;

import java.util.ArrayList;
import java.util.List;
import model.DTO.Kvitto;

/**
 * BokföringsRegister hanterar lagring av kvitton för bokföring.
 * 
 * Klassen är implementerad som en singleton, vilket innebär att endast
 * en instans av BokföringsRegister finns under programmets körning.
 * Den sparar alla kvitton som bokförs i en intern lista.
 */
public class BokföringsRegister {
    private List<Kvitto> kvitton;
    private static final BokföringsRegister BOKFÖRINGS_REGISTER = new BokföringsRegister();
    
    /**
     * Privat konstruktor som skapar en ny instans av BokföringsRegister.
     * Eftersom klassen är en singleton, kan denna inte anropas utanför klassen.
     */
    private BokföringsRegister(){
        this.kvitton = new ArrayList<>();
    }
    
    /**
     * Hämtar den enda instansen av BokföringsRegister (singleton).
     * 
     * @return instansen av BokföringsRegister
     */
    public static BokföringsRegister getBokföringsRegister(){
        return BOKFÖRINGS_REGISTER;
    }
    
    /**
     * Bokför ett kvitto genom att spara det i registret.
     * 
     * @param kvitto Kvitto som ska bokföras
     */
    public void bokföra(Kvitto kvitto){
        kvitton.add(kvitto);
    }
}
