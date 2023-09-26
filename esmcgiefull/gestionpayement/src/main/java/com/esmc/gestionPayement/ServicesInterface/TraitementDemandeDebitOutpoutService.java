package com.esmc.gestionPayement.ServicesInterface;


import com.esmc.gestionPayement.entities.TraitementDemandeDebitOutpout;

import java.util.List;

public interface TraitementDemandeDebitOutpoutService {
    List<TraitementDemandeDebitOutpout> getTraitementDemandeDebitOutpout();

    TraitementDemandeDebitOutpout TraitementDemandeDebitOutpout(Long id);

    TraitementDemandeDebitOutpout saveTraitementDemandeDebitOutpout(TraitementDemandeDebitOutpout traitementDemandeDebitOutpout);

    void deleteTraitementDemandeDebitOutpout(Long id);

    void updateTraitementDemandeDebitOutpout(Long id, TraitementDemandeDebitOutpout traitementDemandeDebitOutpout);
}
