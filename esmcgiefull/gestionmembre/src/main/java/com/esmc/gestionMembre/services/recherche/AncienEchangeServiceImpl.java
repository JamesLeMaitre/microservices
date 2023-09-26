package com.esmc.gestionMembre.services.recherche;

import com.esmc.gestionMembre.entities.AncienEchange;
import com.esmc.gestionMembre.repositories.AncienEchangeRepository;
import com.esmc.gestionMembre.serviceInterfaces.recherche.AncienEchangeServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


/**
 * @author Amemorte99
 */

@Service
@Transactional
public class AncienEchangeServiceImpl implements AncienEchangeServiceInterface {


    @Autowired
    private AncienEchangeRepository ancienEchangeRepository;




    @Override
    public List<AncienEchange> getAncienEchangeByCode(String nomOrCode) {
        return ancienEchangeRepository.findAncienEchangeByCode(nomOrCode);
    }
}
