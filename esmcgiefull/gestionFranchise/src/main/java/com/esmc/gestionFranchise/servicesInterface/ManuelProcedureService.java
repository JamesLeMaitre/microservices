package com.esmc.gestionFranchise.servicesInterface;


import com.esmc.gestionFranchise.entities.ManuelProcedure;

import java.util.List;

public interface ManuelProcedureService {
    List<ManuelProcedure> getmanuelProcedure();
    List<ManuelProcedure> getmanuelProcedurebyTache(Long id);
    ManuelProcedure ajoutermanuelProcedure(ManuelProcedure manuelProcedure);
    ManuelProcedure getmanuelProcedurebyId(Long id);
    void deletemanuelProcedure(Long id);
    ManuelProcedure listManuelProcedure(Long id);


}
