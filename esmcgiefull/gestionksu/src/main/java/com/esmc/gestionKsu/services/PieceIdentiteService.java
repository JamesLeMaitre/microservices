package com.esmc.gestionKsu.services;


import com.esmc.gestionKsu.entities.PieceIdentite;
import com.esmc.gestionKsu.repositories.PieceIdentiteRepository;
import com.esmc.gestionKsu.serviceinterfaces.PieceIdentiteServiceInterface;
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
 * PieceIdentiteService entity service
 * @author katoh <katohdavid@gmail.com>
 */
@Service
public class PieceIdentiteService implements PieceIdentiteServiceInterface {

    @Autowired
    private PieceIdentiteRepository pieceIdentiteRepository;

    @Override
    public void save(PieceIdentite pieceIdentite) {
        //Check if this PieceIdentite type is already in database

        //setting data
        pieceIdentite.setDateCreate(new Date());

        //save PieceIdentite type
        PieceIdentite p=pieceIdentiteRepository.save(pieceIdentite);

    }

    @Override
    public void update(PieceIdentite pieceIdentite) {
        //Check if this pieceIdentite type is already in database by id

        PieceIdentite pi=getById(pieceIdentite.getId());

        //Check if this pieceIdentite type is already in database by libelle
        if (isPresent(pieceIdentite.getLibelle()))
            pi=getByLibelle(pieceIdentite.getLibelle());

        //setting data
        pi.setLibelle( pieceIdentite.getLibelle());
        pi.setDateUpdate(new Date());

        //update pieceIdentite type
        pieceIdentite=pieceIdentiteRepository.save(pi);

    }

    @Override
    public PieceIdentite getById(Long id) {
        //Check if exist

        //return pieceIdentite find
        Optional<PieceIdentite> oppi=pieceIdentiteRepository.findById(id);
        return  oppi.get();
    }

    @Override
    public PieceIdentite getByLibelle(String libelle) {
        //Check if exist

        //return pieceIdentite find
        Optional<PieceIdentite> oppi=pieceIdentiteRepository.findByLibelle(libelle);
        return  oppi.get();
    }

    @Override
    public PagedModel<EntityModel<PieceIdentite>> getAll(Pageable page, PagedResourcesAssembler<PieceIdentite> pagedResourcesAssembler) {
        Page<PieceIdentite> pieceIdentites = pieceIdentiteRepository.findAll(page);
        return pagedResourcesAssembler.toModel(pieceIdentites);
    }

    /**
     * Check if piece identite exist by libelle
     * @param libelle
     * @return
     */
    public boolean isPresent(String libelle){
        Optional<PieceIdentite> oppiece=pieceIdentiteRepository.findByLibelle(libelle);
        if (oppiece.isPresent())
            return true;
        return false;
    }

    /**
     * Check if piece identite exist by id
     * @param id
     * @return
     */
    public boolean isPresent(Long id){
        Optional<PieceIdentite> oppiece=pieceIdentiteRepository.findById(id);
        if (oppiece.isPresent())
            return true;
        return false;
    }
}
