package com.esmc.gestionFranchise.services;

import com.esmc.gestionFranchise.entities.SupportUtilise;
import com.esmc.gestionFranchise.entities.TableDescriptionEp;
import com.esmc.gestionFranchise.repositories.SupportUtiliseRepo;
import com.esmc.gestionFranchise.repositories.TableDescriptionEpRepo;
import com.esmc.gestionFranchise.servicesInterface.SupportUtiliseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class SupportUtiliseServiceImpl implements SupportUtiliseService {
    @Autowired
    private SupportUtiliseRepo supportUtiliseRepo;
    @Autowired
    private TableDescriptionEpRepo tableDescriptionEpRepo;

    @Override
    public List<SupportUtilise> getSupportUtilise() {
        return supportUtiliseRepo.findAll();
    }


    @Override
    public SupportUtilise ajouterSupportUtilise(Long idTdep, SupportUtilise supportUtilise) {
        TableDescriptionEp tableDescriptionEp = tableDescriptionEpRepo.findById(idTdep).orElse(null);
        supportUtilise.setTableDescriptionEp(tableDescriptionEp);
        return supportUtiliseRepo.save(supportUtilise);
    }


    @Override
    public SupportUtilise getSupportUtilisebyId(Long id) {
        return supportUtiliseRepo.findById(id).orElse(null);
    }


    @Override
    public void deleteSupportUtilise(Long id) {
        supportUtiliseRepo.deleteById(id);
    }

    @Override
    public List<SupportUtilise> getByTdep(Long idTdep) {
        return supportUtiliseRepo.getByTdep(idTdep);
    }
}
