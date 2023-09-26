package com.esmc.gestionMembre.services.recherche;


import com.esmc.gestionMembre.entities.Place;
import com.esmc.gestionMembre.repositories.PlaceRepository;
import com.esmc.gestionMembre.serviceInterfaces.recherche.PlaceServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional
@Service
public class PlaceServiceImpl implements PlaceServiceInterface {


    @Autowired
    private PlaceRepository placeRepository;

    @Override
    public List<Place> getPacleByCodeMemebre(String codeMemebre) {
        return placeRepository.passifRedemareByCodeMembre(codeMemebre);
    }
}
