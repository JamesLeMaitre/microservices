package com.esmc.gestionFranchise.services;

import com.esmc.gestionFranchise.repositories.DetailOrganeRepo;
import com.esmc.gestionFranchise.entities.DetailOrgane;
import com.esmc.gestionFranchise.servicesInterface.DetailOrganeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class DetailOrganeServiceImp implements DetailOrganeService {
    @Autowired
    private DetailOrganeRepo detailOrganeRepo;
    @Override
    public List<DetailOrgane> getDetailOrgane() {
        return detailOrganeRepo.findAll();
    }

    @Override
    public DetailOrgane ajouterDetailOrgane(DetailOrgane detailOrgane) {
        detailOrganeRepo.save(detailOrgane);
        return detailOrgane;
    }

    @Override
    public DetailOrgane getDetailOrganebyId(Long id) {
        return detailOrganeRepo.findById(id).get();
    }

    @Override
    public void deleteDetailOrgane(Long id) {
        detailOrganeRepo.deleteById(id);

    }
}
