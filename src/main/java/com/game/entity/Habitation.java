package com.game.entity;

import javax.persistence.*;

@Entity
public class Habitation extends Assurance {
    private double valeurBien;
    private TypeLogement typeLogement; // Apartment, house, etc.
    private String systemeSecurite; // Appartement, maison, etc.

    @OneToOne
    @JoinColumn(name = "localisation_id")
    private Localisation localisation;

    public Habitation() {

    }

    @Override
    public double calculerMontant() {
        return 0;
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

