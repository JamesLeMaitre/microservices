package com.esmc.gestionAchatFranchise.services.felm.referentiel;


import com.esmc.gestionAchatFranchise.entities.franchise.refereniel.FranchiseReferentiel;
import com.esmc.gestionAchatFranchise.repositories.franchise.referenciel.FranchiseReferentielRepository;
import com.esmc.gestionAchatFranchise.servicesinterfaces.felm.referentiel.FranchiseReferentielInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class FrancheReferentielService implements FranchiseReferentielInterface {
    @Autowired
    private FranchiseReferentielRepository franchiseReferentielRepository;
    @Override
    public List<FranchiseReferentiel> getByIdDetailAgr(Long id) {
        List<FranchiseReferentiel> listRef = franchiseReferentielRepository.getByIdDetailAgr(id);
        return listRef;
    }
}
