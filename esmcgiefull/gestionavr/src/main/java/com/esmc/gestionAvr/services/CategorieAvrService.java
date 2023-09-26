package com.esmc.gestionAvr.services;

import com.esmc.gestionAvr.entities.CategorieAvr;
import com.esmc.gestionAvr.exceptions.CategorieAvrException;
import com.esmc.gestionAvr.repositories.CategorieAvrRepository;
import com.esmc.gestionAvr.servicesInterfaces.CategorieAvrInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Implemen
 */
@Service
@Transactional
public class CategorieAvrService implements CategorieAvrInterface {

    @Autowired
    private CategorieAvrRepository categorieAvrRepository;


    @Override
    public CategorieAvr addCategorieAvr(CategorieAvr c) {
        c.setDateCreate(new Date());
        c.setDateUpdate(new Date());
        return categorieAvrRepository.save(c);
    }

    @Override
    public CategorieAvr getCategorieAvrById( Long id) {
        return categorieAvrRepository.findById(id).orElse(null);
    }

    @Override
    public CategorieAvr updateCategorieAvr(Long id,CategorieAvr c){
        c.setDateUpdate(new Date());
        return categorieAvrRepository.save(c);
    }

    @Override
    public void deleteCategorieAvr( Long id) {
        categorieAvrRepository.deleteById(id);
    }

    @Override
    public List<CategorieAvr> listCategorieAvr() {
        return categorieAvrRepository.findAll();
    }

    @Override
    public CategorieAvr getById(Long id) throws CategorieAvrException {
        return categorieAvrRepository.findById(id).orElseThrow(() -> new CategorieAvrException("Categorie AVR with " + id + "not found"));
    }


}
