package test.integration;

import integration.LagerData;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LagerDataTest {


    private LagerData lagerData;

    @BeforeEach
    void setUp() {
        lagerData = new LagerData();
    }

    @Test
    void testUppdateraLager_medNullKvitto_skaKastaIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> {
            lagerData.uppdateraLager(null);
        });
    }

}
