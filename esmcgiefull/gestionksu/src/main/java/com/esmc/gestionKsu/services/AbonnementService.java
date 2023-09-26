package com.esmc.gestionKsu.services;

import com.esmc.gestionKsu.entities.Abonnement;
import com.esmc.gestionKsu.repositories.AbonnementRepository;
import com.esmc.gestionKsu.serviceinterfaces.AbonnementServiceInterface;
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
 * @author katoh <katohdavid@gmail.com>
 */
@Service
public class AbonnementService implements AbonnementServiceInterface {

    @Autowired
    private AbonnementRepository abonnementRepository;


    /**
     * Save abonnement
     *
     * @param abonnement
     */
    @Override
    public Abonnement save(Abonnement abonnement) {
        return abonnementRepository.save(abonnement);
    }

    @Override
    public void update(Abonnement abonnement)  {

        Abonnement ab=getById(abonnement.getId());

        //Check if this abonnement is already in database by libelle
        if (isPresent(abonnement.getLibelle()))
            ab=getById(abonnement.getId());
        //setting data
        ab.setLibelle(abonnement.getLibelle());
        ab.setDateUpdate(new Date());
        //update abonnement
        abonnementRepository.save(ab);

    }

    @Override
    public Abonnement getById(Long id)  {
        //Check if exist
        Optional<Abonnement> opab=abonnementRepository.findById(id);
        return opab.get();
    }

    @Override
    public Abonnement getByLibelle(String libelle)  {
        //Check if exist
        //return abonnement find
        Optional<Abonnement> opab=abonnementRepository.findByLibelle(libelle);
        return opab.get();
    }

    @Override
    public PagedModel<EntityModel<Abonnement>> getAll(Pageable page, PagedResourcesAssembler<Abonnement> pagedResourcesAssembler) {
        Page<Abonnement> abonnements = abonnementRepository.findAll(page);
        return pagedResourcesAssembler.toModel(abonnements);
    }

    @Override
    public List<Abonnement> listAbonnementParTypeOffer(Long id) {
        return abonnementRepository.listAbonnementByTypeOffre(id);
    }

    /**
     * Check if abonnement type exist by libelle
     * @param libelle
     * @return
     */
    public boolean isPresent(String libelle){
        Optional<Abonnement> opab=abonnementRepository.findByLibelle(libelle);
        if (opab.isPresent())
            return true;
        return false;
    }

    /**
     * Check if abonnement type exist by id
     * @param id
     * @return
     */
    public boolean isPresent(Long id){
        Optional<Abonnement> opab=abonnementRepository.findById(id);
        if (opab.isPresent())
            return true;
        return false;
    }

}
