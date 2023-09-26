package com.esmc.gestionMembre.services.recherche;


import com.esmc.gestionMembre.entities.CompteCredit;
import com.esmc.gestionMembre.repositories.CompteCreditRepository;
import com.esmc.gestionMembre.serviceInterfaces.recherche.CompteCreditServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Amemorte99
 */
@Transactional
@Service
public class CompteCreditServiceImpl implements CompteCreditServiceInterface {



    @Autowired
    private CompteCreditRepository compteCreditRepository;



    @Override
    public List<CompteCredit> getCompteCreditByCode(String nomOrCode) {
        return compteCreditRepository.getCompteCreditByCode(nomOrCode);
    }
}
