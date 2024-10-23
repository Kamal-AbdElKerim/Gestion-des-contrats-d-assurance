package com.game.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Contrat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "datedebut", updatable = false)
    private LocalDateTime datedebut;

    @Column(name = "datefin", updatable = false)
    private LocalDateTime datefin;

    // Store file path instead of the file itself
    @Column(name = "file_path")
    private String filePath;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "file_type")
    private String fileType;

    @OneToOne
    @JoinColumn(name = "devis_id")
    private Devis devis; // Contrat est lié à un devis

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public Devis getDevis() {
        return devis;
    }

    public void setDevis(Devis devis) {
        this.devis = devis;
    }
}
