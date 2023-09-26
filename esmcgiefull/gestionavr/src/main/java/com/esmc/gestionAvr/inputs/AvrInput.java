package com.esmc.gestionAvr.inputs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AvrInput {
    private Long idDetailAgrVente;
    private int quantite;
    private Long idDetailAgrAcheteur;
    private String codeBan;
    private String produit;

}
