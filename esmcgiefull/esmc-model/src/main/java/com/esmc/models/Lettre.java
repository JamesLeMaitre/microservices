package com.esmc.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @author katoh
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Lettre implements Serializable {


    private Long id;


    private String adresseAcheteur;


    private String bpAcheteur;


    private String villeAcheteur;


    private String telAcheteur;


    private String emailAcheteur;


    private String lieu;


    private String numeroRelance;


    private String numeroCommande;


    private String representationStructure;


    private String numeroBonCommande;


    private Date dateCommande;


    private String structureVendeur;


    private String articleCommande;


    private Double montantCommande;

    private Date dateCreate;

    private String numeroBonCommande2;


    private Date dateLivraison;

    private Date dateEcheance;


    private String numeroFactureProFormat;


    private byte[] signature;


    private byte[] cachet;


    private Date date;


    private String objet;


    private Date dateUpdate;

    private TypeLettre typeLettre;


    private Avr avr;

}
