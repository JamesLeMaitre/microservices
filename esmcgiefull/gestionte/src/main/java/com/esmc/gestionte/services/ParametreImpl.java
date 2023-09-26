package com.esmc.gestionte.services;



import com.esmc.gestionte.entities.Parametre;
import com.esmc.gestionte.exceptions.Exceptions;
import com.esmc.gestionte.repositories.ParametreRepository;
import com.esmc.gestionte.serviceinterface.ParametreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class ParametreImpl implements ParametreService {

    @Autowired
    ParametreRepository parametreRepository;
    @Override
    public List<Parametre> getParametre() {
        return parametreRepository.findAll();
    }

    @Override
    public void addNewParametre(Parametre parametre) throws Exceptions {

        parametreRepository.save(parametre);

    }

    @Override
    public void deleteParametre(Long id) throws Exceptions {
        boolean exists = parametreRepository.existsById(id);
        if(!exists)
            throw  new Exceptions(Exceptions.alertGeneralException("agr dont l'id "+id+"n'existe pas "));
        parametreRepository.deleteById(id);

    }

    @Override
    public void updateParametre(Long id, Parametre parametre) throws Exceptions {

        if(!isPresent(parametre.getId()))
            throw new Exceptions(Exceptions.alertGeneralException("l'identifiant n'existe pas"));
        Parametre parametre1=getById(parametre.getId());

        parametre1.setPCK(parametre.getPCK());
        parametre1.setPRK(parametre.getPRK());
        parametre1.setLibelle(parametre.getLibelle());


        //update supportMarchandEnchage
        parametre=parametreRepository.save(parametre1);

        if (parametre==null){
            throw new Exceptions(Exceptions.alertGeneralException("modification reussi"));
        }

    }

    @Override
    public Parametre getParametreEsmc() {
        return parametreRepository.getParametre();
    }

    @Override
    public Parametre getById(Long id) throws Exceptions {
        if(!isPresent(id))
            throw new Exceptions(Exceptions.alertGeneralException("l'identifiant n'esiste pas"));
        Optional<Parametre> opad=parametreRepository.findById(id);
        return  opad.get();
    }
    public boolean isPresent(Long id){
        Optional<Parametre> opab=parametreRepository.findById(id);
        if (opab.isPresent())
            return true;
        return false;
    }
}
