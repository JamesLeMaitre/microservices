package com.esmc.gestionKsu.serviceinterfaces;

import com.esmc.gestionKsu.entities.Secteur;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;

import java.util.List;

/**
 * {@link Secteur} service interface
 * @author katoh <katohdavid@gmail.com>
 */
public interface SecteurServiceInterface {

    /**
     * Save offer
     * @param secteur
     */
    public Secteur save(Secteur secteur);

    /**
     * Update offer
     * @param secteur
     */
    public void update(Secteur secteur);

    /**
     * Get offer by id
     * @param id
     * @return
     */
    public Secteur getById(Long id);

    /**
     * Get offer by label
     * @param libelle
     * @return
     */
    public Secteur getByLibelle(String libelle);

    /**
     * Get all offers
     * @param page
     * @param pagedResourcesAssembler
     * @return
     */
    public PagedModel<EntityModel<Secteur>> getAll(Pageable page, PagedResourcesAssembler<Secteur> pagedResourcesAssembler);

    public List<Secteur> listSecteurParCategorie(Long id);
}
