package com.esmc.gestionMembre.services.recherche;



import com.esmc.gestionMembre.entities.Morale;
import com.esmc.gestionMembre.repositories.MoraleRepository;

import com.esmc.gestionMembre.serviceInterfaces.recherche.MoraleServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class MoraleServiceImpl implements MoraleServiceInterface {

    /**
     * @author Amemorte99
     */

    @Autowired
    private MoraleRepository  moraleRepository;


    @Override
    public Page<Morale> pageListMoraleSearch(String search, int offset, int pageSize) {
        return moraleRepository.pasgeMoraleSearch(search, PageRequest.of(offset, pageSize));
    }

    @Override
    public Morale getReDeMaReByMoraleNom(String nomOrCode) {
        return moraleRepository.findReDeMaReByMoraleNom(nomOrCode);
    }
}
