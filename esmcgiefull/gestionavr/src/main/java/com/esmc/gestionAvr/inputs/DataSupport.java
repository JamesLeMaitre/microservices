package com.esmc.gestionAvr.inputs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DataSupport {
    private String idBon;

    // Decommenter si c'est pour les Bon de Consommation de Mevente
    //private InfoBonBCI infoBonBCI;
    //private InfoBonCommande infoBonCommande;

    // Ici pour Bond de Livraison
    private InfoBonLivraison infoBonLivraison;
    //private InfoBonContrat InfoBonContrat;
//    private String numOtrNifTwo;
//    private String dates;
//    private String  dateEmise;
   // private String[] preuve;
    private String preuve;
    private DataApprouved dataApouved;

}
