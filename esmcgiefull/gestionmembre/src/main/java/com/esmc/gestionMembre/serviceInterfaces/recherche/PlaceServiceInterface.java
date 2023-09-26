package com.esmc.gestionMembre.serviceInterfaces.recherche;


import com.esmc.gestionMembre.entities.Place;

import java.util.List;

public interface PlaceServiceInterface {
    List<Place> getPacleByCodeMemebre(String codeMemebre);
}
