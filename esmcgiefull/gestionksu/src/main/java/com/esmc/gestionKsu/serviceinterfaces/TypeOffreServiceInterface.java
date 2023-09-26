package com.esmc.gestionKsu.serviceinterfaces;

import com.esmc.gestionKsu.entities.TypeOffre;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;

/**
 * @author katoh <katohdavid@gmail.com>
 */
public interface TypeOffreServiceInterface {

    public TypeOffre getById(Long id);

    public TypeOffre getByLibelle(String libelle);

    public void save(TypeOffre typeOffre);

    public void update(TypeOffre typeOffre);

    public PagedModel<EntityModel<TypeOffre>> getAll(Pageable page, PagedResourcesAssembler<TypeOffre> pagedResourcesAssembler);
}
