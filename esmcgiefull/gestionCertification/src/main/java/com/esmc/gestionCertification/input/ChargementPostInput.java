package com.esmc.gestionCertification.input;


import com.esmc.gestionCertification.entities.AppelCandidature;
import com.esmc.gestionCertification.entities.TypeChargement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ChargementPostInput {

    private Long detailAgr;

    private Long idDetailAgrFranchiser;

    private TypeChargement typeChargement;

    private Long[] idPoste;

    private AppelCandidature candidature;

}
