package com.esmc.gestionPayement.inputs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CorisInput {

    private String codePays;
    private String telephone;
    private String codePv;
    private String codeRetrait;
    private String montant;
    private boolean montants;

}
