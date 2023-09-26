package com.esmc.gestionAvr.services;

import com.esmc.gestionAvr.entities.TypeAchatEchange;
import com.esmc.gestionAvr.repositories.TypeAchatEchangeRepository;
import com.esmc.gestionAvr.servicesInterfaces.TypeAchatEchangeServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TypeAchatEchangeService implements TypeAchatEchangeServiceInterface {

    @Autowired
    private TypeAchatEchangeRepository typeAchatEchangeRepository;


    /**
     * @param t
     * @return
     */
    @Override
    public TypeAchatEchange addTypeAchatEchange(TypeAchatEchange t) {
        return typeAchatEchangeRepository.save(t);
    }

    /**
     * @param t
     * @return
     */
    @Override
    public TypeAchatEchange updateTypeAchatEchange(TypeAchatEchange t) {

        TypeAchatEchange type = typeAchatEchangeRepository.findById(t.getId()).orElse(null);

        if (type != null) {
            typeAchatEchangeRepository.save(type);
        }
        return type;
    }

    /**
     * @param id
     */
    @Override
    public void deleteTypeAchatEchange(Long id) {
         typeAchatEchangeRepository.deleteById(id);
    }

    /**
     * @return
     */
    @Override
    public List<TypeAchatEchange> listTypeAchatEchange() {
        return typeAchatEchangeRepository.findAll();
    }

    /**
     * @param id
     * @return
     */
    @Override
    public List<TypeAchatEchange> listTypeAchatEchangeByEchange(Long id) {
        return typeAchatEchangeRepository.listTypeAchatEchangeByEchange(id);
    }
}
