package com.esmc.gestionContrat.service;

import com.esmc.gestionContrat.entities.TypeBudget;
import com.esmc.gestionContrat.repositories.TypeBudgetRepository;
import com.esmc.gestionContrat.serviceInterfaces.TypeBudgetServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TypeBudgetService implements TypeBudgetServiceInterface {

    @Autowired
    private TypeBudgetRepository budgetRepository;
    @Override
    @Transactional
    public TypeBudget addTypeBudget(TypeBudget t) {
        return budgetRepository.save(t);
    }

    /**
     * @param typeBudget
     * @return
     */

    @Override
    @Transactional
    public TypeBudget updateTypeBudget(TypeBudget typeBudget) {
        return budgetRepository.save(typeBudget);
    }

    @Override
    @Transactional
    public void deleteTypeBudget(Long id) { budgetRepository.deleteById(id);

    }

    @Override
    @Transactional
    public List<TypeBudget> listTypeBudget() {
        return budgetRepository.findAll();
    }
}
