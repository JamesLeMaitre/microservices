package com.esmc.gestionFranchise.services;

import com.esmc.gestionFranchise.repositories.AssembleGeneraleRepo;
import com.esmc.gestionFranchise.entities.AssembleGenerale;
import com.esmc.gestionFranchise.servicesInterface.AssembleGeneraleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AssembleGeneraleServiceImp implements AssembleGeneraleService {
    @Autowired
    private AssembleGeneraleRepo assembleGeneraleRepo;
    @Override
    public List<AssembleGenerale> getAssembleGenerale() {
        return assembleGeneraleRepo.findAll();
    }

    @Override
    public AssembleGenerale ajouterAssembleGenerale(AssembleGenerale assembleGenerale) {
        assembleGeneraleRepo.save(assembleGenerale);
        return assembleGenerale;
    }

    @Override
    public AssembleGenerale getAssembleGeneralebyId(Long id) {
        return assembleGeneraleRepo.findById(id).get();
    }

    @Override
    public void deleteAssembleGenerale(Long id) {
        assembleGeneraleRepo.deleteById(id);
    }
}
