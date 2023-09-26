package com.esmc.gestionContrat.service;

import com.esmc.gestionContrat.entities.FicheSuiviContrat;
import com.esmc.gestionContrat.repositories.FicheSuiviContratRepository;
import com.esmc.gestionContrat.serviceInterfaces.FicheSuiviContratServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FicheSuiviContratService implements FicheSuiviContratServiceInterface {

    @Autowired
    private FicheSuiviContratRepository ficheSuiviContratRepository;

    @Override
    @Transactional
    public FicheSuiviContrat addFicheSuiviContrat(FicheSuiviContrat f) {
        return ficheSuiviContratRepository.save(f);
    }

    @Override
    @Transactional
    public FicheSuiviContrat updateFicheSuiviContrat(FicheSuiviContrat f) {
        return ficheSuiviContratRepository.save(f) ;
    }

    @Override
    @Transactional
    public void deleteFicheSuiviContrat(Long id) {
        ficheSuiviContratRepository.deleteById(id);
    }

    @Override
    public List<FicheSuiviContrat> listFicheSuiviContrat() {
        return ficheSuiviContratRepository.findAll();
    }
}
