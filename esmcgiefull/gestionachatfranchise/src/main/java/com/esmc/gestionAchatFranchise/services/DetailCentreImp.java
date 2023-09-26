package com.esmc.gestionAchatFranchise.services;

import com.esmc.gestionAchatFranchise.entities.DetailCentre;
import com.esmc.gestionAchatFranchise.repositories.DetailCentreRepository;
import com.esmc.gestionAchatFranchise.servicesinterfaces.DetailCentreInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class DetailCentreImp implements DetailCentreInt {
    /*@Autowired
    private DetailCentreRepository DetailCentreRep;

    @Override
    public List<DetailCentre> getDetailCentre() {
        return DetailCentreRep.findAll();
    }

    @Override
    public DetailCentre getDetailCentreById(Long id) {
        return DetailCentreRep.findById(id).get();
    }

    @Override
    public DetailCentre addDetailCentre(DetailCentre detailCentre) {
        detailCentre.setDateCreate(new Date());
        detailCentre.setDateUpdate(new Date());
        return DetailCentreRep.save(detailCentre);
    }

    @Override
    public DetailCentre updateDetailCentre( DetailCentre detailCentre) {
        return DetailCentreRep.save(detailCentre);
    }

    @Override
    public void deleteDetailCentre(@PathVariable Long detailCentreId) {
        DetailCentreRep.deleteById(detailCentreId);
    }

    @Override
    public List<DetailCentre> listAgenceOdd(Long id) {
        return DetailCentreRep.findDetailCentreByAgenceOdd(id);
    }

    @Override
    public List<DetailCentre> listCentre(Long id) {
        return DetailCentreRep.findDetailCentreByCentre(id);
    }


    @Override
    public String listCentreAndAgenceOdd(Long id, Long id2) {
        return DetailCentreRep.findDetailCentreByCentreAndAgenceOdd(id,id2);

    }*/
}
