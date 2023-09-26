package com.esmc.demandeAchatBanKsu.services;


import com.esmc.demandeAchatBanKsu.entities.TypeMABanKSU;
import com.esmc.demandeAchatBanKsu.repositories.TypeMABanKSURepository;
import com.esmc.demandeAchatBanKsu.servicesInterface.TypeMABanKSUService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;
import java.util.List;

@Transactional
@Service
public class TypeMABanKSUServiceServiceImpl implements TypeMABanKSUService {

    @Autowired
    private TypeMABanKSURepository typeMABanKSURepository;

    @Override
    public List<TypeMABanKSU> getTypeMABanKSU() {
        return typeMABanKSURepository.findAll();
    }

    @Override
    public TypeMABanKSU findTypeMABanKSUById(Long id) {
        return typeMABanKSURepository.findById(id).orElse(null);
    }

    @Override
    public TypeMABanKSU savetypeMABanKSU(TypeMABanKSU typeMABanKSU) {
        typeMABanKSU.setDateCreate(new Date());
        typeMABanKSU.setDateUpdate(new Date());
        return typeMABanKSURepository.save(typeMABanKSU);
    }

    @Override
    public void deletetypeMABanKSU(@PathVariable Long id) {
        typeMABanKSURepository.deleteById(id);
    }

    @Override
    public TypeMABanKSU updatetypeMABanKSU(TypeMABanKSU typeMABanKSU) {
        return typeMABanKSURepository.save(typeMABanKSU);
    }
}
