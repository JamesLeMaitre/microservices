package com.esmc.gestionFranchise.services;


import com.esmc.gestionFranchise.entities.ActiviteBudgetAssocie;
import com.esmc.gestionFranchise.repositories.ActiviteBudgetAssocieRepo;
import com.esmc.gestionFranchise.servicesInterface.ActiviteBudgetAssocieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ActiviteBudgetAssocieServiceImp implements ActiviteBudgetAssocieService {

    @Autowired
    private ActiviteBudgetAssocieRepo activiteBudgetAssocieRepo;

    @Override
    public List<ActiviteBudgetAssocie> getActiviteBudgetAssocie() {
        return activiteBudgetAssocieRepo.findAll();
    }

    @Override
    public ActiviteBudgetAssocie ajouterActiviteBudgetAssocie(ActiviteBudgetAssocie activiteBudgetAssocie) {
        activiteBudgetAssocieRepo.save(activiteBudgetAssocie);
        return activiteBudgetAssocie;
    }

    @Override
    public ActiviteBudgetAssocie getActiviteBudgetAssociebyId(Long id) {
        return activiteBudgetAssocieRepo.findById(id).get();
    }

    @Override
    public void deleteActiviteBudgetAssocie(Long id) {
        activiteBudgetAssocieRepo.deleteById(id);
    }
}
