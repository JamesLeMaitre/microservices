package com.esmc.gestionMembre.serviceInterfaces;

import com.esmc.gestionMembre.input.Result;
import com.esmc.gestionMembre.model.RechercheInputModel;

import java.util.List;

public interface RecherchePassifService {
    Result<Long, List<Object>> getRechercheByCodeMemebre(RechercheInputModel rechercheInputModel);

    Result<Long,Object> rechercheCompteMarchand(RechercheInputModel rechercheInputModel);
}
