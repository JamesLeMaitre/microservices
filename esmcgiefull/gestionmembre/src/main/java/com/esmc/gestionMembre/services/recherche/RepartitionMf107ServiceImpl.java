package com.esmc.gestionMembre.services.recherche;

import com.esmc.gestionMembre.entities.RepartitionMf107;
import com.esmc.gestionMembre.repositories.RepartitionMf107Repository;
import com.esmc.gestionMembre.serviceInterfaces.recherche.RepartitionMf107ServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


/**
 * @author Amemorte99
 */
@Transactional
@Service
public class RepartitionMf107ServiceImpl implements RepartitionMf107ServiceInterface {


    @Autowired
    private RepartitionMf107Repository repartitionMf107Repository;




    @Override
    public List<RepartitionMf107> getRepartitionMf107ByCode(String nomOrCode) {
        return repartitionMf107Repository.getRepartitionMf107ByCode(nomOrCode);
    }
}
