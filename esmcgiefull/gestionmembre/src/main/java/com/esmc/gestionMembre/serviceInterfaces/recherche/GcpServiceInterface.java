package com.esmc.gestionMembre.serviceInterfaces.recherche;

import com.esmc.gestionMembre.entities.Gcp;

import java.util.List;

public interface GcpServiceInterface {
    List<Gcp> getGcpByCode(String nomOrCode);
}
