package com.esmc.gestionAgr.fifo.entities;


import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Avr implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String libelle;

    private int quantite;

    //Changement du nullable false en true pour les test

    private Double prixUnitaire;

    //Changement du nullable false en true pour les test
    @Column(nullable = true)
    private String description;

    //Changement du nullable false en true pour les test

    private Boolean etat = true;

    //Changement du nullable false en true pour les test et du updatable en false car la date
    //création se supprimais après modif
    @CreationTimestamp
    @Column(nullable = false)
    private Date dateCreate;

    @UpdateTimestamp
    @Column(nullable = false)
    private Date dateUpdate;


    @ManyToOne
    @JoinColumn(name = "id_categorie_avr", nullable = true)
    private CategorieAvr categorieAvr;

    @ManyToOne
    @JoinColumn(name = "id_type_avr")
    private TypeAvr typeAvr;

    @Column(name = "id_detail_agr")
    private Long detailAgr;


    @ManyToOne
    @JoinColumn(name = "product_registry_value_id")
    private ProductRegistryValue productRegistryValue = null;
}
