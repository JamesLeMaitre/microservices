package com.esmc.gestionCertification.input;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StepInput {
    private Long idDetailAgr;
    private Long idDetailAgrFranchiser;
    private Long idPoste;

    private Long candidature;
}
