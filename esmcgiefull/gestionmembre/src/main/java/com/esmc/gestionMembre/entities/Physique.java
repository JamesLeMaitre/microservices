package com.esmc.gestionMembre.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "physique")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Physique implements Serializable {

    @Id
    @Column(name = "numidentp")
    private String numIdentp;

    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "sexe")
    private String sexe;

    @Column(name = "datenais")
    private String dateNais;

    @Column(name = "lieunais")
    private String lieuNais;

    @Column(name = "nationalte")
    private String nationalite;

    @Column(name = "formation")
    private String formation;

    @Column(name = "pere")
    private String pere;

    @Column(name = "mere")
    private String mere;

    @Column(name = "sitmatr")
    private String sitmatr;

    @Column(name = "nbrenf")
    private int nbrenf;

    @Column(name = "quartierid")
    private String quartierId;

    @Column(name = "ville")
    private String ville;

    @Column(name = "bp")
    private String bp;

    @Column(name = "tel")
    private String tel;

    @Column(name = "email")
    private String email;

    @Column(name = "dateident")
    private String dateIdent;

    @Column(name = "portable")
    private String portable;

    @Column(name = "numcomptbp")
    private String numComptbq;

    @Column(name = "emprunt")
    private String emprunt;

    @Column(name = "agence")
    private String agence;

    @Column(name = "heureid")
    private String heureId;

    @Column(name = "religion")
    private String religion;

    @Column(name = "user")
    private String user;

    @Column(name = "etat_contrat")
    private boolean etatContrat;

    
}
