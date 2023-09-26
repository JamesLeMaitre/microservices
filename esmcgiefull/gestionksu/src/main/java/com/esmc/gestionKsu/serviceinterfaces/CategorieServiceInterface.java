package com.esmc.gestionKsu.serviceinterfaces;

import com.esmc.gestionKsu.entities.Categorie;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;

/**
 * {@link Categorie} service interface
 * @author katoh <katohdavid@gmail.com>
 */
public interface CategorieServiceInterface {

    /**
     * Save categorie
     * @param categorie

     */
    public void save(Categorie categorie) ;

    /**
     * Update categorie
     * @param categorie

     */
    public void update(Categorie categorie) ;

    /**
     * Get categorie by id
     * @param id
     * @return
     */
    public Categorie getById(Long id);

    /**
     * Get categorie by libelle
     * @param libelle
     * @return

     */
    public Categorie getByLibelle(String libelle);

    /**
     * Get getAll categorie
     * @param page
     * @param pagedResourcesAssembler
     * @return
     */
    public PagedModel<EntityModel<Categorie>> getAll(Pageable page, PagedResourcesAssembler<Categorie> pagedResourcesAssembler);

}
