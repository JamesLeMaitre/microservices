package com.esmc.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

/**
 * @author katoh
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ksu implements Serializable {
    private Long id;

    private String codeKsu;

    private String codeBanKsu;

    private String nom;

    private String prenom;
    private String raisonSocial;
    private String codeKsuRepresentant;
    private String statusJuridique;
    private String denomination;
    private String telephone;
    private String adresse;
    private String boitePostale;
    private String email;

    private String professionActuelle;

    private String formation;

    private String lieuNaissance;
    private Date dateNaissance;
    private String sexe;
    private String nomPere;
    private String nomMere;
    private String situationMatrimoniale;
    private String nombreEnfant;
    private String domicile;
    private String numeroCompteBanque;
    private String signature;
    private Date dateAchat;
    private String numeroPieceId;
    private  String photoPieceId;
    private boolean valide = false;
    private String nif;
    private String numeroRCCM;
    private String agregation;
    private String autorisation;
    private String recepice;
    private Date dateCreate;
    private Date dateUpdate;
    private PieceIdentite pieceIdentite;
    private Abonnement abonnement;
    private Secteur secteur;
    private TypePM typePM;
    private TypeOperateur typeOperateur;
    private Banque banque;
    private Long IdmaBanKsu;
    private Long idCanton;
    private String reference;
    private Boolean EnableConnexionStatus=false;
    private String codeConnexion=null;
    private String tempFingerPrintKey=null;
    private Long DateAccessConexionTimeStamp=null;
    private boolean etat = true;

}
