package com.esmc.gestionAgr.fifo.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
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

    private int unite;

    @Column(nullable = true)
    private int quantite;

    @Column(nullable = true)
    private Double prixUnitaire;

    private Double montantFirst; // montant en le bon depart exemple ban pour ban-bci


    private Double montantSecond; // montant en le bon arrive exemple bci pour ban-bci


    private Double valeur;

    @CreationTimestamp
    private Date dateCreate;

    @UpdateTimestamp
    private Date dateUpdate;


    @ManyToOne
    @JoinColumn(name = "id_smAvr")
    private SMAvr smAvr;

    @ManyToOne
    @JoinColumn(name = "id_avr", nullable = false)
    private Avr avr;

}