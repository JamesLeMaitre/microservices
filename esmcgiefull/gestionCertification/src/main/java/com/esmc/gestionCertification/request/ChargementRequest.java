package com.esmc.gestionCertification.request;

import com.esmc.gestionCertification.entities.AppelCandidature;
import com.esmc.gestionCertification.entities.TypeChargement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChargementRequest implements Serializable {
    private Long detailAgr;

    private Long idDetailAgrFranchiser;

    private TypeChargement typeChargement;

    private Long idPoste;

    private String libellePoste;

    private AppelCandidature candidature;
}
