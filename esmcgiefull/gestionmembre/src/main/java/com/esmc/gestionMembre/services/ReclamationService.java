package com.esmc.gestionMembre.services;

import com.esmc.gestionMembre.entities.Reclamation;
import com.esmc.gestionMembre.repositories.ReclamationRepository;
import com.esmc.gestionMembre.serviceInterfaces.ReclamationServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReclamationService implements ReclamationServiceInterface {
    @Autowired
    private ReclamationRepository reclamationRepository;


    @Override
    public List<Reclamation> getReclamation() {
        return reclamationRepository.findAll();
    }

    @Override
    public Reclamation ajouterReclamation(Reclamation reclamation) {
        return reclamationRepository.save(reclamation);
    }

    @Override
    public Reclamation getReclamationbyId(Long id) {
        return reclamationRepository.findById(id).get();
    }

    @Override
    public Reclamation updateReclamation(Long id, Reclamation reclamation) {

        Reclamation r =  reclamationRepository.findById(id).orElse(null);
        assert r != null : "ID null";
        r.setCode_membre(reclamation.getCode_membre());
        r.setSysteme(reclamation.getSysteme());
        r.setProduit(reclamation.getProduit());
        r.setMotif(reclamation.getMotif());
        r.setEtat(reclamation.isEtat());
        r.setTableau_fichier(reclamation.getTableau_fichier());

        return reclamationRepository.save(r);
    }

    @Override
    public void deleteReclamation(Long id) {
        reclamationRepository.deleteById(id);

    }


}
