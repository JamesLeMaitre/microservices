package com.esmc.gestionMembre.serviceInterfaces.recherche;


import com.esmc.gestionMembre.entities.AncienEscompte;

import java.util.List;

public interface AncienEscompteServiceInterface {
    List<AncienEscompte> getAncienEscompteByCode(String nomOrCode);
}
