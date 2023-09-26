package com.esmc.gestionKsu.serviceinterfaces;

import com.esmc.gestionKsu.entities.Offre;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;

import java.util.List;

/**
 * {@link Offre} service interface
 * @author katoh <katohdavid@gmail.com>
 */
public interface OffreServiceInterface {

    /**
     * Save offer
     * @param offre
     */
    public void save(Offre offre);

    /**
     * Update offer
     * @param offre
     */
    public void update(Offre offre);

    /**
     * Get offer by id
     * @param id
     * @return
     */
    public Offre getById(Long id);

    /**
     * Get offer by label
     * @param libelle
     * @return
     */
    public Offre getByLibelle(String libelle);

    /**
     * Get all offers
     * @param page
     * @param pagedResourcesAssembler
     * @return
     */
    public PagedModel<EntityModel<Offre>> getAll(Pageable page, PagedResourcesAssembler<Offre> pagedResourcesAssembler);

    public List<Offre> listOffreParTypeOffre(Long id, Long id2);
}
