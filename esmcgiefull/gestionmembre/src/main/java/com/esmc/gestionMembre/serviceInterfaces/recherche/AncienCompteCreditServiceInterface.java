package com.esmc.gestionMembre.serviceInterfaces.recherche;

import com.esmc.gestionMembre.entities.AncienCompteCredit;

import java.util.List;

public interface AncienCompteCreditServiceInterface {
    List<AncienCompteCredit> getAncienCompteCreditByCode(String nomOrCode);
}
