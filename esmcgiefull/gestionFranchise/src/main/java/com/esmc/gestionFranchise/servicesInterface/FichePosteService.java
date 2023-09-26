package com.esmc.gestionFranchise.servicesInterface;


import com.esmc.gestionFranchise.entities.FichePoste;
import com.esmc.gestionFranchise.entities.organev2.Structure;

import java.util.List;

public interface FichePosteService {

    List<FichePoste> getFichePoste();
    FichePoste ajouterFichePoste(FichePoste FichePoste);
    FichePoste getFichePostebyId(Long id);
    void deleteFichePoste(Long id);

    FichePoste getById(Long id);
    FichePoste modifierFichePoste(Long id,FichePoste data);

    List<FichePoste> findFichePosteByPost(Long id);


}
