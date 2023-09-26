package com.esmc.gestionFranchise.controller;

import com.esmc.gestionFranchise.entities.CoffreFort;
import com.esmc.gestionFranchise.servicesInterface.CoffreFortService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/CoffreFort")
public class CoffreFortController {

    @Autowired
    private CoffreFortService CoffreFortService;

    @PostMapping("/save")
    public ResponseEntity<CoffreFort> saveCoffreFort(@RequestBody CoffreFort CoffreFort){
        return new ResponseEntity<CoffreFort>(CoffreFortService.ajouterCoffreFort(CoffreFort),HttpStatus.OK);
    }

    @GetMapping("/listAll")
    public ResponseEntity<List<CoffreFort>>getCoffreFort(){
        return new ResponseEntity<List<CoffreFort>>(CoffreFortService.getCoffreFort(),HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<CoffreFort> CoffreFort(@PathVariable("id") Long id){
        return new ResponseEntity<CoffreFort>( CoffreFortService.getCoffreFortbyId(id),HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CoffreFort> updateCoffreFort(@PathVariable("id") Long id , @RequestBody CoffreFort CoffreFort ){
        CoffreFort.setId(id);
        return new  ResponseEntity<CoffreFort>(CoffreFortService.ajouterCoffreFort(CoffreFort), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public  ResponseEntity<CoffreFort> deleteCoffreFort(@PathVariable Long id ){
        CoffreFortService.deleteCoffreFort(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
