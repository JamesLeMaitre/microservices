package com.esmc.gestionAvr.services;

import com.esmc.gestionAvr.entities.TypeLettre;
import com.esmc.gestionAvr.repositories.TypeLettreRepository;
import com.esmc.gestionAvr.servicesInterfaces.TypeLettreInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class TypeLettreSevice implements TypeLettreInterface {


    @Autowired
    private TypeLettreRepository typeLettreRepository;

    /**
     * Implement addTypeLetter function
     * @param l
     * @return
     */
    @Override
    public TypeLettre addTypeLettre(TypeLettre l) {
        l.setDateCreate(new Date());
        l.setDateUpdate(new Date());
       return typeLettreRepository.save(l);
    }

    /**
     * Implemtnt updateTypeLetter function
     * @param id
     * @return
     */
    @Override
    public TypeLettre getTypeLetterById(Long id) {
        return typeLettreRepository.findById(id).orElse(null);
    }

    /**
     * Implement deleteTypeLetter function
     * @param id
     */
    @Override
    public void deleteTypeLettre(Long id) {
        typeLettreRepository.deleteById(id);
    }

    /**
     * Implement ListTypeLetter function
     * @return
     */
    @Override
    public List<TypeLettre> listTypeLettre() {
        return typeLettreRepository.findAll();
    }

    @Override
    public TypeLettre updateTypeLettre(Long id,TypeLettre t) {
        t.setDateUpdate(new Date());
        return typeLettreRepository.save(t);
    }
}
