package com.esmc.gestionContrat.serviceInterfaces;

import com.esmc.gestionContrat.entities.TypeBudget;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public interface TypeBudgetServiceInterface {

    public TypeBudget addTypeBudget(TypeBudget t);
    TypeBudget updateTypeBudget( TypeBudget typeBudget);
    //public TypeBudget updateTypeBudget(Long id);
    public void deleteTypeBudget(Long id);
    public List<TypeBudget> listTypeBudget();
}
