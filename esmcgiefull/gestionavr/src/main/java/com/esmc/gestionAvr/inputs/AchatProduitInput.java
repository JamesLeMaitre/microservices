package com.esmc.gestionAvr.inputs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AchatProduitInput implements Serializable {
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

}
