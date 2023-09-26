package com.esmc.gestionMembre.services.recherche;


import com.esmc.gestionMembre.entities.Integrateur;
import com.esmc.gestionMembre.repositories.IntegrateurRepository;
import com.esmc.gestionMembre.serviceInterfaces.recherche.IntegrateurServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Amemorte99
 */
@Transactional
@Service
public class IntegrateurServiceImpl implements IntegrateurServiceInterface {


    @Autowired
    private IntegrateurRepository integrateurRepository;


    @Override
    public List<Integrateur> getIntegrateurByCodeMembre(String nomOrCode) {
        return integrateurRepository.findIntegrateurByCodeMembre(nomOrCode);
    }
}
