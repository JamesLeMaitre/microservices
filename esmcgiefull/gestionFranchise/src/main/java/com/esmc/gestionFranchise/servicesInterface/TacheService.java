package com.esmc.gestionFranchise.servicesInterface;


import com.esmc.gestionFranchise.entities.Tache;

import java.util.List;

public interface TacheService {
    List<Tache> getTache();
    List<Tache> getTachebyFichedePoste(Long id);
    Tache ajouterTache(Tache tache);
    Tache getTachebyId(Long id);
    void deleteTache(Long id);
    List<Tache> listTache(Long id);

    Tache updatetache(Long id,Tache tache);
}
