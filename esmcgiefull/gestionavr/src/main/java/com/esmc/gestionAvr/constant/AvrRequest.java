package com.esmc.gestionAvr.constant;

import lombok.Data;

@Data
public class    AvrRequest {
    private String libelle;

    private int quantite;

    private Double prixUnitaire;

    private String description;

    private String photo;
    
    private Long idCategoryAvr;

    private Long idTypeAvr;
    private Long idDetailAgrVente;
    private Long idDetailAgrAcheteur;
    private Double montant;
    private String produit;

    private Long idVague;

    private Long idTokenRef;

}
