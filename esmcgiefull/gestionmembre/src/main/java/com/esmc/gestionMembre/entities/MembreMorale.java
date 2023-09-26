package com.esmc.gestionMembre.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Blob;
import java.util.Date;
@Table(name = "membre_morale")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MembreMorale implements Serializable {

    @Id
    @Column(name = "code_membre_morale")
    private  String codeMembreMorale;

    @Column(name = "auto_enroler")
    private String autoEnroler;

    @Column(name = "bp_membre")
    private  String bpMembre;

    @Column(name = "code_gac_filiere")
    private String codeGacFiliere;

    @Column(name = "code_type_acteur")
    private String codeTypeActeur;

    @Column(name = "codesecret")
    private String codeSecret;

    @Column(name = "date_identification")
    private Date dateIdentification;

    @Column(name = "domaine_activite")
    private String domaineActivite;

    @Column(name = "email_membre")
    private String emailMembre;

    @Column(name = "etat_membre", length = 4)
    private String etatMembre;

    @Column(name = "heure_identification")
    @Temporal(TemporalType.TIME)
    private Date heureIdentification;

    @Column(name = "id_filiere")
    private Integer idFiliere;

    @Column(name = "num_registre_membre")
    private String numRegistreMembre;

    @Column(name = "portable_membre")
    private  String portableMembre;

    @Column(name = "quartier_membre")
    private String quartierMembre;

    @Column(name = "raison_sociale")
    private  String raisonSociale;

    @Column(name = "site_web")
    private  String siteWeb;

    @Column(name = "tel_membre")
    private  String telMembre;

    @Column(name = "ville_membre")
    private  String villeMembre;

    @Column(name = "code_agence")
    private  String codeAgence;

    @Column(name = "id_pays")
    private  int idPays;

    @Column(name = "code_statut")
    private  String codeStatut;

    @Column(name = "photo_mpp")
    private  String photoMpp;

    @Column(name = "id_utilisateur")
    private  Long idUtilisateur;

    @Column(name = "id_canton")
    private  String idCanton;

    @Column(name = "id_centres")
    private  String idCentres;

    @Column(name = "type_fournisseur")
    private  String typeFournisseur;

    @Column(name = "desactiver")
    private int desactiver;


}
