package com.esmc.gestionMembre.serviceInterfaces.recherche;

import com.esmc.gestionMembre.entities.RepartitionMF11000;

import java.util.List;

public interface RepartitionMF11000ServiceInterface {
    List<RepartitionMF11000> getRepartitionMF11000ByCode(String nomOrCode);
}
