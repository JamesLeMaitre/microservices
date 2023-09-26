package com.esmc.gestionformation.inputs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ValidationInput {
    private Long idAgr;
    private Long idPoste;
    private Long idDetailAgrFranchiser;
}
