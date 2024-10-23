package com.game.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Habitation extends Assurance {
    private double valeurBien;
    @Enumerated(EnumType.STRING)
    private TypeLogement typeLogement; // Apartment, house, etc.
    private String systemeSecurite; // Appartement, maison, etc.

    @Column(name = "created_at", updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @OneToOne
    @JoinColumn(name = "localisation_id")
    private Localisation localisation;



    public Habitation() {

    }

    @Override
    public double calculerMontant() {
        double base = 300; // Base for home insurance
        if (valeurBien > 200000) {
            base *= 1.10; // +10% for properties over 200,000 MAD
        }
        if ("maison".equalsIgnoreCase(typeLogement.toString())) {
            base *= 1.02; // +2% for houses
        }
        if ("zone à risque".equalsIgnoreCase(systemeSecurite)) {
            base *= 1.05; // +5% for high-risk areas
        }
        return base;
    }


    public Habitation(TypeAssurance typeAssurance, User user, double valeurBien, TypeLogement typeLogement, String systemeSecurite, Localisation localisation) {
        super(typeAssurance, user); // Call the constructor of Assurance
        this.valeurBien = valeurBien;
        this.typeLogement = typeLogement;
        this.systemeSecurite = systemeSecurite;
        this.localisation = localisation;
    }

    public double getValeurBien() {
        return valeurBien;
    }

    public void setValeurBien(double valeurBien) {
        this.valeurBien = valeurBien;
    }

    public TypeLogement getTypeLogement() {
        return typeLogement;
    }

    public void setTypeLogement(TypeLogement typeLogement) {
        this.typeLogement = typeLogement;
    }

    public String getSystemeSecurite() {
        return systemeSecurite;
    }

    public void setSystemeSecurite(String systemeSecurite) {
        this.systemeSecurite = systemeSecurite;
    }

    public Localisation getLocalisation() {
        return localisation;
    }

    public void setLocalisation(Localisation localisation) {
        this.localisation = localisation;
    }


}

