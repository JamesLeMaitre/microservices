package com.esmc.gestionformation.inputs;

import com.esmc.gestionformation.entities.AffectationSalle;
import com.esmc.gestionformation.entities.Code;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ObjectInput {
    private String code;

    private Long idAgr;
    private Long idDetailAgrFranchiser;
    private Long idPoste;

    private Long state;

    private Long idKSU;

    private AffectationSalle affectationSalle;
}
