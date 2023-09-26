package com.esmc.gestionPayement.Services;


import com.esmc.gestionPayement.Repositories.TraitementDemandeDebitOutpoutRepo;
import com.esmc.gestionPayement.ServicesInterface.TraitementDemandeDebitOutpoutService;
import com.esmc.gestionPayement.entities.TraitementDemandeDebitOutpout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TraitementDemandeDebitOutpoutImp  implements TraitementDemandeDebitOutpoutService {

    @Autowired
    private TraitementDemandeDebitOutpoutRepo traitementDemandeDebitOutpoutRepo;

    @Override
    public List<TraitementDemandeDebitOutpout> getTraitementDemandeDebitOutpout() {
        return traitementDemandeDebitOutpoutRepo.findAll();
    }

    @Override
    public TraitementDemandeDebitOutpout TraitementDemandeDebitOutpout(Long id) {
        return traitementDemandeDebitOutpoutRepo.findById(id).get();
    }

    @Override
    public TraitementDemandeDebitOutpout saveTraitementDemandeDebitOutpout(TraitementDemandeDebitOutpout traitementDemandeDebitOutpout) {
        return traitementDemandeDebitOutpoutRepo.save(traitementDemandeDebitOutpout);
    }

    @Override
    public void deleteTraitementDemandeDebitOutpout(Long id) {
        traitementDemandeDebitOutpoutRepo.deleteById(id);
    }

    @Override
    public void updateTraitementDemandeDebitOutpout(Long id, TraitementDemandeDebitOutpout traitementDemandeDebitOutpout) {
        traitementDemandeDebitOutpoutRepo.save(traitementDemandeDebitOutpout);
    }
}
