package com.esmc.gestionCertification.serviceImp;

import com.esmc.gestionCertification.entities.TypeChargement;
import com.esmc.gestionCertification.repository.TypeChargementRepository;
import com.esmc.gestionCertification.services.TypeChargementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class TypeChargementServiceImpl implements TypeChargementService {
    @Autowired
    private TypeChargementRepository typeChargementRepository;
    @Override
    public List<TypeChargement> getTypeChargement() {
        return typeChargementRepository.findAll();
    }

    @Override
    public TypeChargement ajouterTypeChargement(TypeChargement typeChargement) {
        return typeChargementRepository.save(typeChargement);
    }

    @Override
    public TypeChargement getTypeChargementbyId(Long id) {
            return typeChargementRepository.findById(id).get();
    }

    @Override
    public void deleteTypeChargement(Long id) {
        typeChargementRepository.deleteById(id);

    }
}
