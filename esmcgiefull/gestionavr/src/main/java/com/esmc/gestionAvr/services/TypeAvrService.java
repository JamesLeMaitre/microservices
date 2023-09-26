package com.esmc.gestionAvr.services;

import com.esmc.gestionAvr.entities.TypeAvr;
import com.esmc.gestionAvr.exceptions.TypeAvrException;
import com.esmc.gestionAvr.repositories.TypeAvrRepository;
import com.esmc.gestionAvr.servicesInterfaces.TypeAvrInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TypeAvrService implements TypeAvrInterface {


    @Autowired
    private TypeAvrRepository typeAvrRepository;

    /**
     * Implement add TypeAvr function
     *
     * @param a
     * @return
     */
    @Override
    public TypeAvr addTypeAvr( TypeAvr a) {
        a.setDateCreate(new Date());
        a.setDateUpdate(new Date());
        return typeAvrRepository.save(a);
    }

    /**
     * Implement update TypeAvr function
     * @param id
     * @return
     */
    @Override
    public TypeAvr getTypeAvrById(Long id) {
        return typeAvrRepository.findById(id).orElse(null);
    }

    @Override
    public TypeAvr updateTypeAvr(Long id,TypeAvr t) {
        t.setDateUpdate(new Date());
        return typeAvrRepository.save(t);
    }

    /**
     * Implement delete TypeAvr function
     * @param id
     */
    @Override
    public void deleteTypeAvr(Long id) {
        typeAvrRepository.deleteById(id);
    }

    /**
     * Implement list TypeAvr function
     * @return
     */
    @Override
    public List<TypeAvr> listTypeAvr() {
        return typeAvrRepository.findAll();
    }

    @Override
    public List<TypeAvr> listTypeAvrOfFifo() {
        return typeAvrRepository.listTypeAvrOfFifo();
    }

    @Override
    public TypeAvr getById(Long id) throws TypeAvrException {
        return typeAvrRepository.findById(id).orElseThrow(() -> new TypeAvrException("TypeAvr with " + id + " not found"));
    }



}
