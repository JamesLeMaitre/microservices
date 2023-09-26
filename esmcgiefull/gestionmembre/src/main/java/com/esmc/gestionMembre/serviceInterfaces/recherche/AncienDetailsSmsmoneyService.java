package com.esmc.gestionMembre.serviceInterfaces.recherche;

import com.esmc.gestionMembre.entities.AncienDetailsSmsmoney;

import java.util.List;

public interface AncienDetailsSmsmoneyService {
    List<AncienDetailsSmsmoney> getByAncienDetailsSmsmoneyCodeMembre(String nomOrCode);
}
