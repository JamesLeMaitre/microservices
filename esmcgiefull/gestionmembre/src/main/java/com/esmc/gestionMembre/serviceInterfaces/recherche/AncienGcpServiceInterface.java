package com.esmc.gestionMembre.serviceInterfaces.recherche;

import com.esmc.gestionMembre.entities.AncienGcp;

import java.util.List;

public interface AncienGcpServiceInterface {
    List<AncienGcp> getAncienGcpByCode(String nomOrCode);
}
