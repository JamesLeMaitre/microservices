package com.esmc.gestionMembre.serviceInterfaces.recherche;

import com.esmc.gestionMembre.entities.DetailMf107;

import java.util.List;

public interface DetailMf107ServiceInterface {
    List<DetailMf107> getDetailMf107ByCode(String nomOrCode);
}
