package com.esmc.gestionFranchise.servicesInterface;

import com.esmc.gestionFranchise.entities.CoffreFort;

import java.util.List;

public interface CoffreFortService {
    List<CoffreFort> getCoffreFort();
    CoffreFort ajouterCoffreFort(CoffreFort CoffreFort);
    CoffreFort getCoffreFortbyId(Long id);
    void deleteCoffreFort(Long id);
}
