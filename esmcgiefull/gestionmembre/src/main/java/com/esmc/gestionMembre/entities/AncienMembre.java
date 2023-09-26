package com.esmc.gestionMembre.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "ancien_membre")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AncienMembre implements Serializable {

    @Id
    @Column(name = "ancien_code_membre")
    private String ancienCodeMembre;

    @Column(name = "code_gac_filiere")
    private String codeGacFiliere;

    @Column(name = "code_type_acteur")
    private String codeTypeActeur;

    @Column(name = "type_membre")
    private String typeMembre;

    @Column(name = "code_statut")
    private String codeStatut;

    @Column(name = "raison_social")
    private String raisonSocial;

    @Column(name = "nom_membre")
    private String nomMembre;

    @Column(name = "prenom_membre")
    private String prenomMembre;

    @Column(name = "sexe")
    private String sexe;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_nais_membre")
    private Date dateNaisMembre;

    @Column(name = "id_pays")
    private String idPays;

    @Column(name = "pere_membre")
    private String pereMembre;

    @Column(name = "mere_membre")
    private String mereMembre;

    @Column(name = "sit_fam_membre")
    private String sitFamMembre;

    @Column(name = "nbr_enf_membre")
    private String nbrEnfMembre;

    @Column(name = "id_religion_membre")
    private String idReligionMembre;

    @Column(name = "profession_membre")
    private String professionMembre;

    @Column(name = "formation")
    private String formation;

    @Column(name = "quartier_membre")
    private String quartierMembre;

    @Column(name = "ville_membre")
    private String villeMembre;

    @Column(name = "tel_membre")
    private String telMembre;

    @Column(name = "portable_membre")
    private String portableMembre;

    @Column(name = "email_membre")
    private String emailMembre;

    @Column(name = "site_web")
    private String siteWeb;

    @Column(name = "emprunt_membre")
    private String empruntMembre;

    @Column(name = "photo_membre")
    private String photo_membre;

    @Column(name = "domaine_activite")
    private String domaineActivite;

    @Column(name = "num_registre_membre")
    private String numRegistreMembre;

    @Column(name = "code_agence")
    private String codeAgence;

    @Column(name = "date_identification")
    private Date dateIdentification;

    @Column(name = "heure_identification")
    private String heureIdentifiaction;

    @Column(name = "id_utilisateur")
    private String idUtilisateur;

    @Column(name = "auto_enroler")
    private String autoEnroler;

    @Column(name = "FingerPrint1")
    private String fingerPrint1;

    @Column(name = "FingerPrint2")
    private String fingerPrint2;

    @Column(name = "FingerPrint3")
    private String fingerPrint3;

    @Column(name = "FingerPrint4")
    private String fingerPrint4;

    @Column(name = "FingerPrint5")
    private String fingerPrint5;

    @Column(name = "MifareCard")
    private String MifareCard;

    @Column(name = "code_membre")
    private String codeMembre;

}
