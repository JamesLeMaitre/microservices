package com.esmc.gestionAvr.inputs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InfoBonCommande {
    private String acheteur;
    private String numBonCommande;
    private String date;
    private String lieu;
    private String  bnpc;
    private String vendeur;
    private String adresseComplete;
    private String numfacture;
    private String numOrdre;
    private String reference;
    private String articles;
    private String unites;
    private String  quantite;
    private String   prixUnitaire;
    private String   valeurs;
    private String   montantArrete;
}
