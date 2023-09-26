package com.esmc.gestionFranchise.servicesInterface.organev2;

import com.esmc.gestionFranchise.datafomater.StrufctureJSON;
import com.esmc.gestionFranchise.entities.organev2.Structure;

import java.util.List;

public interface StructureService {

    List<Structure> getAll();
    Structure getById(Long id);

    Structure create(Structure data);
    Structure update(Long id, Structure data);

    Structure disable(Long id);

    Structure enable(Long id);

    void delete(Long id);


    void loadResource(StrufctureJSON[] data);
}
