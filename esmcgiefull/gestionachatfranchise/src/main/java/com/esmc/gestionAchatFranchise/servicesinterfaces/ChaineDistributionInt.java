package com.esmc.gestionAchatFranchise.servicesinterfaces;

import com.esmc.gestionAchatFranchise.entities.ChaineDistribution;

import java.util.List;

public interface ChaineDistributionInt {
    List<ChaineDistribution> getChaineDistribution();

    ChaineDistribution getChaineDistributionById(Long id);

    ChaineDistribution addChaineDistribution(ChaineDistribution chaineDistribution);

    ChaineDistribution updateChaineDistribution( ChaineDistribution chaineDistribution);

    void deleteChaineDistribution(Long chaineDistributionId);

    public  List<ChaineDistribution> listChaineValeur(Long id);
}
