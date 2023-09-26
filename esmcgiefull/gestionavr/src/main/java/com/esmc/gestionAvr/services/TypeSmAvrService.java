package com.esmc.gestionAvr.services;

import com.esmc.gestionAvr.entities.TypeSmAvr;
import com.esmc.gestionAvr.repositories.TypeSmAvrRepository;
import com.esmc.gestionAvr.servicesInterfaces.TypeSmAvrInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeSmAvrService implements TypeSmAvrInterface {

    @Autowired
    private TypeSmAvrRepository typeSmAvrRepository;
    /**
     * @param t
     * @return
     */
    @Override
    public TypeSmAvr saveTypeSmavr(TypeSmAvr t) {
        return typeSmAvrRepository.save(t);
    }

    /**
     * @param t
     * @return
     */
    @Override
    public TypeSmAvr updateTypeSmAvr(TypeSmAvr t) {
        return typeSmAvrRepository.save(t);
    }

    /**
     * @param id
     */
    @Override
    public void deleteTypeSmAvr(Long id) {
        typeSmAvrRepository.deleteById(id);
    }

    /**
     * @return
     */
    @Override
    public List<TypeSmAvr> listTypeSmAvr() {
        return typeSmAvrRepository.findAll();
    }

    @Override
    public TypeSmAvr DetailTypeBon(String code) {
        return typeSmAvrRepository.DetailTypeBon(code);
    }

    /**
     * @param id
     * @return
     */
    /*@Override
    public List<TypeSmAvr> listTypeSmAvrBySMAvr(Long id) {
        return typeSmAvrRepository.listTypeSmAvrBySMAvr(id);
    }*/
}
