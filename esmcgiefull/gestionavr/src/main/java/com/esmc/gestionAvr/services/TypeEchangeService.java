package com.esmc.gestionAvr.services;

import com.esmc.gestionAvr.entities.TypeEchange;
import com.esmc.gestionAvr.repositories.TypeEchangeRepository;
import com.esmc.gestionAvr.servicesInterfaces.TypeEchangeServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class TypeEchangeService implements TypeEchangeServiceInterface {
    @Autowired
    private TypeEchangeRepository typeEchangeRepository;

    @Override
    public TypeEchange addTypeAchat(TypeEchange typeEchange) {
        return typeEchangeRepository.save(typeEchange);
    }

    @Override
    public TypeEchange updateTypeAchat(Long id, TypeEchange typeEchange) {
        typeEchange.setDateUpdate(new Date());
        return typeEchangeRepository.save(typeEchange);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public TypeEchange getTypeEchange(Long id) {
        return typeEchangeRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteTypeAchat(Long id) {
        typeEchangeRepository.deleteById(id);

    }

    @Override
    public List<TypeEchange> getAllTypeAchat() {
        return typeEchangeRepository.findAll();
    }

    
}
