package com.esmc.gestionAchatFranchise.services;

import com.esmc.gestionAchatFranchise.entities.DetailCentreFranchise;
import com.esmc.gestionAchatFranchise.repositories.DetailCentreFranchiseRepository;
import com.esmc.gestionAchatFranchise.servicesinterfaces.DetailCentreFranchiseInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class DetailCentreFranchiseImp implements DetailCentreFranchiseInt {
    @Autowired
    private DetailCentreFranchiseRepository detailCentreFranchiseRep;

    @Override
    public List<DetailCentreFranchise> getDetailCentreFranchise() {
        return  detailCentreFranchiseRep.findAll();    }

    @Override
    public DetailCentreFranchise getDetailCentreFranchiseById(Long id) {
        return detailCentreFranchiseRep.findById(id).get();     }

    @Override
    public DetailCentreFranchise addDetailCentreFranchise(DetailCentreFranchise detailCentreFranchise) {
        detailCentreFranchise.setDateCreate(new Date());
        detailCentreFranchise.setDateUpdate(new Date());
        return detailCentreFranchiseRep.save(detailCentreFranchise);    }

    @Override
    public DetailCentreFranchise updateDetailCentreFranchise( DetailCentreFranchise detailCentreFranchise) {
        return   detailCentreFranchiseRep.save(detailCentreFranchise);
    }
    @Override
    public void deleteDetailCentreFranchise(@PathVariable Long detailCentreFranchiseId) {
        detailCentreFranchiseRep.deleteById(detailCentreFranchiseId);
    }

    @Override
    public List<DetailCentreFranchise> listCentreFranchise(Long id) {
        return detailCentreFranchiseRep.findDetailCentreFranchiseByCentreFranchise(id);
    }

    @Override
    public List<DetailCentreFranchise> listCanton(Long id) {
        return detailCentreFranchiseRep.findDetailCentreFranchiseByCanton(id);
    }

    @Override
    public List<DetailCentreFranchise> listCentreFranchiseAndCanton(Long id, Long id2) {
        return detailCentreFranchiseRep.findDetailCentreFranchiseByCentreFranchiseAndCanton(id, id2);

    }
}
