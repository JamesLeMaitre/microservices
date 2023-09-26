package com.esmc.gestionAchatFranchise.services;

import com.esmc.gestionAchatFranchise.entities.ChaineDistribution;
import com.esmc.gestionAchatFranchise.repositories.ChaineDistributionRepository;
import com.esmc.gestionAchatFranchise.servicesinterfaces.ChaineDistributionInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ChaineDistributionImp implements ChaineDistributionInt {
    @Autowired
    private ChaineDistributionRepository chaineDistributionRep;

    @Override
    public List<ChaineDistribution> getChaineDistribution() {
        return  chaineDistributionRep.findAll();    }

    @Override
    public ChaineDistribution getChaineDistributionById(Long id) {
        return chaineDistributionRep.findById(id).get();    }

    @Override
    public ChaineDistribution addChaineDistribution(ChaineDistribution chaineDistribution) {
        chaineDistribution.setDateCreate(new Date());
        chaineDistribution.setDateUpdate(new Date());
        return chaineDistributionRep.save(chaineDistribution);
    }

    @Override
    public ChaineDistribution updateChaineDistribution( ChaineDistribution chaineDistribution) {
        return chaineDistributionRep.save(chaineDistribution);
    }

    @Override
    public void deleteChaineDistribution(@PathVariable Long chaineDistributionId) {
        chaineDistributionRep.deleteById(chaineDistributionId);
    }

    @Override
    public List<ChaineDistribution> listChaineValeur(Long id) {
        return  chaineDistributionRep.findChaineDistributionByChaineValeur(id);
    }
}
