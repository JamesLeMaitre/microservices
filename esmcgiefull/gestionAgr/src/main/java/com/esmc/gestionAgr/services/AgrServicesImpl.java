package com.esmc.gestionAgr.services;

import com.esmc.gestionAgr.entities.Agr;
import com.esmc.gestionAgr.exceptions.Exceptions;
import com.esmc.gestionAgr.repositories.AgrRepository;
import com.esmc.gestionAgr.serviceinterfaces.AgrServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AgrServicesImpl implements AgrServiceInterface {

    @Autowired
    private AgrRepository agrRepository;

    @Override
    public List<Agr> getAgr() {

        return agrRepository.findAll();
    }

    @Override
    public Agr addNewAgr(Agr agr){
        return agrRepository.save(agr);
    }


    @Override
    public void deleteAgr(Long agrId) throws Exceptions {
        boolean exists = agrRepository.existsById(agrId);
        if (!exists)
            throw new Exceptions(Exceptions.alertGeneralException("agr dont l id " + agrId + "n'existe pas "));

        agrRepository.deleteById(agrId);

    }

    @Override
    public void updateAgr(Long id, Agr agr) throws Exceptions {
//        if (!isPresent(id))
//            throw new Exceptions(Exceptions.alertGeneralException("l'identifiant n'esiste pas"));
//        Agr agr1 = getById(id);

        Date d = new Date();

        Agr a = agrRepository.findById(id).orElse(null);

        a.setLibelle(a.getLibelle());
        a.setDescription(agr.getDescription());
        a.setEtat(agr.isEtat());
        a.setDateUpdate(d);

        //update agr
        agr = agrRepository.save(a);

//        if (agr == null) {
//            throw new Exceptions(Exceptions.alertGeneralException("modification reussi"));
//        }

    }

    

    @Override
    public Agr getById(Long id) throws Exceptions {
        //check id
        if (!isPresent(id))
            throw new Exceptions(Exceptions.alertGeneralException("l'identifiant n'esiste pas"));
        Optional<Agr> opad = agrRepository.findById(id);
        return opad.get();
    }


    public boolean isPresent(String libelle) {
        Optional<Agr> opab = agrRepository.findAgrByLibelle(libelle);
        if (opab.isPresent())
            return true;
        return false;
    }

    public boolean isPresent(Long id) {
        Optional<Agr> opab = agrRepository.findById(id);
        if (opab.isPresent())
            return true;
        return false;
    }

    public boolean isType(Long id) {
        Optional<Agr> opab = agrRepository.findById(id);
        if (opab.isPresent())
            return true;
        return false;
    }


}
