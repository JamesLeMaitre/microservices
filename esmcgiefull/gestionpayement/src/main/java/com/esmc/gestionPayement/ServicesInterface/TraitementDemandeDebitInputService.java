package com.esmc.gestionPayement.ServicesInterface;


import com.esmc.gestionPayement.entities.TraitementDemandeDebitInput;

import java.util.List;

public interface TraitementDemandeDebitInputService {
    List<TraitementDemandeDebitInput> getTraitementDemandeDebitInput();

    TraitementDemandeDebitInput TraitementDemandeDebitInput(Long id);

    TraitementDemandeDebitInput saveTraitementDemandeDebitInput(TraitementDemandeDebitInput traitementDemandeDebitInput);

    void deleteTraitementDemandeDebitInput(Long id);

    void updateTraitementDemandeDebitInput(Long id, TraitementDemandeDebitInput traitementDemandeDebitInput);
}
