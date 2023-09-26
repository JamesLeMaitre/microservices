package com.esmc.gestionPayement.ServicesInterface;

import com.esmc.gestionPayement.entities.TypePayement;

import java.util.List;

public interface TypePayementServiceInterface {

    List<TypePayement> getTypePayement();

    TypePayement TypePayement(Long id);

    TypePayement saveTypePayement(TypePayement typePayement);

    void deleteTypePayement(Long id);

    TypePayement updateTypePayement(Long id, TypePayement typePayement);
}
