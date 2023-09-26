package com.esmc.gestionMembre.serviceInterfaces.recherche;



import com.esmc.gestionMembre.entities.Membre;
import org.springframework.data.domain.Page;

import java.util.List;

public interface MembreServiceInterface  {

    Page<Membre> pageListMembrePPSearchByAttributeNom(String search, int offset, int pageSize);


    Membre getMembreByCodeMembreOrNomMembre(String codeOrName);

    Membre getMembreByCodeMembreOrNomPrenom(String nom, String prenom);
}
