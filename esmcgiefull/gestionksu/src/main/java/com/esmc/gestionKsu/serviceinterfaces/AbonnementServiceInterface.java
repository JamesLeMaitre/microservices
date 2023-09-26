package com.esmc.gestionKsu.serviceinterfaces;

import com.esmc.gestionKsu.entities.Abonnement;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;

import java.util.List;

/**
 * @author katoh <katohdavid@gmail.com>
 */
public interface AbonnementServiceInterface {

    /**
     * Save abonnement
*/
    public Abonnement save(Abonnement abonnement) ;

    /**
     * Update abonnement
     * @param abonnement
     *
     */
    public void update(Abonnement abonnement);

    /**
     * Get abonnement by id
     * @param id
     * @return
     */
    public Abonnement getById(Long id);

    /**
     * Get abonnement by libelle
     * @param libelle
     * @return
     */
    public Abonnement getByLibelle(String libelle);

    /**
     * Get all abonnements
     * @param page
     * @param pagedResourcesAssembler
     * @return
     */
    public PagedModel<EntityModel<Abonnement>> getAll(Pageable page, PagedResourcesAssembler<Abonnement> pagedResourcesAssembler);

    public List<Abonnement> listAbonnementParTypeOffer(Long id);
}
