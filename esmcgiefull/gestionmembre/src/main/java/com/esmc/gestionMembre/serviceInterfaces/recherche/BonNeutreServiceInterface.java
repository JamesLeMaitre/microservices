package com.esmc.gestionMembre.serviceInterfaces.recherche;

import com.esmc.gestionMembre.entities.BonNeutre;

import java.util.List;

public interface BonNeutreServiceInterface {
    List<BonNeutre> getBonNeutreByCode(String nomOrCode);
}
