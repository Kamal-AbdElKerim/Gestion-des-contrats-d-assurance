package com.game.entity;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Assurance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    protected TypeAssurance typeAssurance ;

    @OneToOne(mappedBy = "assurance")
    protected Devis devis;

    @ManyToOne
    @JoinColumn(name = "user_id")
    protected User user;

    public Assurance(TypeAssurance typeAssurance, User user) {
        this.typeAssurance = typeAssurance;
        this.user = user;
    }
    protected Assurance() {
        // Required by JPA
    }

    public abstract double calculerMontant();



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TypeAssurance getTypeAssurance() {
        return typeAssurance;
    }

    public void setTypeAssurance(TypeAssurance typeAssurance) {
        this.typeAssurance = typeAssurance;
    }

    public Devis getDevis() {
        return devis;
    }

    public void setDevis(Devis devis) {
        this.devis = devis;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
