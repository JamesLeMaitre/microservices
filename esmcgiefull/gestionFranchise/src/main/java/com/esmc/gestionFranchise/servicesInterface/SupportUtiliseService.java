package com.esmc.gestionFranchise.servicesInterface;

import com.esmc.gestionFranchise.entities.SupportUtilise;

import java.util.List;

public interface SupportUtiliseService {
    List<SupportUtilise> getSupportUtilise();
    SupportUtilise ajouterSupportUtilise(Long idTdep,SupportUtilise SupportUtilise);
    SupportUtilise getSupportUtilisebyId(Long id);
    void deleteSupportUtilise(Long id);
    List<SupportUtilise> getByTdep(Long idTdep);
}
