package com.esmc.gestionFranchise.controller;

import com.esmc.gestionFranchise.entities.AssembleGenerale;
import com.esmc.gestionFranchise.servicesInterface.AssembleGeneraleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/AssembleGenerale")
public class AssembleGeneraleController {
    @Autowired
    private AssembleGeneraleService AssembleGeneraleService;

    @PostMapping("/save")
    public ResponseEntity<AssembleGenerale> saveAssembleGenerale(@RequestBody AssembleGenerale assembleGenerale){
        return new ResponseEntity<AssembleGenerale>(AssembleGeneraleService.ajouterAssembleGenerale(assembleGenerale), HttpStatus.OK);
    }

    @GetMapping("/listAll")
    public ResponseEntity<List<AssembleGenerale>>getAssembleGenerale(){
        return new ResponseEntity<List<AssembleGenerale>>(AssembleGeneraleService.getAssembleGenerale(),HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<AssembleGenerale> AssembleGenerale(@PathVariable("id") Long id){
        return new ResponseEntity<AssembleGenerale>( AssembleGeneraleService.getAssembleGeneralebyId(id),HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<AssembleGenerale> updateAssembleGenerale(@PathVariable("id") Long id , @RequestBody AssembleGenerale assembleGenerale ){
        assembleGenerale.setId(id);
        return new  ResponseEntity<AssembleGenerale>(AssembleGeneraleService.ajouterAssembleGenerale(assembleGenerale), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public  ResponseEntity<AssembleGenerale> deleteAssembleGenerale(@PathVariable Long id ){
        AssembleGeneraleService.deleteAssembleGenerale(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
