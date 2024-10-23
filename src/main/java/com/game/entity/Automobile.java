package com.game.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Automobile extends Assurance {
    private int conducteurAge;
    private String utilisation; // Private or professional use
    private String historiqueConduite;// true si des sinistres dans les 3 dernières années
    @Column(name = "created_at", updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;
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
        double base = 500; // Base for automobile insurance
        if (conducteurAge < 25) {
            base *= 1.10; // +10% for drivers under 25
        }
        if ("luxe".equalsIgnoreCase(utilisation)) {
            base *= 1.15; // +15% for luxury vehicles
        }
        if ("professionnel".equalsIgnoreCase(utilisation)) {
            base *= 1.10; // +10% for professional use
        }
        if ("false".equals(historiqueConduite)) {
            base *= 0.80; // -20% for no accidents in the last 3 years
        }
        return base;
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

