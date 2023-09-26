package com.esmc.gestionKsu.services;

import com.esmc.gestionKsu.entities.TypeOperateur;
import com.esmc.gestionKsu.repositories.TypeOperateurRepository;
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
 * TypeOperateur entity service
 * @author katoh <katohdavid@gmail.com>
 */
@Service
public class TypeOperateurService {

    @Autowired
    private TypeOperateurRepository typeOperateurRepository;

    /**
     * get all operator types
     * @param page
     * @param pagedResourcesAssembler
     * @return
     */
    public PagedModel<EntityModel<TypeOperateur>> getAll(Pageable page, PagedResourcesAssembler<TypeOperateur> pagedResourcesAssembler) {
        Page<TypeOperateur> typeOperateurs = typeOperateurRepository.findAll(page);
        return pagedResourcesAssembler.toModel(typeOperateurs);
    }

    /**
     * Save operator type
     * @param typeOperateur
     */
    public void save(TypeOperateur typeOperateur) {

        //setting data
        typeOperateur.setDateCreate(new Date());

        //save operator type
        TypeOperateur t=typeOperateurRepository.save(typeOperateur);

    }

    /**
     * Update operator type
     * @param typeOperateur
     */
    public void update(TypeOperateur typeOperateur) {
        //Check if this operator type is already in database by id

        TypeOperateur tp=getById(typeOperateur.getId());

        //Check if this operator type is already in database by libelle
        if (isPresent(typeOperateur.getLibelle()))
            tp=getById(typeOperateur.getId());

        //setting data
        tp.setLibelle(typeOperateur.getLibelle());
        tp.setDateUpdate(new Date());

        //update operator type
        typeOperateur=typeOperateurRepository.save(tp);


    }

    /**
     * Get operator type by id
     * @param id
     * @return
     */
    public TypeOperateur getById(Long id) {
        //Check if exist

        //return operator type find
        Optional<TypeOperateur> optype=typeOperateurRepository.findById(id);
        return optype.get();
    }

    /**
     * Get operator type by libelle
     * @param libelle
     * @return
     */
    public TypeOperateur getByLibelle(String libelle) {
        //Check if exist

        //return operator type find
        Optional<TypeOperateur> optype=typeOperateurRepository.findByLibelle(libelle);
        return optype.get();
    }

    /**
     * Check if operator type exist by id
     * @param id
     * @return
     */
    public boolean isPresent(Long id){
        Optional<TypeOperateur> optype=typeOperateurRepository.findById(id);
        if (optype.isPresent())
            return true;
        return false;
    }

    /**
     * Check if operator type exist by libelle
     * @param libelle
     * @return
     */
    public boolean isPresent(String libelle){
        Optional<TypeOperateur> optype=typeOperateurRepository.findByLibelle(libelle);
        if (optype.isPresent())
            return true;
        return false;
    }

}
