package com.esmc.gestionMembre.serviceInterfaces;

import com.esmc.gestionMembre.entities.Reclamation;

import java.util.List;

public interface ReclamationServiceInterface {

    List<Reclamation> getReclamation();

    Reclamation ajouterReclamation (Reclamation reclamation);

    Reclamation getReclamationbyId(Long id);

    void deleteReclamation (Long id);

    Reclamation updateReclamation(Long id, Reclamation reclamation);



}
