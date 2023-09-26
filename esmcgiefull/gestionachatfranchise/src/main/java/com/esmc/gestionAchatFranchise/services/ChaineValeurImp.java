package com.esmc.gestionAchatFranchise.services;

import com.esmc.gestionAchatFranchise.entities.ChaineValeur;
import com.esmc.gestionAchatFranchise.repositories.ChaineValeurRepository;
import com.esmc.gestionAchatFranchise.servicesinterfaces.ChaineValeurInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ChaineValeurImp implements ChaineValeurInt {
    @Autowired
    private ChaineValeurRepository chaineValeurRep;

    @Override
    public List<ChaineValeur> getChaineValeur() {
        return chaineValeurRep.findAll();
    }

    @Override
    public ChaineValeur getChaineValeurById(Long id) {
        return chaineValeurRep.findById(id).get();
    }

    @Override
    public ChaineValeur addChaineValeur(ChaineValeur chaineValeur) {
        chaineValeur.setDateCreate(new Date());
        chaineValeur.setDateUpdate(new Date());
        return chaineValeurRep.save(chaineValeur);    }

    @Override
    public ChaineValeur updateChaineValeur( ChaineValeur chaineValeur) {
        return chaineValeurRep.save(chaineValeur);
    }

    @Override
    public void deleteChaineValeur(@PathVariable Long chaineValeurId) {
        chaineValeurRep.deleteById(chaineValeurId);
    }

    @Override
    public List<ChaineValeur> listDetailDestination(Long id) {
        return chaineValeurRep.findChaineValeurByDetailDestination(id);
    }

}
