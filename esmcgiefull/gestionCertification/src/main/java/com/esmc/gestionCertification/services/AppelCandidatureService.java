package com.esmc.gestionCertification.services;


import com.esmc.gestionCertification.entities.AppelCandidature;

import java.util.List;

public interface AppelCandidatureService {

    List<AppelCandidature> getAppelCandidature();
    AppelCandidature  ajouterAppelCandidature ( AppelCandidature  appelCandidature );
    AppelCandidature  getAppelCandidaturebyId(Long id);

    List<AppelCandidature> listAppelCandidaturebyId(Long id);

    void deleteAppelCandidature (Long id);


    AppelCandidature publicationAppelCandidaure(Long idAp);
}
