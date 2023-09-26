package com.esmc.gestionAvr.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author katoh
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DetailSMAvr implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = true)
    private String numOrdre;

    @Column(nullable = true)
    private String reference;

    @Column(nullable = true)
    private String article;

    @Column(nullable = true)
    private int unite;

    @Column(nullable = true)
    private int quantite;

    @Column(nullable = true)
    private Double prixUnitaire;

    @Column(nullable = true)
    private Double montantFirst; // montant en le bon depart exemple ban pour ban-bci

    @Column(nullable = true)
    private Double montantSecond; // montant en le bon arrive exemple bci pour ban-bci

    @Column(nullable = true)
    private Double valeur;

    @CreationTimestamp
    @Column(nullable = false)
    private Date dateCreate;

    @UpdateTimestamp
    @Column(nullable = false)
    private Date dateUpdate;


    @ManyToOne
    @JoinColumn(name = "id_smAvr")
    private SMAvr smAvr;

    @ManyToOne
    @JoinColumn(name = "id_avr",nullable = false)
    private Avr avr;

    @PrePersist
    private void setDateTime() {
        dateCreate = dateUpdate = new Date();
    }

    @PreUpdate
    private void updateDateTime() {
        dateUpdate = new Date();
    }

}
