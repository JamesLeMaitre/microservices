package com.esmc.gestionMembre.services.recherche;

import com.esmc.gestionMembre.entities.AncienCompteCredit;
import com.esmc.gestionMembre.repositories.AncienCompteCreditRepository;
import com.esmc.gestionMembre.serviceInterfaces.recherche.AncienCompteCreditServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * @author Amemorte99
 */

@Service
@Transactional
public class AncienCompteCreditServiceImpl implements AncienCompteCreditServiceInterface {


    @Autowired
    private AncienCompteCreditRepository ancienCompteCreditRepository;




    @Override
    public List<AncienCompteCredit> getAncienCompteCreditByCode(String nomOrCode) {
        return ancienCompteCreditRepository.findAncienCompteCreditByCode(nomOrCode);
    }
}
