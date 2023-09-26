package com.esmc.gestionFranchise.services;

import com.esmc.gestionFranchise.repositories.OrganeRepo;
import com.esmc.gestionFranchise.entities.Organe;
import com.esmc.gestionFranchise.servicesInterface.OrganeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
@Service
@Transactional
public class OrganeServiceImp implements OrganeService {
    @Autowired
    private OrganeRepo organeRepo;
    @Override
    public List<Organe> getOrgane() {
        return organeRepo.findAll();
    }

    @Override
    public List<Organe> getOrganebyCadmin(Long id) {
        return organeRepo.getOrganebyCadmin(id);
    }

    @Override
    public Organe ajouterOrgane(Organe organe) {
        organe.setDateCreate(new Date());
        organe.setDateUpdate(new Date());
        return organeRepo.save(organe);
    }

    @Override
    public Organe getOrganebyId(Long id) {
        return organeRepo.findById(id).get();
    }

    @Override
    public void deleteOrgane(Long id) {
        organeRepo.deleteById(id);
    }
}
