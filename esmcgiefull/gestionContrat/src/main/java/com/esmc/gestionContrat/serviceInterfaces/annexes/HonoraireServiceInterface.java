package com.esmc.gestionContrat.serviceInterfaces.annexes;

import com.esmc.gestionContrat.entities.annexes.Grille;
import com.esmc.gestionContrat.entities.annexes.Honoraire;

import java.util.List;

public interface HonoraireServiceInterface {
    List<Honoraire> getAll();
    Honoraire create(Honoraire honoraire,Long idDetailsAgr);
    void delete(Long id);
    Honoraire edit(Honoraire honoraire,Long id);
    Honoraire getById(Long id);
    int getCountAll();
}
