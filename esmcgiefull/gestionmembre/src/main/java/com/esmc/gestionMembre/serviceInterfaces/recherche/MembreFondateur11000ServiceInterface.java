package com.esmc.gestionMembre.serviceInterfaces.recherche;

import com.esmc.gestionMembre.entities.MembreFondateur11000;

import java.util.List;

public interface MembreFondateur11000ServiceInterface {
    List<MembreFondateur11000> getByMembreFondateur11000CodeMembre(String nomOrCode);
}
