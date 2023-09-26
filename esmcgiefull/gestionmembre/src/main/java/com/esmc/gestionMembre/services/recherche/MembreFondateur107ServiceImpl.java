package com.esmc.gestionMembre.services.recherche;


import com.esmc.gestionMembre.entities.MembreFondateur107;
import com.esmc.gestionMembre.repositories.MembreFondateur107Repository;
import com.esmc.gestionMembre.serviceInterfaces.recherche.MembreFondateur107ServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Amemorte99
 */
@Transactional
@Service
public class MembreFondateur107ServiceImpl implements MembreFondateur107ServiceInterface {

    @Autowired
    private MembreFondateur107Repository membreFondateur107Repository;




    @Override
    public List<MembreFondateur107> getByMembreFondateur107Code(String nomOrCode) {
        return membreFondateur107Repository.findByMembreFondateur107Code(nomOrCode);
    }
}
