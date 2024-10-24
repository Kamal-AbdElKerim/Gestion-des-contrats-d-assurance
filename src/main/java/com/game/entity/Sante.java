package com.game.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Sante extends Assurance {
    private int age;
    private boolean etatSante; // Medical history
    @Enumerated(EnumType.STRING)
    private TypeCouverture typeCouverture; // Basic, premium, etc.

    @Column(name = "created_at", updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @OneToOne(mappedBy = "sante")
    protected Devis devis;

    public Devis getDevis() {
        return devis;
    }

    public void setDevis(Devis devis) {
        this.devis = devis;
    }

    public Sante(TypeAssurance typeAssurance, User user, int age, boolean etatSante, TypeCouverture typeCouverture) {
        super(typeAssurance, user); // Call the constructor of Assurance
        this.age = age;
        this.etatSante = etatSante;
        this.typeCouverture = typeCouverture;
    }

    public Sante() {

    }



    @Override
    public double calculerMontant() {
        double base = 150.0;

        if (age > 60) {
            base += base * 0.20;
        }

        if (etatSante) {
            base += base * 0.30;
        }

        if (typeCouverture == typeCouverture.BASE) {
            base -= base * 0.10;
        } else if (typeCouverture == typeCouverture.PREMIUM) {
            base += base * 0.05;
        }

        return base;
    }


    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean getEtatSante() {
        return etatSante;
    }

    public void setEtatSante(boolean etatSante) {
        this.etatSante = etatSante;

    }

    public TypeCouverture getTypeCouverture() {
        return typeCouverture;
    }

    public void setTypeCouverture(TypeCouverture typeCouverture) {
        this.typeCouverture = typeCouverture;
    }

    public boolean isEtatSante() {
        return etatSante;
    }




}

