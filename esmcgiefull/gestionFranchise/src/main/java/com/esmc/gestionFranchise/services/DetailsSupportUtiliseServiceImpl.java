package com.esmc.gestionFranchise.services;

import com.esmc.gestionFranchise.entities.DetailsSupportUtilise;
import com.esmc.gestionFranchise.repositories.DetailsSupportUtiliseRepo;
import com.esmc.gestionFranchise.servicesInterface.DetailsSupportUtiliseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DetailsSupportUtiliseServiceImpl  implements DetailsSupportUtiliseService {
    @Autowired
    private DetailsSupportUtiliseRepo detailsSupportUtiliseRepo;
    @Override
    public List<DetailsSupportUtilise> getDetailsSupportUtilise() {
        return detailsSupportUtiliseRepo.findAll();
    }

    @Override
    public List<DetailsSupportUtilise> getDetailsSupportUtilisebyTdep(Long id) {
        return detailsSupportUtiliseRepo.getDetailsSupportUtilisebyTdep( id);
    }

    @Override
    public DetailsSupportUtilise ajouterDetailsSupportUtilise(DetailsSupportUtilise detailsSupportUtilise) {
        return detailsSupportUtiliseRepo.save(detailsSupportUtilise);
    }

    @Override
    public DetailsSupportUtilise getDetailsSupportUtilisebyId(Long id) {
        return detailsSupportUtiliseRepo.findById(id).get();
    }

    @Override
    public void deleteDetailsSupportUtilise(Long id) {
    detailsSupportUtiliseRepo.deleteById(id);
    }
}
