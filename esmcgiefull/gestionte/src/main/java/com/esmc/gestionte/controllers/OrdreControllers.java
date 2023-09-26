package com.esmc.gestionte.controllers;

import com.esmc.gestionte.entities.Ordre;
import com.esmc.gestionte.exceptions.Exceptions;
import com.esmc.gestionte.serviceinterface.OrdreService;
import com.esmc.gestionte.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("ordres/")
public class OrdreControllers {

    @Autowired
    private OrdreService ordreService;

    @GetMapping("getAll")
    public List<Ordre> getOrdre(){
        return  ordreService.getOrdre();
    }

    @PostMapping("save")
    public ResponseEntity<?> addNewOrdre(@RequestBody Ordre ordre){
        try {
            ordreService.addNewOrdre(ordre);
            return new ResponseEntity<>(Util.SUCCES_MESSAGE, HttpStatus.OK);
        } catch (Exceptions e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }

    }

    @PutMapping("update/{id}")
    public ResponseEntity<?> updateOrdre(@PathVariable("id") Long id,@RequestBody Ordre ordre ){
        try {
            ordreService.updateOrdre(id,ordre);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exceptions e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteOrdre(@PathVariable ("id") Long id){
        try {
            ordreService.deleteOrdre(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exceptions e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable ("id") Long id){
        try {
            ordreService.getById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exceptions e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
        }
    }
}
