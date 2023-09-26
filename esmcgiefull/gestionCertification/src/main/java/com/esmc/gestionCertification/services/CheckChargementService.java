package com.esmc.gestionCertification.services;

import com.esmc.gestionCertification.entities.CheckCandidature;
import com.esmc.models.Ksu;

import java.util.List;

public interface CheckChargementService {
    List<CheckCandidature> getAll();
    //CheckCandidature save(CheckCandidature checkChargement);

    CheckCandidature save(CheckCandidature checkCandidature);

    Object postePostule(Long idPoste);

    Boolean checkExist(Long idPoste);

    Ksu getKsu(Long idDetailAgr);
}
