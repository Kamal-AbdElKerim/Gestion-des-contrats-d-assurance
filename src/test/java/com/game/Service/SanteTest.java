package com.game.Service;

import com.game.entity.Sante;
import com.game.entity.TypeAssurance;
import com.game.entity.TypeCouverture;
import com.game.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SanteTest {

    private Sante sante;

    @BeforeEach
    public void setUp() {
        User user = new User();

        sante = new Sante(TypeAssurance.SANTE, user, 30, false, TypeCouverture.BASE);
    }

    @Test
    public void testCalculerMontant_DefaultValues() {
        double expected = 135.0;
        assertEquals(expected, sante.calculerMontant(), 0.01);
    }

    @Test
    public void testCalculerMontant_OlderThan60() {
        sante.setAge(65);  // Age over 60
        double expected = 162.0;  // +20% for age
        assertEquals(expected, sante.calculerMontant(), 0.01);
    }

    @Test
    public void testCalculerMontant_PoorHealth() {
        sante.setEtatSante(true);
        double expected = 175.5;  // +30% for poor health
        assertEquals(expected, sante.calculerMontant(), 0.01);
    }

    @Test
    public void testCalculerMontant_BaseCoverage() {
        sante.setTypeCouverture(TypeCouverture.BASE);
        double expected = 150.0 - (150.0 * 0.10);  // -10% for base coverage
        assertEquals(expected, sante.calculerMontant(), 0.01);
    }

    @Test
    public void testCalculerMontant_PremiumCoverage() {
        sante.setTypeCouverture(TypeCouverture.PREMIUM);
        double expected = 150.0 + (150.0 * 0.05);  // +5% for premium coverage
        assertEquals(expected, sante.calculerMontant(), 0.01);
    }

    @Test
    public void testCalculerMontant_ComplexScenario() {
        sante.setAge(65);
        sante.setEtatSante(true);
        sante.setTypeCouverture(TypeCouverture.PREMIUM);

        double expected = 150.0;
        expected += expected * 0.20;  // +20% for age
        expected += expected * 0.30;  // +30% for poor health
        expected += expected * 0.05;   // +5% for premium coverage

        assertEquals(expected, sante.calculerMontant(), 0.01);
    }
}

