package com.esmc.gestionMembre.services.recherche;

import com.esmc.gestionMembre.entities.AncienGcp;
import com.esmc.gestionMembre.repositories.AncienGcpRepository;
import com.esmc.gestionMembre.serviceInterfaces.recherche.AncienGcpServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * @author Amemorte99
 */

@Service
@Transactional
public class AncienGcpServiceImpl implements AncienGcpServiceInterface {


    @Autowired
    private AncienGcpRepository ancienGcpRepository;








    @Override
    public List<AncienGcp> getAncienGcpByCode(String nomOrCode) {
        return ancienGcpRepository.findAncienGcpByCode(nomOrCode);
    }



}
