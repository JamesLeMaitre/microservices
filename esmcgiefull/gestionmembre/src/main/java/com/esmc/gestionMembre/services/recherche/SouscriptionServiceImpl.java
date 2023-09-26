package com.esmc.gestionMembre.services.recherche;


import com.esmc.gestionMembre.entities.Souscription;
import com.esmc.gestionMembre.repositories.SouscriptionRepository;
import com.esmc.gestionMembre.serviceInterfaces.recherche.SouscriptionServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Amemorte99
 */
@Transactional
@Service
public class SouscriptionServiceImpl implements SouscriptionServiceInterface {


    @Autowired
    private SouscriptionRepository souscriptionRepository;



    @Override
    public List<Souscription> getSouscriptionByCodeOrName(String nomOrCode) {
        return souscriptionRepository.findSouscriptionByCodeOrName(nomOrCode);
    }
}
