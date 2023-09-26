package com.esmc.gestionMembre.serviceInterfaces.recherche;

import com.esmc.gestionMembre.entities.Credit;
import com.esmc.gestionMembre.entities.Place;
import com.esmc.gestionMembre.input.Result;

import java.util.List;

public interface RedemareServiceInterface {

    public Result<Long, List<Credit>> getCreditCNCS(String code, Long idKsu);

    public Result<Long, List<Place>> getPlaceCnp(String code, Long idKsu);

}
