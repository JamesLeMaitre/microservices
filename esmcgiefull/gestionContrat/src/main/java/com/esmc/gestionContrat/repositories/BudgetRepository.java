package com.esmc.gestionContrat.repositories;

import com.esmc.gestionContrat.entities.Budget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BudgetRepository extends JpaRepository<Budget, Long> {
    @Query("Select c from Budget c where c.typeBudget.id = :x")
    public List<Budget> listeBudgetByTypeBudget(@Param("x") Long id);

    @Query("Select c from Budget c where c.contrat.id = :x")
    public List<Budget> listeBudgetByContrat(@Param("x") Long id);
}
