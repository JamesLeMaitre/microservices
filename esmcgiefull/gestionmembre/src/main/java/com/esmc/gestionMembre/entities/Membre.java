package com.esmc.gestionMembre.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

@Table(name = "membre")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Membre implements Serializable {

    @Id
    @Column(name = "code_membre")
    private String codeMembre;

    @Column(name = "date_identification")
    private Date dateIdentification;

    @Column(name = "date_naisMembre")
    private Date dateNaisMembre;

    @Column(name = "email_membre")
    private String emailMembre;

    @Column(name = "formation")
    private String formation;

    @Column(name = "heure_identification")
    private Time heureIdentification;

    @Column(name = "lieu_nais_membre")
    private String lieuNaisMembre;

    @Column(name = "mifarecard")
    private String mifarecard;

    @Column(name = "nbr_enf_mmbre")
    private Integer nbrEnfMmbre;

    @Column(name = "photompp")
    private String photompp;

    @Column(name = "portable_membre")
    private String portableMembre;

    @Column(name = "nom_membre")
    private String  nomMembre;

    @Column(name = "prenom_membre")
    private String prenomMembre;

    @Column(name = "profession_membre")
    private String professionMembre;

    @Column(name = "quartier_membre")
    private String quartierMembre;

    @Column(name = "sexe_membre")
    private String sexeMembre;

    @Column(name = "sitfam_membre")
    private String sitfamMembre;

    @Column(name = "tel_membre")
    private String telMembre;

    @Column(name = "ville_membre")
    private String villeMembre;

    @Column(name = "code_agence")
    private String codeAgence;

    @Column(name = "id_pays")
    private Long idPays;

    @Column(name = "id_religion_membre")
    private Long idReligionMembre;

    @Column(name = "id_maison")
    private Long idMaison;

    @Column(name = "etat_membre")
    private String etatMembre;

    @Column(name = "id_utilisateur")
    private Long idUtilisateur;

    @Column(name = "auto_enroler")
    private String autoEnroler;

    @Column(name = "photo_mpp")
    private String photoMpp;

    @Column(name = "code_gac")
    private String codeGac;

    @Column(name = "id_canton")
    private Long idCanton;

    @Column(name = "id_centres")
    private Long idCentres;

    @Column(name = "id_agences_odd")
    private Long idAgencesOdd;

    @Column(name = "desactiver")
    private Integer desactiver;

    @Column(name = "valider")
    private Integer valider;



}
