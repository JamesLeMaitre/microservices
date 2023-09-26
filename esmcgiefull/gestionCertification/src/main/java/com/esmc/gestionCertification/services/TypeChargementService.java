package com.esmc.gestionCertification.services;


import com.esmc.gestionCertification.entities.TypeChargement;

import java.util.List;

public interface TypeChargementService {

    List<TypeChargement> getTypeChargement();
    TypeChargement  ajouterTypeChargement ( TypeChargement  TypeChargement );
    TypeChargement  getTypeChargementbyId(Long id);
    void deleteTypeChargement (Long id);
}
