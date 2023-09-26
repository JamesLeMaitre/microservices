package com.esmc.gestionKsu.services;

import com.esmc.gestionKsu.entities.TypeOffre;
import com.esmc.gestionKsu.repositories.TypeOffreRepository;
import com.esmc.gestionKsu.serviceinterfaces.TypeOffreServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

/**
 * @author katoh <katohdavid@gmail.com>
 */
@Service
public class TypeOffreService implements TypeOffreServiceInterface {

    @Autowired
    private TypeOffreRepository typeOffreRepository;

    /**
     * Get offre type by id
     * @param id
     * @return
     */
    @Override
    public TypeOffre getById(Long id)  {
        //Check if exist

        //return offre type find
        Optional<TypeOffre> oftype=typeOffreRepository.findById(id);
        return oftype.get();
    }

    /**
     * Get offre type by id
     * @param libelle
     * @return
     */
    @Override
    public TypeOffre getByLibelle(String libelle) {
        //Check if exist

        //return offre type find
        Optional<TypeOffre> oftype=typeOffreRepository.findByLibelle(libelle);
        return oftype.get();
    }

    /**
     * Save offer type
     * @param typeOffre
     */
    @Override
    public void save(TypeOffre typeOffre) {
        //check if already exist

        //save
        typeOffre.setDateCreate(new Date());
        TypeOffre tyof=typeOffreRepository.save(typeOffre);


    }

    /**
     * update
     * @param typeOffre
     */
    @Override
    public void update(TypeOffre typeOffre) {
        //Check if this offer type is already in database by id

        TypeOffre tp=getById(typeOffre.getId());

        //Check if this offer type is already in database by libelle
        if (isPresent(typeOffre.getLibelle()))
            tp=getByLibelle(typeOffre.getLibelle());

        //setting data
        tp.setLibelle(typeOffre.getLibelle());
        tp.setDateUpdate(new Date());

        //update offer type
        typeOffre=typeOffreRepository.save(tp);

    }

    @Override
    public PagedModel<EntityModel<TypeOffre>> getAll(Pageable page, PagedResourcesAssembler<TypeOffre> pagedResourcesAssembler) {
        Page<TypeOffre> typeOffres = typeOffreRepository.findAll(page);
        return pagedResourcesAssembler.toModel(typeOffres);
    }

    /**
     * Check if offer type exist by libelle
     * @param libelle
     * @return
     */
    public boolean isPresent(String libelle){
        Optional<TypeOffre> oftype=typeOffreRepository.findByLibelle(libelle);
        if (oftype.isPresent())
            return true;
        return false;
    }

    /**
     * Check if offer type exist by libelle
     * @param id
     * @return
     */
    public boolean isPresent(Long id){
        Optional<TypeOffre> oftype=typeOffreRepository.findById(id);
        if (oftype.isPresent())
            return true;
        return false;
    }
}
