package com.esmc.gestionFranchise.servicesInterface;

import com.esmc.gestionFranchise.entities.AssembleGenerale;

import java.util.List;

public interface AssembleGeneraleService {
    List<AssembleGenerale> getAssembleGenerale();
    AssembleGenerale ajouterAssembleGenerale(AssembleGenerale assembleGenerale);
    AssembleGenerale getAssembleGeneralebyId(Long id);
    void deleteAssembleGenerale(Long id);
}
