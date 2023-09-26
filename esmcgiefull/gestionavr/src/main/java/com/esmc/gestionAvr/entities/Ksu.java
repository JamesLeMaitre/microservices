package com.esmc.gestionAvr.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Ksu implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code_ksu")
    private String codeKsu;

    @Column(nullable = true)
    private String codeBanKsu;

    @Column(nullable = true)
    private String nom;

    @Column(nullable = true)
    private String prenom;

    @Column(name = "raison_social",nullable = true)
    private String raisonSocial;

    @Column(nullable = true)
    private String codeKsuRepresentant;

    @Column(nullable = true)
    private String statusJuridique;

    @Column(name = "denomination", length = 30, nullable = true)
    private String denomination;


    private String telephone;

    @Column(nullable = true)
    private String adresse;

    @Column(nullable = true)
    private String boitePostale;

    private String email;

    private String professionActuelle;

    private String formation;

    private String lieuNaissance;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateNaissance;

    private String sexe;

    private String nomPere;

    private String nomMere;

    private String situationMatrimoniale;

    private String nombreEnfant;

    private String domicile;

    private String numeroCompteBanque;

    @Column(nullable = true)
    private String signature;

    @Temporal(TemporalType.DATE)
    private Date dateAchat;

    private String numeroPieceId;

    @Column(nullable = true)
    private  String photoPieceId;

    private boolean valide = false;

    @Column(nullable = true)
    private String nif;

    @Column(nullable = true)
    private String numeroRCCM;

    @Column(nullable = true)
    private String agregation;

    @Column(nullable = true)
    private String autorisation;

    @Column(nullable = true)
    private String recepice;

    @CreationTimestamp
    @Column(nullable = false)
    private Date dateCreate;

    @UpdateTimestamp
    @Column(nullable = false)
    private Date dateUpdate;


    @Column(name = "id_ma_ban_Ksu")
    private Long IdmaBanKsu;

    @Column(name = "id_canton")
    private Long idCanton;

    @Column(name = "reference")
    private String reference;

    @Column(nullable = true)
    private Boolean EnableConnexionStatus=false;

    @Column(nullable = true)
    private String codeConnexion=null;

    @Column(nullable = true,length = 5000)
    private String tempFingerPrintKey=null;

    @Column( nullable = true)
    private Long DateAccessConexionTimeStamp=null;

    @Column(name = "status", nullable = false)
    private boolean etat = true;
}
