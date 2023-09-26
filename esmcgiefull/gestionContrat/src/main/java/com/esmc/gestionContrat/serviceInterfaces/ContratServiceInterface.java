package com.esmc.gestionContrat.serviceInterfaces;

import com.esmc.gestionContrat.entities.Contrat;
import com.esmc.models.Ksu;

import java.util.List;

public interface ContratServiceInterface {

     Contrat addContrat(Contrat c);
     Contrat updateContrat(Contrat c,Long id);
     void deleteContrat(Long id);
     List<Contrat> listContrat();
     Contrat saveWithDetailsAgr(Contrat c, Long idDtAgr, String code);
     Contrat getById(Long id);

     Ksu getKsu(Long id);

}
