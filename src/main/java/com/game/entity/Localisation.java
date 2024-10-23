package com.game.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Localisation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String adresse;
    private String zoneRisque; // Zone à risque ou non

    @Column(name = "created_at", updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @OneToOne
    @JoinColumn(name = "habitation_id")
    private Habitation habitation; // Localisation liée à l'assurance habitation

    public Localisation(Long id, String adresse, String zoneRisque, Habitation habitation) {
        this.id = id;
        this.adresse = adresse;
        this.zoneRisque = zoneRisque;
        this.habitation = habitation;
    }

    public Localisation() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getZoneRisque() {
        return zoneRisque;
    }

    public void setZoneRisque(String zoneRisque) {
        this.zoneRisque = zoneRisque;
    }

    public Habitation getHabitation() {
        return habitation;
    }

    public void setHabitation(Habitation habitation) {
        this.habitation = habitation;
    }
}

