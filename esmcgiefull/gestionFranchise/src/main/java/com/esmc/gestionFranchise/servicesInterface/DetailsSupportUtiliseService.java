package com.esmc.gestionFranchise.servicesInterface;

import com.esmc.gestionFranchise.entities.DetailsSupportUtilise;

import java.util.List;

public interface DetailsSupportUtiliseService {
    List<DetailsSupportUtilise> getDetailsSupportUtilise();
    List<DetailsSupportUtilise> getDetailsSupportUtilisebyTdep(Long id);
    DetailsSupportUtilise ajouterDetailsSupportUtilise(DetailsSupportUtilise DetailsSupportUtilise);
    DetailsSupportUtilise getDetailsSupportUtilisebyId(Long id);
    void deleteDetailsSupportUtilise(Long id);
}
