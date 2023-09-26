package com.esmc.gestionContrat.controllers;

import com.esmc.gestionContrat.entities.FicheSuiviContrat;
import com.esmc.gestionContrat.service.FicheSuiviContratService;
import com.esmc.gestionContrat.serviceInterfaces.FicheSuiviContratServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ficheSuiviContrat")
public class FicheSuiviContratController {

    @Autowired
    private FicheSuiviContratService ficheSuiviContratService;

    @PostMapping("/save")
    public FicheSuiviContrat ajouterFicheSuiviContrat(@RequestBody FicheSuiviContrat f) {
        return ficheSuiviContratService.addFicheSuiviContrat(f);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<FicheSuiviContrat>  updateFicheSuiviContrat(@PathVariable Long id, @RequestBody FicheSuiviContrat fiche) {
        fiche.setId(id);
        return new ResponseEntity<FicheSuiviContrat>(ficheSuiviContratService.updateFicheSuiviContrat(fiche), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public void supprimerFicheSuiviContrat(@PathVariable Long id) {
        ficheSuiviContratService.deleteFicheSuiviContrat(id);
    }

    @GetMapping("/list")
    public List<FicheSuiviContrat> listFicheSuiviContrat() {
        return ficheSuiviContratService.listFicheSuiviContrat();
    }
}
