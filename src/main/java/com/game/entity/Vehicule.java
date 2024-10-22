package com.game.entity;

import javax.persistence.*;

@Entity
public class Vehicule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String marque;
    private String modele;
    private String type;

    @OneToOne
    @JoinColumn(name = "automobile_id")
    private Automobile automobile; // Vehicule lié à l'assurance automobile


    public Vehicule(Long id, String marque, String modele, String type, Automobile automobile) {
        this.id = id;
        this.marque = marque;
        this.modele = modele;
        this.type = type;
        this.automobile = automobile;
    }

    public Vehicule() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Automobile getAutomobile() {
        return automobile;
    }

    public void setAutomobile(Automobile automobile) {
        this.automobile = automobile;
    }
}

