package com.esmc.gestionFranchise.servicesInterface;


import com.esmc.gestionFranchise.entities.Organe;


import java.util.List;

public interface OrganeService {
    List<Organe> getOrgane();
    List<Organe> getOrganebyCadmin(Long id);
    Organe ajouterOrgane(Organe organe);
    Organe getOrganebyId(Long id);
    void deleteOrgane(Long id);

}
