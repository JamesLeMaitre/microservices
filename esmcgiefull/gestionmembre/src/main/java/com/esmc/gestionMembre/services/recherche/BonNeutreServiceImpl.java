package com.esmc.gestionMembre.services.recherche;


import com.esmc.gestionMembre.entities.BonNeutre;
import com.esmc.gestionMembre.repositories.BonNeutreRepository;
import com.esmc.gestionMembre.serviceInterfaces.recherche.BonNeutreServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Amemorte99
 */
@Transactional
@Service
public class BonNeutreServiceImpl implements BonNeutreServiceInterface {



    @Autowired
    private BonNeutreRepository bonNeutreRepository;



    @Override
    public List<BonNeutre> getBonNeutreByCode(String nomOrCode) {
        return bonNeutreRepository.findBonNeutreByCode(nomOrCode);
    }
}
