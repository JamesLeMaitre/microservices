package com.esmc.gestionAvr.inputs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InfoBonBCI {
    private String idbon;
    private String numContratAchat;
    private String numBonCommande;
    private String numBonLivraison;
    private String valeurInitiale;
    private String valeurReinitialise;
    private String dateEmission;
    private String dateExpiration;
    private String dateExpression;
}
