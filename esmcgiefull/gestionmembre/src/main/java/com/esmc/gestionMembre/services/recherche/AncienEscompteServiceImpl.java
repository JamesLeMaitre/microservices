package com.esmc.gestionMembre.services.recherche;


import com.esmc.gestionMembre.entities.AncienEscompte;
import com.esmc.gestionMembre.repositories.AncienEscompteRepository;
import com.esmc.gestionMembre.serviceInterfaces.recherche.AncienEscompteServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Amemorte99
 */

@Service
@Transactional
public class AncienEscompteServiceImpl implements AncienEscompteServiceInterface {



    @Autowired
    private AncienEscompteRepository ancienEscompteRepository;




    @Override
    public List<AncienEscompte> getAncienEscompteByCode(String nomOrCode) {
        return ancienEscompteRepository.findAncienEscompteByCode(nomOrCode);
    }

}
