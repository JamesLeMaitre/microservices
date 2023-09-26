package com.esmc.gestionMembre.serviceInterfaces.recherche;

import com.esmc.gestionMembre.entities.RepartitionMf107;

import java.util.List;

public interface RepartitionMf107ServiceInterface {
    List<RepartitionMf107> getRepartitionMf107ByCode(String nomOrCode);
}
