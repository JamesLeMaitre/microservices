package com.esmc.gestionPayement.Services;

import com.esmc.gestionPayement.Repositories.TypePayementRepository;
import com.esmc.gestionPayement.ServicesInterface.TypePayementServiceInterface;
import com.esmc.gestionPayement.entities.TypePayement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TypePayementServiceImpl implements TypePayementServiceInterface {

    @Autowired
    private TypePayementRepository typePayementRepository;

    @Override
    public List<TypePayement> getTypePayement() {
        return typePayementRepository.findAll();
    }

    @Override
    @Transactional
    public TypePayement TypePayement(Long id) {
        return typePayementRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public TypePayement saveTypePayement(TypePayement typePayement) {
        return typePayementRepository.save(typePayement);
    }

    @Override
    @Transactional
    public void deleteTypePayement(Long id) {
        typePayementRepository.deleteById(id);
    }

    @Override
    @Transactional
    public TypePayement updateTypePayement(Long id, TypePayement typePayement) {

        TypePayement t = typePayementRepository.findById(id).orElse(null);
        t.setLibelle(typePayement.getLibelle());
        return typePayementRepository.save(t);
    }
}
