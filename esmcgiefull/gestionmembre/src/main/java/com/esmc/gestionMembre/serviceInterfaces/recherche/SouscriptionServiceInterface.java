package com.esmc.gestionMembre.serviceInterfaces.recherche;

import com.esmc.gestionMembre.entities.Souscription;

import java.util.List;

public interface SouscriptionServiceInterface {
    List<Souscription> getSouscriptionByCodeOrName(String nomOrCode);
}
