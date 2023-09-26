package com.esmc.gestionMembre.serviceInterfaces.recherche;

import com.esmc.gestionMembre.entities.AncienMembre;
import org.springframework.data.domain.Page;

import java.util.List;


public interface AncienMembreServiceInterface {

    // ancien membre search
    Page<AncienMembre> pageListAncienMembrePPSearch(String search, int offset, int pageSize);


    Page<AncienMembre> pageListAncienMembrePMSearch(String search, int offset, int pageSize);



    AncienMembre ancienMembreByCodeOrName(String codeOrName);

    AncienMembre ancienMembreByNameAndLastName(String nom, String prenom);
}
