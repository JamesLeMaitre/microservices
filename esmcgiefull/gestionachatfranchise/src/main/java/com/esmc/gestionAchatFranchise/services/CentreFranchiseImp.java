package com.esmc.gestionAchatFranchise.services;

import com.esmc.gestionAchatFranchise.entities.CentreFranchise;
import com.esmc.gestionAchatFranchise.repositories.CentreFranchiseRepository;
import com.esmc.gestionAchatFranchise.servicesinterfaces.CentreFranchiseInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class CentreFranchiseImp implements CentreFranchiseInt {
    @Autowired
    private CentreFranchiseRepository centreFranchiseRep;

    //public CentreFranchiseImp(CentreFranchiseRepository centreFranchiseRep) {
    // this.centreFranchiseRep = centreFranchiseRep;
    // }

    @Override
    public List<CentreFranchise> getCentreFranchise() {
        return centreFranchiseRep.findAll();
    }

    @Override
    public CentreFranchise getCentreFranchiseById(Long id) {
        return centreFranchiseRep.findById(id).get();    }

    @Override
    public CentreFranchise addCentreFranchise(CentreFranchise centreFranchise) {
        centreFranchise.setDateCreate(new Date());
        centreFranchise.setDateUpdate(new Date());
        return centreFranchiseRep.save(centreFranchise);
    }

    @Override
    public CentreFranchise updateCentreFranchise(Long id, CentreFranchise centreFranchise) {
        CentreFranchise centreFranchise1 = centreFranchiseRep.findById(id).orElse(null);
        assert centreFranchise1 != null :"ID null";
        centreFranchise1.setId(centreFranchise.getId());
        centreFranchise1.setLibelle(centreFranchise.getLibelle());
        return centreFranchiseRep.save(centreFranchise1);
    }

    @Override
    public void deleteCentreFranchise(@PathVariable Long centreFranchiseId) {
        centreFranchiseRep.deleteById(centreFranchiseId);
    }
}
