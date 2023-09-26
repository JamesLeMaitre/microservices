package com.esmc.gestionformation.services;

import com.esmc.gestionformation.entities.SalleFormation;
import com.esmc.gestionformation.enums.SalleEnums;
import com.esmc.gestionformation.repositories.SalleFormationRepository;
import com.esmc.gestionformation.serviceinterfaces.SalleFormationServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class SalleFormationService implements SalleFormationServiceInterface {
    @Autowired
    private SalleFormationRepository salleFormationRepository;
    @Override
    public SalleFormation create(SalleFormation data) {
        return salleFormationRepository.save(data);
    }

    @Override
    public SalleFormation update(Long id, SalleFormation s) {
        return null;
    }

    @Override
    public void delete(Long id) {
        salleFormationRepository.deleteById(id);
    }

    @Override
    public List<SalleFormation> getAll() {
        return salleFormationRepository.findAll();
    }

    @Override
    public SalleFormation getById(Long id) {
        return null;
    }

    @Override
    public List<SalleFormation> getByIdSalle(Long id) {
            return salleFormationRepository.findByTypesalle(id);

    }

    @Override
    public List<SalleFormation> getByIdSalle() {
        return salleFormationRepository.findAll();
    }
}
