package com.esmc.gestionFranchise.services;

import com.esmc.gestionFranchise.repositories.CoffreFortRepo;
import com.esmc.gestionFranchise.entities.CoffreFort;
import com.esmc.gestionFranchise.servicesInterface.CoffreFortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class CoffreFortServiceImp implements CoffreFortService {
    @Autowired
    private CoffreFortRepo coffreFortRepo;
    @Override
    public List<CoffreFort> getCoffreFort() {
        return coffreFortRepo.findAll();
    }

    @Override
    public CoffreFort ajouterCoffreFort(CoffreFort coffreFort) {
        coffreFortRepo.save(coffreFort);
        return coffreFort;
    }

    @Override
    public CoffreFort getCoffreFortbyId(Long id) {
        return coffreFortRepo.findById(id).get();
    }

    @Override
    public void deleteCoffreFort(Long id) {
        coffreFortRepo.deleteById(id);
    }
}
