package com.esmc.gestionMembre.services.recherche;


import com.esmc.gestionMembre.entities.MembreFondateur11000;
import com.esmc.gestionMembre.repositories.MembreFondateur11000Repository;
import com.esmc.gestionMembre.serviceInterfaces.recherche.MembreFondateur11000ServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Amemorte99
 */
@Transactional
@Service
public class MembreFondateur11000ServiceImpl implements MembreFondateur11000ServiceInterface {

    @Autowired
    private MembreFondateur11000Repository membreFondateur11000Repository;




    @Override
    public List<MembreFondateur11000> getByMembreFondateur11000CodeMembre(String nomOrCode) {
        return membreFondateur11000Repository.findByMembreFondateur11000CodeMembre(nomOrCode);
    }
}
