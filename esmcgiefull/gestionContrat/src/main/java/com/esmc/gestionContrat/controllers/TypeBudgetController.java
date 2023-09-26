package com.esmc.gestionContrat.controllers;

import com.esmc.gestionContrat.entities.TypeBudget;
import com.esmc.gestionContrat.service.TypeBudgetService;
import com.esmc.gestionContrat.serviceInterfaces.TypeBudgetServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/typeBudget")
public class TypeBudgetController {

    @Autowired
    private TypeBudgetService typeBudgetService;

    @PostMapping("/save")
    public TypeBudget ajouterTypeBudgetService(@RequestBody TypeBudget t) {
        return typeBudgetService.addTypeBudget(t);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<TypeBudget>updateTypeBudget(@PathVariable("id") Long id , @RequestBody TypeBudget typebudget) {
        typebudget.setId(id);
        return new ResponseEntity<TypeBudget>(typeBudgetService.updateTypeBudget(typebudget),HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public void supprimerTypeBudget(@PathVariable Long id) {
        typeBudgetService.deleteTypeBudget(id);
    }

    @GetMapping("/list")
    public List<TypeBudget> listTypeBudget() {
        return typeBudgetService.listTypeBudget();
    }
}
