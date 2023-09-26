package com.esmc.gestionMembre.serviceInterfaces.recherche;

import com.esmc.gestionMembre.entities.Integrateur;

import java.util.List;

public interface IntegrateurServiceInterface {
    List<Integrateur> getIntegrateurByCodeMembre(String nomOrCode);
}
