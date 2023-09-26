package com.esmc.gestionte.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Promotion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String libelle;

    @Column(name = "montant_promo",nullable = false)
    private double montantPromo;

    private boolean etat = false;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date dateDebut;



    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date dateFin;

    @PrePersist
    private void setDateTime() {
        dateDebut = dateDebut = new Date();
    }

    @PreUpdate
    private void updateDateTime() {
        dateFin = new Date();
    }

}
