package com.esmc.gestionContrat.service;

import com.esmc.gestionContrat.entities.Budget;
import com.esmc.gestionContrat.repositories.BudgetRepository;
import com.esmc.gestionContrat.serviceInterfaces.BudgetServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BudgetService implements BudgetServiceInterface {

    @Autowired
    private BudgetRepository budgetRepository;

    @Override
    @Transactional
    public Budget addBudget(Budget b) {
        return budgetRepository.save(b);
    }

    @Override
    @Transactional
    public Budget updateBudget( Budget b) {
        return budgetRepository.save(b);
    }

    @Override
    @Transactional
    public void deleteBudget(Long id) {
        budgetRepository.deleteById(id);
    }

    @Override
    public List<Budget> listBudget() {
        return budgetRepository.findAll();
    }

    @Override
    public List<Budget> listBudgetContrat(Long id) {
        return budgetRepository.listeBudgetByContrat(id);
    }

    @Override
    public List<Budget> listBudgetTypeBudget(Long id) {
        return budgetRepository.listeBudgetByTypeBudget(id);
    }

}
