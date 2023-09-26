package com.esmc.gestionCertification.services;


import com.esmc.gestionCertification.entities.Chargement;
import com.esmc.gestionCertification.exceptions.AppelCandidatureException;
import com.esmc.gestionCertification.input.ChargementPostInput;
import com.esmc.gestionCertification.request.ChargementRequest;

import java.util.List;

public interface ChargementService {
    List<Chargement> getChargement();
    Chargement ajouterChargementAppelCandidature(Chargement  chargement );

    Chargement addChargement(ChargementRequest chargementRequest) throws AppelCandidatureException;

    Chargement ajouterChargementPoste(ChargementPostInput chargement);

    boolean checkExist(Chargement chargement);

    boolean checkExistNew(ChargementRequest chargement);

    Chargement checkExistGet(ChargementPostInput chargement);

    boolean checkExistPoste(ChargementPostInput chargementPostInput);

    Chargement getChargementbyId(Long id);
    void deleteChargement (Long id);

    public  List<Chargement> listTypeChargement(Long id);
    public  List<Chargement> listCandidature(Long id);
    public  List<Chargement> listPoste(Long id);
    public  List<Chargement> listDetailAgrAndTypeChargement(Long id1, Long id2);

    Chargement getDetailAgrAndTypeChargement(Long id1, Long id2);

    List<Chargement> listChargementByIdDetailAgrAndCanditure(Long id1, Long id2);

    List<Chargement> listChargementByIdDetailAgrAndCanditurePoste(Long id1, Long id2);

    List<Chargement> listChargementByIdDetailAgr(Long idDetailAgr);

    public List<Chargement>addAppelCandidatureAndDetailAgr(Long id , Chargement chargement );

    public void getById(Long id );


    Chargement getChargementByIdDetailAgrAndPoste(Long id1, Long id2);
}
