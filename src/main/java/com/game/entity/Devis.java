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

    @Enumerated(EnumType.STRING)
    private DevisStatus status;

    @Enumerated(EnumType.STRING) // Store as String in the database
    private TypeAssurance typeAssurance;

    @Column(name = "created_at", updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @OneToOne
    @JoinColumn(name = "sante_id")
    private Sante sante;

    @OneToOne(mappedBy = "devis")
    private Contrat contrat; // Devis peut être associé à un contrat

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Sante getSante() {
        return sante;
    }

    public void setSante(Sante sante) {
        this.sante = sante;
    }

    public DevisStatus getStatus() {
        return status;
    }

    public void setStatus(DevisStatus status) {
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



    public Contrat getContrat() {
        return contrat;
    }

    public void setContrat(Contrat contrat) {
        this.contrat = contrat;
    }
}


