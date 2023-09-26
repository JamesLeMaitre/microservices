package com.esmc.gestionMembre.services.recherche;


import com.esmc.gestionMembre.entities.Gcp;
import com.esmc.gestionMembre.repositories.GcpRepository;
import com.esmc.gestionMembre.serviceInterfaces.recherche.GcpServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Amemorte99
 */
@Transactional
@Service
public class GcpServiceImpl implements GcpServiceInterface {



    @Autowired
    private GcpRepository gcpRepository;



    @Override
    public List<Gcp> getGcpByCode(String nomOrCode) {
        return gcpRepository.getGcpByCode(nomOrCode);
    }

}
