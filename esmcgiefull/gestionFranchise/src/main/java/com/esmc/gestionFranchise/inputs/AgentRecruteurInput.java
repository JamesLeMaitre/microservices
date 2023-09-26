package com.esmc.gestionFranchise.inputs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AgentRecruteurInput {
    private Long idDetailAgrFranchiser;
    private Long idPoste;
    private Long idAgr;
}
