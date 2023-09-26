package com.esmc.input;

import com.esmc.models.AppelCandidature;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckTraitementInput {
    private Long idSm;
    private Long idTeAcheteur;
    private Long idTeVendeur;
    private Double montant;
    private Long idDetailAgr;
    private Long idPoste;

    private Long idDetailAgrFranchiser;

    private Long candidature;
}
