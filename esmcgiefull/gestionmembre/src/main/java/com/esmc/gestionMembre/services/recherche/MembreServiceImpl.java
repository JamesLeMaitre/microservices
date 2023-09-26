package com.esmc.gestionMembre.services.recherche;


import com.esmc.gestionMembre.entities.Membre;
import com.esmc.gestionMembre.repositories.MembreRepository;
import com.esmc.gestionMembre.serviceInterfaces.recherche.MembreServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class MembreServiceImpl implements MembreServiceInterface {


    /**
     * @author Amemorte99
     */

    @Autowired
    private MembreRepository membreRepository;




    @Override
    public Page<Membre> pageListMembrePPSearchByAttributeNom(String search, int offset, int pageSize) {
        return membreRepository.searchByAttributeNom(search, PageRequest.of(offset, pageSize));
    }


    @Override
    public Membre getMembreByCodeMembreOrNomMembre(String codeOrName) {
        return membreRepository.getMembreByCodeMembreOrNomMembre(codeOrName);
    }

    @Override
    public Membre getMembreByCodeMembreOrNomPrenom(String nom, String prenom) {
        return membreRepository.getMembreByCodeMembreOrNomPrenom(nom,prenom);
    }


}
