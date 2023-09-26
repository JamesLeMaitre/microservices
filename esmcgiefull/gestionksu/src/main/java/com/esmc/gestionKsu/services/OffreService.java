package com.esmc.gestionKsu.services;

import com.esmc.gestionKsu.entities.Offre;
import com.esmc.gestionKsu.entities.TypeOffre;
import com.esmc.gestionKsu.entities.TypePM;
import com.esmc.gestionKsu.repositories.OffreRepository;
import com.esmc.gestionKsu.serviceinterfaces.OffreServiceInterface;
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
 * {@link Offre} service
 * @author katoh <katohdavid@gmail.com>
 */
@Service
public class OffreService implements OffreServiceInterface {

    @Autowired
    private OffreRepository offreRepository;

    @Autowired
    private TypeOffreService typeOffreService;

    @Autowired
    private TypePMService typePMService;

    @Override
    public void save(Offre offre){

        //check if this offer already exist

        //setting data
        offre=refactorOffreObject(offre);
        offre.setDateCreate(new Date());

        Offre of=offreRepository.save(offre);

    }

    @Override
    public void update(Offre offre) {

        //check if this offer already exist

        Offre off=getById(offre.getId());

        //Check if this offer is already in database by libelle
        if (isPresent(offre.getLibelle()))
            off=getByLibelle(offre.getLibelle());

        //setting data
        off=refactorOffreObject(off);
        off.setLibelle(offre.getLibelle());
        off.setDateUpdate(new Date());

        //update offer type
        offre=offreRepository.save(off);

    }

    @Override
    public Offre getById(Long id)  {
        //Check if exist

        //return offer find
        Optional<Offre> of=offreRepository.findById(id);
        return of.get();
    }

    @Override
    public Offre getByLibelle(String libelle)  {
        //Check if exist

        //return offer find
        Optional<Offre> of=offreRepository.findByLibelle(libelle);
        return of.get();
    }

    @Override
    public PagedModel<EntityModel<Offre>> getAll(Pageable page, PagedResourcesAssembler<Offre> pagedResourcesAssembler) {
        Page<Offre> offres = offreRepository.findAll(page);
        return pagedResourcesAssembler.toModel(offres);
    }

    @Override
    public List<Offre> listOffreParTypeOffre(Long id, Long id2) {
        return offreRepository.findOffresByTypePMAndTypeOffre(id, id2);
    }

    //Check if offer already exist by id
    public boolean isPresent(Long id){
        Optional<Offre> optionalOffre=offreRepository.findById(id);
        if (optionalOffre.isPresent())
            return true;
        return false;
    }

    //Check if offer already exist by libelle
    public boolean isPresent(String libelle){
        Optional<Offre> optionalOffre=offreRepository.findByLibelle(libelle);
        if (optionalOffre.isPresent())
            return true;
        return false;
    }

    /**
     * Refactor offer object
     * @param offre
     * @return

     */
    public Offre refactorOffreObject(Offre offre) {

        //check if this offer already exist

        //check if this PM type already exist
        //setting data
        TypePM typePM=typePMService.getById(offre.getTypePM().getId());
        TypeOffre typeOffre=typeOffreService.getById(offre.getTypeOffre().getId());
        offre.setTypeOffre(typeOffre);
        offre.setTypePM(typePM);

        //return offer
        return offre;

    }
}
