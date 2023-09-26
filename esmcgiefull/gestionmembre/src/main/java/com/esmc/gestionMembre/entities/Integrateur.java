package com.esmc.gestionMembre.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "integrateur")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Integrateur implements Serializable {

    @Id
    @Column(name = "integrateur_id")
    private Long integrateurId;

    @Column(name = "integrateur_souscription")
    private Long integrateurSouscription;

    @Column(name = "integrateur_critere1")
    private Integer integrateurCritere1;

    @Column(name = "integrateur_critere2")
    private Integer integrateurCritere2;

    @Column(name = "integrateur_critere3")
    private Integer integrateurCritere3;

    @Column(name = "integrateur_type")
    private Integer integrateurType;

    @Column(name = "integrateur_date")
    private Date integrateurDate;

    @Column(name = "publier")
    private Integer publier;

    @Column(name = "integrateur_poste")
    private String integrateurPoste;

    @Column(name = "integrateur_document")
    private String integrateurDocument;

    @Column(name = "integrateur_education")
    private String integrateurEducation;

    @Column(name = "integrateur_affiliation")
    private String integrateurAffiliation;

    @Column(name = "integrateur_formation")
    private String integrateurFormation;

    @Column(name = "integrateur_langue")
    private String integrateurLangue;

    @Column(name = "integrateur_experience")
    private String integrateurExperience;

    @Column(name = "integrateur_attestation")
    private Double integrateurAttestation;

    @Column(name = "integrateur_diplome")
    private String integrateurDiplome;

    @Column(name = "integrateur_membreasso")
    private Integer integrateurMembreasso;

    @Column(name = "integrateur_canton")
    private String integrateurCanton;

    @Column(name = "integrateur_ville")
    private String integrateurVille;

    @Column(name = "integrateur_adresse")
    private String integrateurAdresse;

    @Column(name = "code_membre")
    private String codeMembre;

}
