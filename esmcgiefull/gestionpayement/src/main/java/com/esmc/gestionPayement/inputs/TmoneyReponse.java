package com.esmc.gestionPayement.inputs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TmoneyReponse {
    private String code;
    private String message;
    private String refTmoney;
    private String dateHeureTmoney;
    private String idRequete;
    private String statutRequete;
    private String typeRequete;
    private String dateHeureRequete;
    private String refCommande;
    private String montant;
    private String numeroClient;
    private String idPartenaire;

}
