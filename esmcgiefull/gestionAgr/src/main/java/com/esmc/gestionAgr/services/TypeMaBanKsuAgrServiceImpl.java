package com.esmc.gestionAgr.services;

import com.esmc.gestionAgr.entities.TypeMaBanKsuAgr;
import com.esmc.gestionAgr.feign.TypeMaBanKsuRestClient;
import com.esmc.gestionAgr.repositories.TypeMaBanKsuAgrRepository;
import com.esmc.gestionAgr.serviceinterfaces.TypeMaBanKsuAgrServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TypeMaBanKsuAgrServiceImpl implements TypeMaBanKsuAgrServiceInterface {

    @Autowired
    private TypeMaBanKsuAgrRepository TypeMaBanKsuAgrRepository;

    @Autowired
    private TypeMaBanKsuRestClient typeMaBanKsuRestClient;

    @Override
    @Transactional
    public TypeMaBanKsuAgr addTypeMaBanKsuAgr(TypeMaBanKsuAgr t) {
        return TypeMaBanKsuAgrRepository.save(t);
    }

    @Override
    public TypeMaBanKsuAgr updateTypeMaBanKsuAgr(Long id) {
        return TypeMaBanKsuAgrRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteTypeMaBanKsuAgr(Long id) {
        TypeMaBanKsuAgrRepository.deleteById(id);
    }

    @Override
    public List<TypeMaBanKsuAgr> listTypeMaBanKsuAgr() {
        return TypeMaBanKsuAgrRepository.findAll();
    }

    /**
     * @param id
     * @return
     */
    @Override
    public List<TypeMaBanKsuAgr> listTypeMaBanKsuAgrByTypeMaBanKsu(Long id) {
        return TypeMaBanKsuAgrRepository.listTypeMaBanKsuAgrByTypeMaBanKsu(typeMaBanKsuRestClient.getById(id).getId());
    }
}
