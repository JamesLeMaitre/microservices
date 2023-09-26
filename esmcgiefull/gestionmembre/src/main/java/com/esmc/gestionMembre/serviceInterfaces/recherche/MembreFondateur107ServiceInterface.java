package com.esmc.gestionMembre.serviceInterfaces.recherche;


import com.esmc.gestionMembre.entities.MembreFondateur107;

import java.util.List;

public interface MembreFondateur107ServiceInterface {
    List<MembreFondateur107> getByMembreFondateur107Code(String nomOrCode);
}
