package com.esmc.gestionContrat.service;

import com.esmc.gestionContrat.entities.TypeContrat;
import com.esmc.gestionContrat.repositories.TypeContratRepository;
import com.esmc.gestionContrat.serviceInterfaces.TypeContratServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TypeContratService implements TypeContratServiceInterface {

    @Autowired
    private TypeContratRepository typeContratRepository;


    @Override
    @Transactional
    public TypeContrat addTypeContrat(TypeContrat t) {
        return typeContratRepository.save(t) ;
    }

    @Override
    @Transactional
    public TypeContrat updateTypeContrat(TypeContrat typeContrat,Long id) {
        TypeContrat  typeContrat1 = typeContratRepository.findById(id).orElse(null);
        assert typeContrat1 != null;
        typeContrat1.setId(typeContrat.getId());
        typeContrat1.setLibelle(typeContrat.getLibelle());
        typeContrat1.setDateCreate(typeContrat.getDateCreate());
        typeContrat1.setCode(typeContrat.getCode());
        typeContrat1.setDateUpdate(typeContrat.getDateUpdate());
        return typeContratRepository.save(typeContrat1);
    }

    @Override
    @Transactional
    public void deleteTypeContrat(Long id) { typeContratRepository.deleteById(id);

    }

    @Override
    @Transactional
    public List<TypeContrat> listTypeContrat() {
        return typeContratRepository.findAll();
    }

    @Override
    public TypeContrat getById(Long id) {
        return typeContratRepository.findById(id).orElse(null);
    }
}
