package com.esmc.gestionMembre.serviceInterfaces.recherche;

import com.esmc.gestionMembre.entities.Morale;
import org.springframework.data.domain.Page;

public interface MoraleServiceInterface {

    Page<Morale> pageListMoraleSearch(String search, int offset, int pageSize);

    Morale getReDeMaReByMoraleNom(String nomOrCcode);
}
