package com.esmc.gestionContrat.serviceInterfaces;

import com.esmc.gestionContrat.entities.FicheSuiviContrat;

import java.util.List;

public interface FicheSuiviContratServiceInterface {

    public FicheSuiviContrat addFicheSuiviContrat(FicheSuiviContrat f);
     FicheSuiviContrat updateFicheSuiviContrat(FicheSuiviContrat f);
    public void deleteFicheSuiviContrat(Long id);
    public List<FicheSuiviContrat> listFicheSuiviContrat();
}
