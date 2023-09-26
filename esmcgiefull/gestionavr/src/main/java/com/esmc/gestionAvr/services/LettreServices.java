package com.esmc.gestionAvr.services;

import com.esmc.gestionAvr.entities.Lettre;
import com.esmc.gestionAvr.repositories.LettreRepository;
import com.esmc.gestionAvr.servicesInterfaces.LettreServicesInterfaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class LettreServices implements LettreServicesInterfaces {

    @Autowired
    private LettreRepository lettreRepository;

    @Override
    public Lettre addLettre(Lettre lettre) {
        return lettreRepository.save(lettre);
    }

    @Override
    public Lettre updateLettre(Long id, Lettre lettre) {
        lettre.setDateUpdate(new Date());
        return lettreRepository.save(lettre);
    }

    @Override
    public void deleteLettre(Long id) {
        lettreRepository.deleteById(id);

    }

    @Override
    public List<Lettre> listLettres() {
        return lettreRepository.findAll();

    }


}
