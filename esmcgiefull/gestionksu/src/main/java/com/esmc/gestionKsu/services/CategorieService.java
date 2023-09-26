package com.esmc.gestionKsu.services;

import com.esmc.gestionKsu.entities.Categorie;
import com.esmc.gestionKsu.repositories.CategorieRepository;
import com.esmc.gestionKsu.serviceinterfaces.CategorieServiceInterface;

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
 * {@link Categorie}
 * @author katoh <katohdavid@gmail.com>
 */
@Service
public class CategorieService implements CategorieServiceInterface {

    @Autowired
    private CategorieRepository categorieRepository;

    @Override
    public void save(Categorie categorie) {
        //check if exist

        categorie.setDateCreate(new Date());

        //save
        Categorie ab=categorieRepository.save(categorie);

    }

    @Override
    public void update(Categorie categorie) {
        //Check if this categorie is already in database by id

        Categorie categ=getById(categorie.getId());

        //Check if this categorie is already in database by libelle
        if (isPresent(categorie.getLibelle()))
            categ=getById(categorie.getId());

        //setting data
        categ.setLibelle(categorie.getLibelle());
        categ.setDateUpdate(new Date());

        //update categorie
        categorie=categorieRepository.save(categ);

    }

    @Override
    public Categorie getById(Long id) {
        //Check if exist

        //return categorie find
        Optional<Categorie> opcateg=categorieRepository.findById(id);
        return opcateg.get();
    }

    @Override
    public Categorie getByLibelle(String libelle) {
        //Check if exist

        //return categorie find
        Optional<Categorie> opcateg=categorieRepository.findByLibelle(libelle);
        return opcateg.get();
    }

    @Override
    public PagedModel<EntityModel<Categorie>> getAll(Pageable page, PagedResourcesAssembler<Categorie> pagedResourcesAssembler) {
        Page<Categorie> categories = categorieRepository.findAll(page);
        return pagedResourcesAssembler.toModel(categories);
    }

    /**
     * Check if categorie exist by id
     * @param id
     * @return
     */
    public boolean isPresent(Long id){
        Optional<Categorie> oppcateg=categorieRepository.findById(id);
        if (oppcateg.isPresent())
            return true;
        return false;
    }

    /**
     * Check if categorie exist by id
     * @param libelle
     * @return
     */
    public boolean isPresent(String libelle){
        Optional<Categorie> oppcateg=categorieRepository.findByLibelle(libelle);
        if (oppcateg.isPresent())
            return true;
        return false;
    }
}
