package com.game.entity;

import javax.persistence.*;

@Entity
public class Automobile extends Assurance {
    private int conducteurAge;
    private String utilisation; // Private or professional use
    private String historiqueConduite;// true si des sinistres dans les 3 dernières années

    @OneToOne
    @JoinColumn(name = "vehicule_id")
    private Vehicule vehicule;

    public Automobile(TypeAssurance typeAssurance, User user, int conducteurAge, String utilisation, String historiqueConduite, Vehicule vehicule) {
        super(typeAssurance, user); // Call the constructor of Assurance
        this.conducteurAge = conducteurAge;
        this.utilisation = utilisation;
        this.historiqueConduite = historiqueConduite;
        this.vehicule = vehicule;
    }

    public Automobile() {

    }

    @Override
    public double calculerMontant() {
        return 0;
    }

    public int getConducteurAge() {
        return conducteurAge;
    }

    public void setConducteurAge(int conducteurAge) {
        this.conducteurAge = conducteurAge;
    }

    public String getUtilisation() {
        return utilisation;
    }

    public void setUtilisation(String utilisation) {
        this.utilisation = utilisation;
    }

    public String getHistoriqueConduite() {
        return historiqueConduite;
    }

    public void setHistoriqueConduite(String historiqueConduite) {
        this.historiqueConduite = historiqueConduite;
    }

    public Vehicule getVehicule() {
        return vehicule;
    }

    public void setVehicule(Vehicule vehicule) {
        this.vehicule = vehicule;
    }
}

