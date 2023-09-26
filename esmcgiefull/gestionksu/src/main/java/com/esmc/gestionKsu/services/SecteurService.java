package com.esmc.gestionKsu.services;

import com.esmc.gestionKsu.entities.Categorie;
import com.esmc.gestionKsu.entities.Secteur;
import com.esmc.gestionKsu.repositories.SecteurRepository;
import com.esmc.gestionKsu.serviceinterfaces.SecteurServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * {@link Secteur} service
 * @author katoh <katohdavid@gmail.com>
 */
@Service
public class SecteurService implements SecteurServiceInterface {

    @Autowired
    private SecteurRepository secteurRepository;

    @Autowired
    private CategorieService categorieService;

    @Override
    public Secteur save(Secteur secteur)  {
        return secteurRepository.save(secteur);
    }

    @Override
    public void update(Secteur secteur) {
        //check if secteur already exist

        Secteur sec=getById(secteur.getId());

        //Check if secteur is already in database by libelle
        if (isPresent(secteur.getLibelle()))
            sec=getByLibelle(secteur.getLibelle());

        //setting data
        sec=refactorOffreObject(sec);
        sec.setLibelle(secteur.getLibelle());
        sec.setDateUpdate(new Date());

        //update offer type
        secteur=secteurRepository.save(sec);

    }

    @Override
    public Secteur getById(Long id)  {
        //Check if exist


        //return secteur find
        Optional<Secteur> of=secteurRepository.findById(id);
        return of.get();
    }

    @Override
    public Secteur getByLibelle(String libelle) {
        //Check if exist


        //return secteur find
        Optional<Secteur> of=secteurRepository.findByLibelle(libelle);
        return of.get();
    }

    @Override
    public PagedModel<EntityModel<Secteur>> getAll(Pageable page, PagedResourcesAssembler<Secteur> pagedResourcesAssembler) {
        Page<Secteur> secteurs = secteurRepository.findAll(page);
        return pagedResourcesAssembler.toModel(secteurs);
    }

    @Override
    public List<Secteur> listSecteurParCategorie(Long id) {
        return secteurRepository.findSecteurByCategorie(id);
    }

    //Check if offer already exist by id
    public boolean isPresent(Long id){
        Optional<Secteur> optionalSecteur=secteurRepository.findById(id);
        if (optionalSecteur.isPresent())
            return true;
        return false;
    }

    //Check if offer already exist by id
    public boolean isPresent(String libelle){
        Optional<Secteur> optionalSecteur=secteurRepository.findByLibelle(libelle);
        if (optionalSecteur.isPresent())
            return true;
        return false;
    }

    /**
     * Refactor secteur object
     * @param secteur
     * @return
     */
    public Secteur refactorOffreObject(Secteur secteur) {

        //setting data
        Categorie categorie=categorieService.getById(secteur.getCategorie().getId());
        secteur.setCategorie(categorie);

        //return offer
        return secteur;

    }

}
