package com.esmc.gestionte.services;


import com.esmc.gestionte.entities.SupportMarchandEnchage;
import com.esmc.gestionte.entities.TypeOrdre;
import com.esmc.gestionte.exceptions.Exceptions;
import com.esmc.gestionte.repositories.TypeOrdreRepository;
import com.esmc.gestionte.serviceinterface.TypeOrdreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class TypeOrdreImpl implements TypeOrdreService {

    @Autowired
    private TypeOrdreRepository typeOrdreRepository;

    @Override
    public List<TypeOrdre> getTypeOrdre() {
        return  typeOrdreRepository.findAll();
    }

/*
    @Override
    public TypeOrdre addNew(TypeOrdre typeOrdre) {

        TypeOrdre or = typeOrdreRepository.save(typeOrdre);
        or.setDateUpdate(new Date());
        or.setDateCreate(new Date());

        return typeOrdreRepository.save(or);
    }
*/

    @Override
    public void addNewTypeOrdre(TypeOrdre typeOrdre) throws Exceptions {


       if(isPresent(typeOrdre.getLibelle())){
            throw new Exceptions(Exceptions.codeSMNotFound(typeOrdre.getLibelle()));
        }
        typeOrdre.setDateUpdate(new Date());
        typeOrdre.setDateCreate(new Date());
        TypeOrdre typeOrdre1=typeOrdreRepository.save(typeOrdre);
        if (typeOrdre1==null){
            throw new Exceptions(Exceptions.alertGeneralException("erreur d'ajout"));
        }
    }

    @Override
    public void deleteTypeOrdre(Long id) throws Exceptions {
        boolean exists = typeOrdreRepository.existsById(id);
        if(!exists)
            throw  new Exceptions(Exceptions.alertGeneralException("agr dont l'id "+id+"n'existe pas "));

        typeOrdreRepository.deleteById(id);

    }

    @Override
    public void updateTypeOrdre(Long id, TypeOrdre typeOrdre) throws Exceptions {

       if(!isPresent(typeOrdre.getId()))
            throw new Exceptions(Exceptions.alertGeneralException("l'identifiant n'existe pas"));
        TypeOrdre typeOrdre1=getById(typeOrdre.getId());

        typeOrdre1.setLibelle(typeOrdre.getLibelle());
        typeOrdre1.setDateUpdate(new Date());

        //update supportMarchandEnchage
        typeOrdre=typeOrdreRepository.save(typeOrdre1);

        if (typeOrdre==null){
            throw new Exceptions(Exceptions.alertGeneralException("modification reussi"));
        }
    }

    @Override
    public TypeOrdre getById(Long id) throws Exceptions {
        if(!isPresent(id))
            throw new Exceptions(Exceptions.alertGeneralException("l'identifiant n'esiste pas"));
        Optional<TypeOrdre> opad=typeOrdreRepository.findById(id);
        return  opad.get();
    }
    public boolean isPresent(String libelle){
        Optional<SupportMarchandEnchage> opab=typeOrdreRepository.findTypeOrdreByLibelle(libelle);
        if (opab.isPresent())
            return true;
        return false;
    }
    public boolean isPresent(Long id){
        Optional<TypeOrdre> opab=typeOrdreRepository.findById(id);
        if (opab.isPresent())
            return true;
        return false;
    }
}
