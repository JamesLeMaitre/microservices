package com.esmc.gestionContrat.serviceInterfaces;

import com.esmc.gestionContrat.entities.Budget;

import java.util.List;

public interface BudgetServiceInterface {

    public Budget addBudget(Budget b);
    public Budget updateBudget( Budget b);
    public void deleteBudget(Long id);
    public List<Budget> listBudget();

    public List<Budget> listBudgetContrat(Long id);

    public List<Budget> listBudgetTypeBudget(Long id);
}
