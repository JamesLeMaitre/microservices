package com.esmc.gestionAchatFranchise.services;

import com.esmc.gestionAchatFranchise.entities.Centre;
import com.esmc.gestionAchatFranchise.repositories.CentreRepository;
import com.esmc.gestionAchatFranchise.servicesinterfaces.CentreInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class CentreImp implements CentreInt {

    @Autowired
    private CentreRepository centreRep;

    // public CentreImp(CentreRepository centreRep ) {
    //  this.centreRep = centreRep;
    // }

    @Override
    public List<Centre> getCentre() {
        return  centreRep.findAll();
    }

    @Override
    public Centre getCentreById(Long id) {
        return centreRep.findById(id).get();
    }

    @Override
    public Centre addCentre(Centre centre) {
        centre.setDateCreate(new Date());
        centre.setDateUpdate(new Date());
        return centreRep.save(centre);
    }

    @Override
    public Centre updateCentre( Centre centre) {
        return centreRep.save(centre);
    }

    @Override
    public void deleteCentre(@PathVariable Long centreId) {
        centreRep.deleteById(centreId);
    }

    @Override
    public List<Centre> listCentreFranchiseCentre(Long id) {
        return centreRep.findCentreByCentreFranchise(id);
    }
}
