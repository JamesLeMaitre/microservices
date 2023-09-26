package com.esmc.gestionFranchise.servicesInterface.organev2;

import com.esmc.gestionFranchise.entities.organev2.SupportIntervenant;

import java.util.List;

public interface SupportIntervenantService {


    List<SupportIntervenant> getAll();
    SupportIntervenant getById(Long id);

    SupportIntervenant create(SupportIntervenant data);
    SupportIntervenant update(Long id, SupportIntervenant data);

    SupportIntervenant disable(Long id);

    SupportIntervenant enable(Long id);

    void delete(Long id);

}
