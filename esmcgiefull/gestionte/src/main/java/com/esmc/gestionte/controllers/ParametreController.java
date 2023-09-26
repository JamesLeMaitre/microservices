package com.esmc.gestionte.controllers;

import com.esmc.gestionte.entities.Parametre;
import com.esmc.gestionte.exceptions.Exceptions;
import com.esmc.gestionte.serviceinterface.ParametreService;
import com.esmc.gestionte.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/parametre/")
public class ParametreController {


    @Autowired
    private ParametreService parametreService;

    @GetMapping("getAll")
    public List<Parametre> getParametre(){
        return  parametreService.getParametre();
    }

    @PostMapping("save")
    public ResponseEntity<?> addNewParametre(@RequestBody Parametre parametre){
        try {
            parametreService.addNewParametre(parametre);
            return new ResponseEntity<>(Util.SUCCES_MESSAGE, HttpStatus.OK);
        } catch (Exceptions e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }

    }

    @PutMapping("update/{id}")
    public ResponseEntity<?> updateParametre(@PathVariable("id") Long id, @RequestBody Parametre parametre ){
        try {
            parametre.setId(id);
            parametreService.updateParametre(id,parametre);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exceptions e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteParametre(@PathVariable ("id") Long id){
        try {
            parametreService.deleteParametre(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exceptions e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable ("id") Long id){
        try {
            parametreService.getById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exceptions e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/current")
    public Parametre getParametreEsmc() {
        return parametreService.getParametreEsmc();
    }

}
