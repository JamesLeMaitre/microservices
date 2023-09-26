package com.esmc.gestionMembre.serviceInterfaces.recherche;

import com.esmc.gestionMembre.entities.AncienEchange;

import java.util.List;

public interface AncienEchangeServiceInterface {
    List<AncienEchange> getAncienEchangeByCode(String nomOrCode);
}
