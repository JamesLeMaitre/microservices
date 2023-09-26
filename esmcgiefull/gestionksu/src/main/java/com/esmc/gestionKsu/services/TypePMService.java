package com.esmc.gestionKsu.services;

import com.esmc.gestionKsu.entities.TypePM;
import com.esmc.gestionKsu.repositories.TypePMRepository;
import com.esmc.gestionKsu.serviceinterfaces.TypePMServiceInterface;
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
 * {@link TypePM} service
 * @author katoh <katohdavid@gmail.com>
 */
@Service
public class TypePMService implements TypePMServiceInterface {

    @Autowired
    private TypePMRepository typePMRepository;

    @Override
    public void save(TypePM typePM) {
        //check if exist

        typePM.setDateCreate(new Date());

        //save
        TypePM tp=typePMRepository.save(typePM);


    }

    @Override
    public void update(TypePM typePM)  {
        //Check if this typePM is already in database by id


         TypePM tp=getById(typePM.getId());

        //Check if this typePM is already in database by libelle
        if (isPresent(typePM.getLibelle()))
            tp=getById(typePM.getId());

        //setting data
        tp.setLibelle(typePM.getLibelle());
        tp.setDateUpdate(new Date());

        //update typePM
        typePM=typePMRepository.save(tp);

    }

    @Override
    public TypePM getById(Long id)  {
        //Check if exist

        //return typePM find
        Optional<TypePM> tp=typePMRepository.findById(id);
        return tp.get();
    }

    @Override
    public TypePM getByLibelle(String libelle) {
        //return typePM find
        Optional<TypePM> optp=typePMRepository.findByLibelle(libelle);
        return optp.get();
    }

    @Override
    public PagedModel<EntityModel<TypePM>> getAll(Pageable page, PagedResourcesAssembler<TypePM> pagedResourcesAssembler) {
        Page<TypePM> typePMS = typePMRepository.findAll(page);
        return pagedResourcesAssembler.toModel(typePMS);
    }

    /**
     * Check if typePM type exist by id
     * @param libelle
     * @return
     */
    public boolean isPresent(String libelle){
        Optional<TypePM> optp=typePMRepository.findByLibelle(libelle);
        if (optp.isPresent())
            return true;
        return false;
    }

    /**
     * Check if typePM type exist by id
     * @param id
     * @return
     */
    public boolean isPresent(Long id){
        Optional<TypePM> optp=typePMRepository.findById(id);
        if (optp.isPresent())
            return true;
        return false;
    }

}
