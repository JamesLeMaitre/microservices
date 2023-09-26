package com.esmc.gestionKsu.serviceinterfaces;

import com.esmc.gestionKsu.entities.TypePM;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;

/**
 * {@link TypePM} service interface
 * @author katoh <katohdavid@gmail.com>
 */
public interface TypePMServiceInterface {

    /**
     * Save typePM
     * @param typePM
     */
    public void save(TypePM typePM);

    /**
     * Update typePM
     * @param typePM
     */
    public void update(TypePM typePM);

    /**
     * Get typePM by id
     * @param id
     * @return
     */
    public TypePM getById(Long id);

    /**
     * Get typePM by libelle
     * @param libelle
     * @return
     */
    public TypePM getByLibelle(String libelle);

    /**
     * Get all typePM
     * @param page
     * @param pagedResourcesAssembler
     * @return
     */
    public PagedModel<EntityModel<TypePM>> getAll(Pageable page, PagedResourcesAssembler<TypePM> pagedResourcesAssembler);

}
