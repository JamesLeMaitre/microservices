package com.esmc.gestionMembre.services.recherche;


import com.esmc.gestionMembre.entities.AncienDetailsSmsmoney;
import com.esmc.gestionMembre.repositories.AncienDetailsSmsmoneyRepository;
import com.esmc.gestionMembre.serviceInterfaces.recherche.AncienDetailsSmsmoneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Amemorte99
 */

@Service
@Transactional
public class AncienDetailsSmsmoneyServiceImpl implements AncienDetailsSmsmoneyService {


    @Autowired
    private AncienDetailsSmsmoneyRepository ancienDetailsSmsmoneyRepository;




    @Override
    public List<AncienDetailsSmsmoney> getByAncienDetailsSmsmoneyCodeMembre(String nomOrCode) {
        return ancienDetailsSmsmoneyRepository.findByAncienDetailsSmsmoneyCodeMembre(nomOrCode);
    }
}
