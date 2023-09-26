package com.esmc.gestionFranchise.controller;


import com.esmc.gestionFranchise.entities.ActiviteBudgetAssocie;
import com.esmc.gestionFranchise.servicesInterface.ActiviteBudgetAssocieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/ActiviteBudgetAssocie")
public class ActiviteBudgetAssocieController {

    @Autowired
    private ActiviteBudgetAssocieService activiteBudgetAssocieService;

    @PostMapping("/save")
    public ResponseEntity<ActiviteBudgetAssocie> saveActiviteBudgetAssocie(@RequestBody ActiviteBudgetAssocie activiteBudgetAssocie){
        return new ResponseEntity<ActiviteBudgetAssocie>(activiteBudgetAssocieService.ajouterActiviteBudgetAssocie(activiteBudgetAssocie), HttpStatus.OK);
    }

    @GetMapping("/listAll")
    public ResponseEntity<List<ActiviteBudgetAssocie>>getActiviteBudgetAssocie(){
        return new ResponseEntity<List<ActiviteBudgetAssocie>>(activiteBudgetAssocieService.getActiviteBudgetAssocie(),HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ActiviteBudgetAssocie> ActiviteBudgetAssocie(@PathVariable("id") Long id){
        return new ResponseEntity<ActiviteBudgetAssocie>( activiteBudgetAssocieService.getActiviteBudgetAssociebyId(id),HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ActiviteBudgetAssocie> updateActiviteBudgetAssocie(@PathVariable("id") Long id , @RequestBody ActiviteBudgetAssocie ActiviteBudgetAssocie ){
        ActiviteBudgetAssocie.setId(id);
        return new  ResponseEntity<ActiviteBudgetAssocie>(activiteBudgetAssocieService.ajouterActiviteBudgetAssocie(ActiviteBudgetAssocie), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public  ResponseEntity<ActiviteBudgetAssocie> deleteActiviteBudgetAssocie(@PathVariable Long id ){
        activiteBudgetAssocieService.deleteActiviteBudgetAssocie(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
