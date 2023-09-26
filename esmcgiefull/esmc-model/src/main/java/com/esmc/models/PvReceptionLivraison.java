package com.esmc.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;


/**
 * @author katoh
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PvReceptionLivraison implements Serializable {

    private Long id;

    private String nom;

    private String prenom;

    private String agent;

    private String numCommande;

    private String acheteur;

    private String agents;

    private String reserve;

    private String delai;

    private String fournisseur;

    private String designation;

    private String contacts;

    private String lieu;

    private Date dateCommande;

    private Date dateEffet;

    private Date datePv;

    private Date dateCreate;

    private Date dateUpdate;

    private Intervenant intervenant;
}
