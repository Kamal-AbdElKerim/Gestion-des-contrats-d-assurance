package com.game.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
        double base = 150; // Base for health insurance
        if (age > 60) {
            base *= 1.20; // +20% for people over 60
        }
        if (etatSante) {
            base *= 1.30; // +30% for chronic illnesses
        }
        if (typeCouverture == TypeCouverture.PREMIUM) {
            base *= 1.05; // +5% for premium coverage
        } else {
            base *= 0.90; // -10% for basic coverage
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
}

