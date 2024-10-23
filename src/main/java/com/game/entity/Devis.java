package com.game.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Devis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double montant;

    private String status;

    @Enumerated(EnumType.STRING) // Store as String in the database
    private TypeAssurance typeAssurance;

    @Column(name = "created_at", updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @OneToOne
    @JoinColumn(name = "assurance_id")
    private Assurance assurance; // Devis est lié à une assurance

    @OneToOne(mappedBy = "devis")
    private Contrat contrat; // Devis peut être associé à un contrat

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public TypeAssurance getTypeAssurance() {
        return typeAssurance;
    }

    public void setTypeAssurance(TypeAssurance typeAssurance) {
        this.typeAssurance = typeAssurance;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public Assurance getAssurance() {
        return assurance;
    }

    public void setAssurance(Assurance assurance) {
        this.assurance = assurance;
    }

    public Contrat getContrat() {
        return contrat;
    }

    public void setContrat(Contrat contrat) {
        this.contrat = contrat;
    }
}


