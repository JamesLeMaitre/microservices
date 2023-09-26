package com.esmc.gestionAchatFranchise.services;

import com.esmc.gestionAchatFranchise.entities.Continent;
import com.esmc.gestionAchatFranchise.repositories.ContinentRepository;
import com.esmc.gestionAchatFranchise.servicesinterfaces.ContinentInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
@Transactional
public class ContinentImp implements ContinentInt {
    @Autowired
    private ContinentRepository continentRep;

    @Override
    public List<Continent> getContinent() {
        return continentRep.findAll();
    }

    @Override
    public Continent getContinentById(Long id) {
        return continentRep.findById(id).get();
    }

    @Override
    public Continent addContinent(Continent continent) {
        return continentRep.save(continent);
    }

    @Override
    public Continent updateContinent( Continent continent) {
        return continentRep.save(continent);
    }

    @Override
    public void deleteContinent(@PathVariable Long continentId) {
        continentRep.deleteById(continentId);
    }

    @Override
    public int getCountAll() {
        return (int) continentRep.count();
    }

    @Override
    public int getCountFreeContinentByMonde() {
        return continentRep.getCountFreeContinentByMonde();
    }

    @Override
    public int getCountBuyContinentByMonde() {
        return continentRep.getCountBuyContinentByMonde();
    }
}
