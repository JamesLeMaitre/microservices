package com.esmc.gestionKsu.serviceinterfaces;

import com.esmc.gestionKsu.entities.PieceIdentite;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;

/**
 * @author katoh <katohdavid@gmail.com>
 */
public interface PieceIdentiteServiceInterface {

    /**
     * Save piece identite
     * @param pieceIdentite

     */
    public void save(PieceIdentite pieceIdentite);

    /**
     * Update piece identite
     * @param pieceIdentite

     */
    public void update(PieceIdentite pieceIdentite);

    /**
     * Get abonnement by id
     * @param id
     * @return

     */
    public PieceIdentite getById(Long id);

    /**
     * Get abonnement by libelle
     * @param libelle
     * @return
     */
    public PieceIdentite getByLibelle(String libelle);

    /**
     * Get all piece identite
     * @param page
     * @param pagedResourcesAssembler
     * @return
     */
    public PagedModel<EntityModel<PieceIdentite>> getAll(Pageable page, PagedResourcesAssembler<PieceIdentite> pagedResourcesAssembler);

}
