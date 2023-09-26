package com.esmc.gestionFranchise.servicesInterface;

import com.esmc.gestionFranchise.entities.DetailOrgane;

import java.util.List;

public interface DetailOrganeService {
    List<DetailOrgane> getDetailOrgane();
    DetailOrgane ajouterDetailOrgane(DetailOrgane detailOrgane);
    DetailOrgane getDetailOrganebyId(Long id);
    void deleteDetailOrgane(Long id);
}
