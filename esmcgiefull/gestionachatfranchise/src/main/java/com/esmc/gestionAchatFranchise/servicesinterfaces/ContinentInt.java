package com.esmc.gestionAchatFranchise.servicesinterfaces;

import com.esmc.gestionAchatFranchise.entities.Continent;

import java.util.List;

public interface ContinentInt {
    List<Continent> getContinent();

    Continent getContinentById(Long id);

    Continent addContinent(Continent continent);

    Continent updateContinent( Continent continent);

    void deleteContinent(Long continentId);

    int getCountAll();

    int getCountFreeContinentByMonde();

    int getCountBuyContinentByMonde();
}
