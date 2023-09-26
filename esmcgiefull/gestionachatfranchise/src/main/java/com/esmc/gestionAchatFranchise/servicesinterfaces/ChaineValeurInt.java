package com.esmc.gestionAchatFranchise.servicesinterfaces;

import com.esmc.gestionAchatFranchise.entities.ChaineValeur;

import java.util.List;

public interface ChaineValeurInt {
    List<ChaineValeur> getChaineValeur();

    ChaineValeur getChaineValeurById(Long id);

    ChaineValeur addChaineValeur(ChaineValeur chaineValeur);

    ChaineValeur updateChaineValeur( ChaineValeur chaineValeur);

    void deleteChaineValeur(Long chaineValeurId);

    public  List<ChaineValeur> listDetailDestination(Long id);


}
