package com.esmc.gestionCertification.services;

import com.esmc.gestionCertification.entities.StepCertification;

import java.util.List;

public interface StepCertificationService {
    List<StepCertification> getAllStep();

    StepCertification save(StepCertification stepCertification);

    StepCertification update(Long id, StepCertification stepCertification);

    void delete(Long id);

    StepCertification getById(Long id);

    StepCertification get3Id(Long idDetailAgr,Long idDetailAgrFranchiser,Long idPoste);
}
