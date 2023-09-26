package com.esmc.gestionAgr.controllers;

import com.esmc.gestionAgr.entities.Agr;
import com.esmc.gestionAgr.exceptions.Exceptions;
import com.esmc.gestionAgr.serviceinterfaces.AgrServiceInterface;
import com.esmc.gestionAgr.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "api/agrs/")
public class AgrController {

    @Autowired
    private AgrServiceInterface agrService;


    @GetMapping("getAll")
    public List<Agr> getAgr() {
        return agrService.getAgr();
    }

    @PostMapping("save")
    public Agr agrRepositoryregisterNewAgr(@RequestBody Agr agr) {
        return agrService.addNewAgr(agr);
    }


    @DeleteMapping(path = "delete/{agrId}")
    public ResponseEntity<?> deleteAgr(@PathVariable("agrId") Long agrId) {
        try {
            agrService.deleteAgr(agrId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exceptions e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @PutMapping("update/{id}")
    public ResponseEntity<?> updateOrdre(@PathVariable("id") Long id, @RequestBody Agr agr) {
        try {
            agrService.updateAgr(id, agr);
            return new ResponseEntity<>(Util.SUCCES_MESSAGE, HttpStatus.OK);
        } catch (Exceptions e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        try {
            agrService.getById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exceptions e) {
            return new ResponseEntity<>(Util.SUCCES_MESSAGE, HttpStatus.CONFLICT);
        }
    }
}
