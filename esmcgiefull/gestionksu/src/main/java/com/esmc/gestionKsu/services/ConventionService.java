package com.esmc.gestionKsu.services;

import com.esmc.gestionKsu.entities.Convention;
import com.esmc.gestionKsu.entities.Ksu;
import com.esmc.gestionKsu.repositories.ConventionRepository;
import com.esmc.gestionKsu.repositories.KsuRepository;
import com.esmc.gestionKsu.serviceinterfaces.ConventionServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ConventionService implements ConventionServiceInterface {

    @Autowired
    private ConventionRepository conventionRepository;

    @Autowired
    private KsuRepository ksuRepository;

    @Override
    public void saveConvention(Long idKsu) {

        Convention c = conventionRepository.getConventionByKsu(idKsu);

        if (c == null) {
            Ksu k = ksuRepository.findById(idKsu).orElse(null);
            Convention convention = new Convention();
            convention.setKsu(k);
            conventionRepository.save(convention);
        } else {
            conventionRepository.getConventionByKsu(idKsu);
        }

    }

    @Override
    public void updateConvention(Long id) {

    }

    @Override
    public Convention getConventionById(Long id) {
        return conventionRepository.getConventionByKsu(id);
    }


    @Override
    public List<Convention> getAllConvention() {
        return conventionRepository.findAll();
    }

    @Override
    public List<Convention> listConventionParKsu(Long id) {
        return conventionRepository.listConventionByKsu(id);
    }
}
