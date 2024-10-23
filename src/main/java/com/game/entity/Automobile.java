package com.game.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Automobile extends Assurance {
    private int conducteurAge;
    @Enumerated(EnumType.STRING)
    private UtilisationVehicule utilisationVehicule;
    @Enumerated(EnumType.STRING)
    private TypeVehicule typeVehicule;
    private boolean historiqueConduite;// true si des sinistres dans les 3 dernières années
    @Column(name = "created_at", updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @OneToOne(mappedBy = "automobile")
    protected Devis devis;



    public Automobile(TypeAssurance typeAssurance, User user, int conducteurAge) {
        super(typeAssurance, user); // Call the constructor of Assurance
        this.conducteurAge = conducteurAge;
    }

    public Automobile() {

    }

    public UtilisationVehicule getUtilisationVehicule() {
        return utilisationVehicule;
    }

    public void setUtilisationVehicule(UtilisationVehicule utilisationVehicule) {
        this.utilisationVehicule = utilisationVehicule;
    }

    public TypeVehicule getTypeVehicule() {
        return typeVehicule;
    }

    public void setTypeVehicule(TypeVehicule typeVehicule) {
        this.typeVehicule = typeVehicule;
    }

    public boolean isHistoriqueConduite() {
        return historiqueConduite;
    }

    public void setHistoriqueConduite(boolean historiqueConduite) {
        this.historiqueConduite = historiqueConduite;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Devis getDevis() {
        return devis;
    }

    public void setDevis(Devis devis) {
        this.devis = devis;
    }

    @Override
    public double calculerMontant() {
        double base = 500; // Base for automobile insurance
        if (conducteurAge < 25) {
            base += base * 0.10; // +10%
        }
        if (typeVehicule == typeVehicule.luxe) {
            base += base * 0.15; // +15%
        }
        if (utilisationVehicule == UtilisationVehicule.PROFESSIONNELLE) {
            base += base * 0.10; // +10%
        }

        if (historiqueConduite) {
            base -= base * 0.20; // -20%
        } else {
            base += base * 0.10; // +10% sinon
        }
        return base;
    }

    public int getConducteurAge() {
        return conducteurAge;
    }

    public void setConducteurAge(int conducteurAge) {
        this.conducteurAge = conducteurAge;
    }






}

