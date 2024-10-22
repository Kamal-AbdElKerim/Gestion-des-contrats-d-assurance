package com.game.entity;

import javax.persistence.Entity;

@Entity
public class Sante extends Assurance {
    private int age;
    private String etatSante; // Medical history
    private TypeCouverture typeCouverture; // Basic, premium, etc.

    public Sante(TypeAssurance typeAssurance, User user, int age, String etatSante, TypeCouverture typeCouverture) {
        super(typeAssurance, user); // Call the constructor of Assurance
        this.age = age;
        this.etatSante = etatSante;
        this.typeCouverture = typeCouverture;
    }

    public Sante() {

    }

    @Override
    public double calculerMontant() {
        return 0;
    }


    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEtatSante() {
        return etatSante;
    }

    public void setEtatSante(String etatSante) {
        this.etatSante = etatSante;
    }

    public TypeCouverture getTypeCouverture() {
        return typeCouverture;
    }

    public void setTypeCouverture(TypeCouverture typeCouverture) {
        this.typeCouverture = typeCouverture;
    }
}

