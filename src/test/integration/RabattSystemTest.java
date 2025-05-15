package test.integration;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import integration.RabattSystem;

public class RabattSystemTest {

    @Test
    public void testRabattMedKund123() {
        RabattSystem rs = new RabattSystem();
        
        float resultat = rs.rabattKontroll(100.0f, 123);
  
        assertEquals(90.0f, resultat, 0.001f);
    }

    @Test
    public void testRabattMedKund456() {
        RabattSystem rs = new RabattSystem();
        
        float resultat = rs.rabattKontroll(200.0f, 456);
        
        assertEquals(180.0f, resultat, 0.001f);
    }

    @Test
    public void testRabattIngenRabatt() {
        RabattSystem rs = new RabattSystem();
        
        float resultat = rs.rabattKontroll(150.0f, 789);
        
        assertEquals(150.0f, resultat, 0.001f);
    }

    @Test
    public void testRabattVidNollPris() {
        RabattSystem rs = new RabattSystem();
        
        float resultat = rs.rabattKontroll(0.0f, 123);
        
        assertEquals(0.0f, resultat, 0.001f);
    }
}
