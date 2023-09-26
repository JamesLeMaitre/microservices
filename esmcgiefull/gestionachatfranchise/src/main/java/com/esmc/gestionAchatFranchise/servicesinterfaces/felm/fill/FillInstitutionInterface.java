package com.esmc.gestionAchatFranchise.servicesinterfaces.felm.fill;

import com.esmc.gestionAchatFranchise.entities.felm.fill.FillInstitution;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FillInstitutionInterface  {
    List<FillInstitution> getAll();
    FillInstitution  getById(Long id);

    FillInstitution  create(FillInstitution data);
    FillInstitution  update(Long id, FillInstitution data);

    FillInstitution disable(Long id);

    FillInstitution enable(Long id);

    int getCountAll();

    void delete(Long id);
}
