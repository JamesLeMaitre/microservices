package com.esmc.gestionContrat.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;


/**
 * @author katoh
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Contrat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "contact_fournisseur", length = 8,nullable = false)
    private  int contactFournisseur;

    @Column(name = "montant_global_bps", length = 50,nullable = false)
    private  Double montantGlobalBps;

    @Column(name = "montant_bci", length = 50,nullable = false)
    private  Double montantBci;

    @Column(name = "nom_entreprise", length = 100, nullable = false)
    private String nomEntreprise;

    @Column(name = "siege_social", length = 50)
    private String siegeSocial;
    @Column(name = "boite_postale", length = 10)
    private String boitePostale;
    @Column(name = "telephone", length = 14)
    private String telephone;
    @Column(name = "nom_representant", length = 50)
    private String nomRepresentant;
    @Column(name = "profession", length = 50)
    private String profession;
    @Column(name = "nom_personne_morale", length = 50)
    private String nomPersonneMorale;
    @Column(name = "domaine_entreprise", length = 50)
    private String domaineEntreprise;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date dateEntreVigueur;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_fin")
    private Date dateFin;

    @Column(name = "etat_contrat",nullable = false)
    private  boolean etatContrat;

    @CreationTimestamp
    @Column(nullable = false)
    private Date dateCreate;

    @UpdateTimestamp
    @Column(nullable = false)
    private Date dateUpdate;


    @Column(name = "id_detail_agr",nullable = false)
    private Long detailAgr;

    @ManyToOne
    @JoinColumn(name = "id_type_contrat")
    private TypeContrat typeContrat;

    @PrePersist
    private void setDateTime() {
        dateCreate = dateUpdate = new Date();
    }

    @PreUpdate
    private void updateDateTime() {
        dateUpdate = new Date();
    }




}
