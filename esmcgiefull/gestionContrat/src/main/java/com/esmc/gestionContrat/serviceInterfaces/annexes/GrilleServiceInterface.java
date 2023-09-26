package com.esmc.gestionContrat.serviceInterfaces.annexes;

import com.esmc.gestionContrat.entities.annexes.Grille;

import java.util.List;

public interface GrilleServiceInterface {
    List<Grille> getAll();
    Grille create(Grille grille,Long idDetailsAgr);
    void delete(Long id);
    Grille edit(Grille grille,Long id);
    Grille getById(Long id);
    int getCountAll();
}
