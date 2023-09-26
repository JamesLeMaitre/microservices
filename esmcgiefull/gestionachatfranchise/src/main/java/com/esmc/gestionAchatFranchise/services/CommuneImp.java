package com.esmc.gestionAchatFranchise.services;

import com.esmc.gestionAchatFranchise.entities.Commune;
import com.esmc.gestionAchatFranchise.repositories.CommuneRepository;
import com.esmc.gestionAchatFranchise.servicesinterfaces.CommuneInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class CommuneImp implements CommuneInt {
    @Autowired
    private CommuneRepository communeRep;

    @Override
    public List<Commune> getCommune() {
        return communeRep.findAll();
    }

    @Override
    public Commune getCommuneById(Long id) {
        return communeRep.findById(id).orElse(null);
    }

    @Override
    public Commune addCommune(Commune commune) {
        commune.setDateCreate(new Date());
        commune.setDateUpdate(new Date());
        return communeRep.save(commune);
    }

    @Override
    public Commune updateCommune( Commune commune) {
        return communeRep.save(commune);
    }

    @Override
    public void deleteCommune(@PathVariable Long communeId) {
        communeRep.deleteById(communeId);
    }

    @Override
    public List<Commune> listPrefecture(Long id) {
        return communeRep.findCommuneByPrefecture(id);
    }

    @Override
    public int getCountAll() {
        return (int) communeRep.count();
    }

    @Override
    public int getCountFreeCommuneByPrefecture(Long idFranchise) {
        return communeRep.getCountFreeCommuneByPrefecture(idFranchise);
    }

    @Override
    public int getCountBuyCommuneByPrefecture(Long idFranchise) {
        return communeRep.getCountBuyCommuneByPrefecture(idFranchise);
    }


}
