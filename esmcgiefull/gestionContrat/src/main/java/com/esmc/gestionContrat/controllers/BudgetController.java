package com.esmc.gestionContrat.controllers;

import com.esmc.gestionContrat.entities.Budget;
import com.esmc.gestionContrat.service.BudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/budget")
public class BudgetController {

    @Autowired
    private BudgetService budgetService;

    @PostMapping("/save")
    public Budget ajouterBudget(@RequestBody Budget b) {
        return budgetService.addBudget(b);
    }

    @PutMapping("/edit/{id}")
    public Budget updateBudget(@PathVariable Long id, @RequestBody Budget budget) {
        budget.setId(id);
        return budgetService.updateBudget(budget);
    }

    @DeleteMapping("/delete/{id}")
    public void supprimerBudget(@PathVariable Long id) {
        budgetService.deleteBudget(id);
    }

    @GetMapping("/list")
    public List<Budget> listBudget() {
        return budgetService.listBudget();
    }

    @GetMapping("/list/{id}/TypeBudget")
    public List<Budget> listBudgetTypeBudget(@PathVariable Long id) {
        return budgetService.listBudgetTypeBudget(id);
    }

    @GetMapping("/list/{id}/Contrat")
    public List<Budget> listBudgetContrat(@PathVariable Long id) {
        return budgetService.listBudgetContrat(id);
    }


}
