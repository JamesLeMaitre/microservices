package com.esmc.gestionCertification.services;


import com.esmc.gestionCertification.entities.Affectation;
import com.esmc.gestionCertification.input.AffectationInput;


import java.util.List;

public interface AffectationService {

    List<Affectation> getAffectation();

    Affectation  ajouterAffectation ( AffectationInput Affectation );

    int countAlready(AffectationInput affectation);


    Affectation  getAffectationbyId(Long id);
    void deleteAffectation (Long id);

    public  List<Affectation> listAffectationCandidature(Long id);
    Object listPosteByAppelCadidature(Long idAc,Long idPos);

    Affectation getLibelle(Long idAc);
    Object getKSU(Long idDetAgr);


}
