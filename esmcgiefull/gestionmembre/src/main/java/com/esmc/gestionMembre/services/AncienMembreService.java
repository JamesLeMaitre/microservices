package com.esmc.gestionMembre.services;


import com.esmc.gestionMembre.entities.AncienMembre;
import com.esmc.gestionMembre.repositories.AncienMembreRepository;
import com.esmc.gestionMembre.serviceInterfaces.recherche.AncienMembreServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class AncienMembreService implements AncienMembreServiceInterface {

    /**
     * @author Amemorte99
     */

    @Autowired
    private AncienMembreRepository ancienMembreRepository;

    @Override
    public Page<AncienMembre> pageListAncienMembrePPSearch(String search, int offset, int pageSize) {
        return ancienMembreRepository.searchByAttributeNomPP(search,PageRequest.of(offset, pageSize));
    }

    @Override
    public Page<AncienMembre> pageListAncienMembrePMSearch(String search, int offset, int pageSize) {
        return ancienMembreRepository.searchByAttributeNomPM(search,PageRequest.of(offset, pageSize));
    }

    @Override
    public AncienMembre ancienMembreByCodeOrName(String codeOrName) {
        return ancienMembreRepository.findancienMembreByCodeOrName(codeOrName);
    }



    @Override
    public AncienMembre ancienMembreByNameAndLastName(String nom,String prenom) {
        return ancienMembreRepository.findancienMembreByNomAndPrenom(nom,prenom);
    }
}
