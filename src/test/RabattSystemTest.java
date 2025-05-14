package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import integration.RabattSystem;

public class RabattSystemTest {

    @Test
    public void testRabattMedKund123() {
        RabattSystem rs = new RabattSystem();
        
        // Testa när kundID är 123 och förväntad rabatt är 10%
        float resultat = rs.rabattKontroll(100.0f, 123);
        
        // Förväntar oss att priset blir 90 (10% rabatt på 100)
        assertEquals(90.0f, resultat, 0.001f);
    }

    @Test
    public void testRabattMedKund456() {
        RabattSystem rs = new RabattSystem();
        
        // Testa när kundID är 456 och förväntad rabatt är 10%
        float resultat = rs.rabattKontroll(200.0f, 456);
        
        // Förväntar oss att priset blir 180 (10% rabatt på 200)
        assertEquals(180.0f, resultat, 0.001f);
    }

    @Test
    public void testRabattIngenRabatt() {
        RabattSystem rs = new RabattSystem();
        
        // Testa när kundID inte är 123 eller 456, så ingen rabatt ges
        float resultat = rs.rabattKontroll(150.0f, 789);
        
        // Förväntar oss att det totala priset är 150 utan rabatt
        assertEquals(150.0f, resultat, 0.001f);
    }

    @Test
    public void testRabattVidNollPris() {
        RabattSystem rs = new RabattSystem();
        
        // Testa när priset är 0, oavsett kundID, priset ska alltid vara 0
        float resultat = rs.rabattKontroll(0.0f, 123);
        
        // Förväntar oss att priset blir 0 även om rabatten ges
        assertEquals(0.0f, resultat, 0.001f);
    }
}
