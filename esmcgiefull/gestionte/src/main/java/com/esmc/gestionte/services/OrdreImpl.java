package com.esmc.gestionte.services;

import com.esmc.gestionte.entities.Ordre;
import com.esmc.gestionte.exceptions.Exceptions;
import com.esmc.gestionte.repositories.OrdreRepository;
import com.esmc.gestionte.serviceinterface.OrdreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OrdreImpl implements OrdreService {

    @Autowired
    private OrdreRepository ordreRepository;

    @Override
    public List<Ordre> getOrdre() {
        return ordreRepository.findAll();
    }

    @Override
    public void addNewOrdre(Ordre ordre) throws Exceptions {
        if(!isPresent(ordre.getId())){
            throw new Exceptions(Exceptions.codeSMNotFound(ordre.getCodeOPI()));
        }
        ordre.setDateEmission(new Date());
        ordre.setDateCreate(new Date());
        ordre.setDateUpdate(new Date());
        Ordre ordre1=ordreRepository.save(ordre);
        if (ordre1==null){
            throw new Exceptions(Exceptions.alertGeneralException("erreur d'ajout"));
        }

    }

    @Override
    public void deleteOrdre(Long id) throws Exceptions {
        boolean exists = ordreRepository.existsById(id);
        if(!exists)
            throw  new Exceptions(Exceptions.alertGeneralException("agr dont l id "+id+"n'existe pas "));

        ordreRepository.deleteById(id);

    }

    @Override
    public void updateOrdre(Long id, Ordre ordre) throws Exceptions {

        if(!isPresent(ordre.getId()))
            throw new Exceptions(Exceptions.alertGeneralException("l'identifiant n'esiste pas"));
        Ordre ordre1=getById(ordre.getId());

        ordre1.setNumeroOPI(ordre.getNumeroOPI());
        ordre1.setCodeOPI(ordre.getCodeOPI());
        ordre1.setNumeroFacture(ordre.getNumeroFacture());
        ordre1.setMontantOPI(ordre.getMontantOPI());
        ordre1.setDatePrelevementOPI(new Date());
        ordre1.setCodeBar(ordre.getCodeBar());
        ordre1.setDateEmission(ordre.getDateEmission());
        ordre1.setSignature(ordre.getSignature());
        ordre1.setNumeroCompteBancaire(ordre.getNumeroCompteBancaire());
        ordre1.setDateUpdate(new Date());

        //update detailSMEnchange
        ordre=ordreRepository.save(ordre1);

        if (ordre==null){
            throw new Exceptions(Exceptions.alertGeneralException("modification reussi"));
        }

    }

    @Override
    public Ordre getById(Long id) throws Exceptions {
        if(!isPresent(id))
            throw new Exceptions(Exceptions.alertGeneralException("l'identifiant n'esiste pas"));
        Optional<Ordre> opad=ordreRepository.findById(id);
        return  opad.get();
    }
    public boolean isPresent(String codeSM){
        Optional<Ordre> opab=ordreRepository.findOrdreByNumeroOPI(codeSM);
        if (opab.isPresent())
            return true;
        return false;
    }
    public boolean isPresent(Long id){
        Optional<Ordre> opab=ordreRepository.findById(id);
        if (opab.isPresent())
            return true;
        return false;
    }
}
