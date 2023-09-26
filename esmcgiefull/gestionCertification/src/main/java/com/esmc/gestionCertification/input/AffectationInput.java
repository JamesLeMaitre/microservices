package com.esmc.gestionCertification.input;


import com.esmc.gestionCertification.entities.AppelCandidature;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AffectationInput {
    private AppelCandidature candidature;

    private Long[] idPoste;

    private Long idDetailAgrFranchiser;


}
