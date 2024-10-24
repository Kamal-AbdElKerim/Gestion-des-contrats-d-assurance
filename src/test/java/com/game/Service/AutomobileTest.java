package com.game.Service;

import com.game.entity.Automobile;
import com.game.entity.*;
import com.game.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AutomobileTest {

    private Automobile automobile;

    @BeforeEach
    public void setUp() {
        User user = new User();

        automobile = new Automobile(TypeAssurance.AUTOMOBILE, user, 30);
        automobile.setTypeVehicule(TypeVehicule.utilitaire);
        automobile.setUtilisationVehicule(UtilisationVehicule.PRIVEE);
        automobile.setHistoriqueConduite(false);
    }

    @Test
    public void testCalculerMontant_DefaultValues() {
        // Base price for a 30-year-old driver with standard vehicle and personal use, no driving history issues
        double expected = 550.0;  // Base price, no adjustments
        assertEquals(expected, automobile.calculerMontant());  // Allow small delta for floating point
    }

    @Test
    public void testCalculerMontant_YoungDriver() {
        automobile.setConducteurAge(20);  // Age under 25
        double expected = 605.0;  // +10% for young drivers
        assertEquals(expected, automobile.calculerMontant(), 0.01);
    }

    @Test
    public void testCalculerMontant_LuxuryVehicle() {
        automobile.setTypeVehicule(TypeVehicule.luxe);  // Luxury vehicle
        double expected = 632.5 ;  // +15% for luxury vehicles
        assertEquals(expected, automobile.calculerMontant(), 0.01);
    }

    @Test
    public void testCalculerMontant_ProfessionalUse() {
        automobile.setUtilisationVehicule(UtilisationVehicule.PROFESSIONNELLE);  // Professional use
        double expected = 605.0 ;
        assertEquals(expected, automobile.calculerMontant());
    }

    @Test
    public void testCalculerMontant_WithAccidentHistory() {
        automobile.setHistoriqueConduite(true);  // Accident history
        double expected = 500.0 - (500.0 * 0.20);
        assertEquals(expected, automobile.calculerMontant(), 0.01);
    }

    @Test
    public void testCalculerMontant_NoAccidentHistory() {
        automobile.setHistoriqueConduite(false);  // No accident history
        double expected = 500.0 + (500.0 * 0.10);  // +10% for no accident history
        assertEquals(expected, automobile.calculerMontant(), 0.01);
    }

    @Test
    public void testCalculerMontant_ComplexScenario() {
        // Under 25 years, luxury vehicle, professional use, no accident history
        automobile.setConducteurAge(22);
        automobile.setTypeVehicule(TypeVehicule.luxe);
        automobile.setUtilisationVehicule(UtilisationVehicule.PROFESSIONNELLE);
        automobile.setHistoriqueConduite(false);

        // Base price adjustments
        double expected = 500.0;
        expected += expected * 0.10;  // +10% for young driver
        expected += expected * 0.15;  // +15% for luxury vehicle
        expected += expected * 0.10;  // +10% for professional use
        expected += expected * 0.10;  // +10% for no accident history

        assertEquals(expected, automobile.calculerMontant(), 0.01);
    }
}

