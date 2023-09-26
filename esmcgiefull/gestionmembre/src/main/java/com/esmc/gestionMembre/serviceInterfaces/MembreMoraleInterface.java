package com.esmc.gestionMembre.serviceInterfaces;


import com.esmc.gestionMembre.entities.MembreMorale;
import org.springframework.data.domain.Page;

import java.util.List;

public interface MembreMoraleInterface {
    Page<MembreMorale> pageListMembreMoraleByAttributeDateLieuNaiss(String search, int offset, int pageSize);

    MembreMorale getMembreByCodeMembreMoraleOrNomMembre(String codeOrName);
}
