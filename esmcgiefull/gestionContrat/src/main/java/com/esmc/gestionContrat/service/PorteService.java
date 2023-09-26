package com.esmc.gestionContrat.service;

import com.esmc.gestionContrat.entities.Porte;
import com.esmc.gestionContrat.repositories.PorteRepositorey;
import com.esmc.gestionContrat.serviceInterfaces.PorteServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class PorteService implements PorteServiceInterface {

    @Autowired
    private PorteRepositorey porteRepositorey;

    @Override
    @Transactional
    public Porte addPorte(Porte p) {
        return porteRepositorey.save(p);}

    @Override
    @Transactional
    public Porte updatePorte(Porte porte) {
        return porteRepositorey.save(porte);
    }

    @Override
    @Transactional
    public void deletePorte(Long id) { porteRepositorey.deleteById(id);}

    @Override
    public List<Porte> listPorte() {
        return porteRepositorey.findAll();
    }
}
