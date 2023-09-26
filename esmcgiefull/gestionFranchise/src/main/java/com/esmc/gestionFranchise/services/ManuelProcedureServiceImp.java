package com.esmc.gestionFranchise.services;


import com.esmc.gestionFranchise.repositories.ManuelProcedureRepo;
import com.esmc.gestionFranchise.entities.ManuelProcedure;
import com.esmc.gestionFranchise.servicesInterface.ManuelProcedureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
@Service
@Transactional
public class ManuelProcedureServiceImp implements ManuelProcedureService {
    @Autowired
    private ManuelProcedureRepo manuelProcedureRepo;

    @Override
    public List<ManuelProcedure> getmanuelProcedure() {
        return manuelProcedureRepo.findAll();
    }

    @Override
    public List<ManuelProcedure> getmanuelProcedurebyTache(Long id) {
        return manuelProcedureRepo.getManuelByTache(id);
    }

    @Override
    public ManuelProcedure getmanuelProcedurebyId(Long id) {
        return manuelProcedureRepo.findById(id).get();
    }

    @Override
    public void deletemanuelProcedure(Long id) {
        manuelProcedureRepo.deleteById(id);
    }

    @Override
    public ManuelProcedure listManuelProcedure(Long id) {
        return manuelProcedureRepo.findManuelProcedureBytache(id);
    }

    @Override
    public ManuelProcedure ajoutermanuelProcedure(ManuelProcedure manuelProcedure) {
        manuelProcedure.setDateCreate(new Date());
        manuelProcedure.setDateUpdate(new Date());
        return manuelProcedureRepo.save(manuelProcedure);
    }

}
