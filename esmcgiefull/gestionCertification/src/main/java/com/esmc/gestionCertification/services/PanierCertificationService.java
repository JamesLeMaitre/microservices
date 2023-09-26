package com.esmc.gestionCertification.services;

import com.esmc.gestionCertification.entities.PanierCertification;
import com.esmc.gestionCertification.input.PanierInput;

import java.util.List;

public interface PanierCertificationService {

    List<PanierCertification> getPanierCertification();
    PanierCertification  ajouterPanierCertification ( PanierInput p );

    Boolean checkPanier(PanierInput panierCertification);

    PanierCertification  getPanierCertificationbyId(Long id);
    PanierCertification updatePanierCertification(Long id, PanierCertification p);

    List<PanierCertification> listPanierCertification(Long idDetailAgr, Long idPoste);

    PanierCertification getPanierCertification(Long idDetailAgr, Long idPoste,Long idDetailAgrFranchiser);

    void deletePanierCertification (Long id);
    
}
