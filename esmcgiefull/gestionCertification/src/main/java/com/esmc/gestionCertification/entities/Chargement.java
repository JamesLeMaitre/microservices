package com.esmc.gestionCertification.entities;


import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Chargement implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_detailAgr",nullable = false)
    private Long detailAgr;

    @Column(name = "id_detail_agr_franchiser")
    private Long idDetailAgrFranchiser;

    @ManyToOne
    @JoinColumn(name = "id_type_chargement")
    private TypeChargement typeChargement;

    @Column(name = "id_poste",nullable = true)
    private Long idPoste;

    @Column(name = "libelle_poste")
    private String libellePoste;

    @ManyToOne
    @JoinColumn(name = "id_candidature")
    private AppelCandidature candidature;

    @Column(nullable = false)
    private boolean etat;

   @CreationTimestamp
    @Column(nullable = false)
    private Date dateCreation;

    @UpdateTimestamp
    @Column(nullable = false)
    private Date dateUpdate;

}
