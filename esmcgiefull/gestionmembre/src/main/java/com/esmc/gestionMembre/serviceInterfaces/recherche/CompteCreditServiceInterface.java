package com.esmc.gestionMembre.serviceInterfaces.recherche;

import com.esmc.gestionMembre.entities.CompteCredit;

import java.util.List;

public interface CompteCreditServiceInterface {
    List<CompteCredit> getCompteCreditByCode(String nomOrCode);
}
