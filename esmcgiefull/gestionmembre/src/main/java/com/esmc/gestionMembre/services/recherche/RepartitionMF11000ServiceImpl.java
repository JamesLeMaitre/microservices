package com.esmc.gestionMembre.services.recherche;


import com.esmc.gestionMembre.entities.RepartitionMF11000;
import com.esmc.gestionMembre.repositories.RepartitionMF11000Repository;
import com.esmc.gestionMembre.serviceInterfaces.recherche.RepartitionMF11000ServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Amemorte99
 */
@Transactional
@Service
public class RepartitionMF11000ServiceImpl implements RepartitionMF11000ServiceInterface {


    @Autowired
    private RepartitionMF11000Repository repartitionMF11000Repository;




    @Override
    public List<RepartitionMF11000> getRepartitionMF11000ByCode(String nomOrCode) {
        return repartitionMF11000Repository.getRepartitionMF11000ByCodeMembreOrName(nomOrCode);
    }
}
