package com.esmc.gestionAvr.inputs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InfoBonLivraison {
     private String acteurVendeur;
     private String adresseTel;
    private String numBonLivraison;
    private String date;
    private String lieu;
    private String numBonCommande;
    private String acteurAcheteur;
    private String adresseComplete;
    private String numOrdre;
    private String reference;
    private String articles;
    private String unites;
    private String quantite;
    private String prixUnitaire;
    private String valeurs;
    private String totalHt;
    private String tva;
    private String tottalTtc;
}
