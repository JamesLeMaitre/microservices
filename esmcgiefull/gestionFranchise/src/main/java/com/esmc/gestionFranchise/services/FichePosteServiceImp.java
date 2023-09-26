package com.esmc.gestionFranchise.services;


import com.esmc.gestionFranchise.entities.organev2.Poste;
import com.esmc.gestionFranchise.entities.organev2.Structure;
import com.esmc.gestionFranchise.repositories.FichePosteRepo;
import com.esmc.gestionFranchise.entities.FichePoste;
import com.esmc.gestionFranchise.repositories.organev2.PosteRepo;
import com.esmc.gestionFranchise.servicesInterface.FichePosteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class FichePosteServiceImp implements FichePosteService {

    @Autowired
    private FichePosteRepo fichePosteRepo;

    @Autowired
    private PosteRepo posteRepo;

    @Override
    public List<FichePoste> getFichePoste() {
        return fichePosteRepo.findAll();
    }

    @Override
    public FichePoste ajouterFichePoste(FichePoste data) {
        return fichePosteRepo.save(data);
    }
    @Override
    public FichePoste modifierFichePoste(Long id,FichePoste data) {

          FichePoste fichePoste=getById(id);

        fichePoste.setPoste(data.getPoste());
        fichePoste.setLibelle(data.getLibelle());


        return fichePosteRepo.save(fichePoste);
    }

    @Override
    public FichePoste getFichePostebyId(Long id) {
        return fichePosteRepo.findById(id).get();
    }

    @Override
    public void deleteFichePoste(Long id) {
        fichePosteRepo.deleteById(id);
    }

    @Override
    public FichePoste getById(Long id) {
        return fichePosteRepo.findById(id).get();
    }

    @Override
    public List<FichePoste> findFichePosteByPost(Long id) {
        return fichePosteRepo.findFichePosteByPost(id);
    }




}
