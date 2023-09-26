package com.esmc.gestionAchatFranchise.servicesinterfaces.felm.referentiel;

import com.esmc.gestionAchatFranchise.entities.franchise.refereniel.FranchiseReferentiel;

import java.util.List;

public interface FranchiseReferentielInterface {
    List<FranchiseReferentiel> getByIdDetailAgr(Long id);
}
