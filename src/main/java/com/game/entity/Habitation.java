package com.game.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Habitation extends Assurance {
    private double valeurBien;
    @Enumerated(EnumType.STRING)
    private TypeLogement typeLogement; // Apartment, house, etc.
    private boolean systemeSecurite; // Appartement, maison, etc.

    @Column(name = "created_at", updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    private boolean zoneRisque;

    @OneToOne(mappedBy = "habitation")
    protected Devis devis;

    public Habitation() {

    }

    @Override
    public double calculerMontant() {
        double base = 300.0;

        if (typeLogement == typeLogement.HOUSE) {
            base += base * 0.02;
        }

        if (zoneRisque) {
            base += base * 0.05;
        }

        if (valeurBien > 200_000) {
            base += base * 0.10;
        }

        if (systemeSecurite) {
            base -= base * 0.15;
        } else {
            base += base * 0.15;
        }

        return base;
    }

    public boolean isSystemeSecurite() {
        return systemeSecurite;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isZoneRisque() {
        return zoneRisque;
    }

    public void setZoneRisque(boolean zoneRisque) {
        this.zoneRisque = zoneRisque;
    }

    public Devis getDevis() {
        return devis;
    }

    public void setDevis(Devis devis) {
        this.devis = devis;
    }

    public Habitation(TypeAssurance typeAssurance, User user, double valeurBien, TypeLogement typeLogement) {
        super(typeAssurance, user); // Call the constructor of Assurance
        this.valeurBien = valeurBien;
        this.typeLogement = typeLogement;
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

    public boolean getSystemeSecurite() {
        return systemeSecurite;
    }

    public void setSystemeSecurite(boolean systemeSecurite) {
        this.systemeSecurite = systemeSecurite;
    }




}

