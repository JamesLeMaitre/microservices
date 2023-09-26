package com.esmc.gestionFranchise.servicesInterface;


import com.esmc.gestionFranchise.entities.ActiviteBudgetAssocie;

import java.util.List;

public interface ActiviteBudgetAssocieService {
    List<ActiviteBudgetAssocie> getActiviteBudgetAssocie();
    ActiviteBudgetAssocie ajouterActiviteBudgetAssocie(ActiviteBudgetAssocie activiteBudgetAssocie);
    ActiviteBudgetAssocie getActiviteBudgetAssociebyId(Long id);
    void deleteActiviteBudgetAssocie(Long id);
}
