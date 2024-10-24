package com.game.Service;

import com.game.entity.Habitation;
import com.game.entity.TypeAssurance;
import com.game.entity.TypeLogement;
import com.game.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HabitationTest {

    private Habitation habitation;

    @BeforeEach
    public void setUp() {
        User user = new User();

        habitation = new Habitation(TypeAssurance.HABITATION, user, 150_000, TypeLogement.APPARTEMENT);
        habitation.setZoneRisque(false);
        habitation.setSystemeSecurite(true);
    }

    @Test
    public void testCalculerMontant_DefaultValues() {
        double expected = 255.0;
        assertEquals(expected, habitation.calculerMontant());
    }

    @Test
    public void testCalculerMontant_HouseWithRiskZone() {
        habitation.setTypeLogement(TypeLogement.HOUSE);
        habitation.setZoneRisque(true);


        double expected = 273.105;
        assertEquals(expected, habitation.calculerMontant(), 0.01);
    }

    @Test
    public void testCalculerMontant_HighValueProperty() {
        habitation.setValeurBien(300_000);


        double expected = 280.5;
        assertEquals(expected, habitation.calculerMontant(), 0.01);
    }

    @Test
    public void testCalculerMontant_NoSecuritySystem() {
        habitation.setSystemeSecurite(false);


        double expected = 345.0;
        assertEquals(expected, habitation.calculerMontant(), 0.01);
    }

    @Test
    public void testCalculerMontant_ComplexScenario() {
        habitation.setTypeLogement(TypeLogement.HOUSE);
        habitation.setValeurBien(300_000);
        habitation.setSystemeSecurite(false);
        habitation.setZoneRisque(true);

        double expected = 300.0;
        expected += expected * 0.02;  // +2% for house
        expected += expected * 0.05;  // +5% for risk zone
        expected += expected * 0.10;  // +10% for high value
        expected += expected * 0.15;  // +15% for no security system

        assertEquals(expected, habitation.calculerMontant(), 0.01);
    }
}
