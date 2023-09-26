package com.esmc.gestionMembre.services.recherche;


import com.esmc.gestionMembre.entities.MembreMorale;
import com.esmc.gestionMembre.repositories.MembreMoraleRepository;
import com.esmc.gestionMembre.serviceInterfaces.MembreMoraleInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class MembreMoraleImpl implements MembreMoraleInterface {


    /**
     * @author Amemorte99
     */
    @Autowired
    private MembreMoraleRepository membreMoraleRepository;


    @Override
    public Page<MembreMorale> pageListMembreMoraleByAttributeDateLieuNaiss(String search, int offset, int pageSize) {
        return membreMoraleRepository.searchByAttributeDateLieuNaiss(search, PageRequest.of(offset, pageSize));
    }


    @Override
    public MembreMorale getMembreByCodeMembreMoraleOrNomMembre(String codeOrName) {
        return membreMoraleRepository.getMembreByCodeMembreMoraleOrNomMembre(codeOrName);
    }
}
