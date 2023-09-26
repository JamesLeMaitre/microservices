package com.esmc.gestionPayement.Services;


import com.esmc.gestionPayement.Repositories.TraitementDemandeDebitInputRepo;
import com.esmc.gestionPayement.ServicesInterface.TraitementDemandeDebitInputService;
import com.esmc.gestionPayement.entities.TraitementDemandeDebitInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TraitementDemandeDebitInputImp implements TraitementDemandeDebitInputService {

    @Autowired
    private TraitementDemandeDebitInputRepo traitementDemandeDebitInputRepo;
    @Override
    public List<TraitementDemandeDebitInput> getTraitementDemandeDebitInput() {
        return traitementDemandeDebitInputRepo.findAll();
    }

    @Override
    public TraitementDemandeDebitInput TraitementDemandeDebitInput(Long id) {
        return traitementDemandeDebitInputRepo.findById(id).get();
    }

    @Override
    public TraitementDemandeDebitInput saveTraitementDemandeDebitInput(TraitementDemandeDebitInput traitementDemandeDebitInput) {
        return traitementDemandeDebitInputRepo.save(traitementDemandeDebitInput);
    }

    @Override
    public void deleteTraitementDemandeDebitInput(Long id) {
        traitementDemandeDebitInputRepo.deleteById(id);
    }

    @Override
    public void updateTraitementDemandeDebitInput(Long id, TraitementDemandeDebitInput traitementDemandeDebitInput) {
        traitementDemandeDebitInputRepo.save(traitementDemandeDebitInput);
    }
}
