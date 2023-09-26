package com.esmc.gestionFranchise.services;


import com.esmc.gestionFranchise.repositories.TacheRepo;
import com.esmc.gestionFranchise.entities.Tache;
import com.esmc.gestionFranchise.servicesInterface.TacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class TacheServiceImp implements TacheService {

    @Autowired
    private TacheRepo tacheRepo;

    @Override
    public List<Tache> getTache() {
        return tacheRepo.findAll();
    }

    @Override
    public List<Tache> getTachebyFichedePoste(Long id) {
        return tacheRepo.getTachebyfichedeposte(id);
    }

    @Override
    public Tache ajouterTache(Tache tache) {
        tache.setDateCreate(new Date());
        tache.setDateUpdate(new Date());
        return tacheRepo.save(tache);
    }
    @Override
    public Tache getTachebyId(Long id) {
        return tacheRepo.findById(id).get();
    }

    @Override
    public void deleteTache(Long id) {
        tacheRepo.deleteById(id);
    }

    @Override
    public List<Tache> listTache(Long id) {
        return tacheRepo.findTacheByAgentRecruteur(id);
    }

    @Override
    public Tache updatetache(Long id,Tache tache) {
        Tache tache1 = tacheRepo.findById(id).orElse(null);

        assert tache1 != null : "Null id";
        tache1.setEtat(tache.isEtat());
        tache1.setLibelle(tache.getLibelle());
        tache1.setFichePoste(tache.getFichePoste());
        return tacheRepo.save(tache1);
    }
}
