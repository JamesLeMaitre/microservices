package com.esmc.gestionAchatFranchise.controllers;

import com.esmc.gestionAchatFranchise.entities.Canton;
import com.esmc.gestionAchatFranchise.services.CantonImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/api/canton")
public class CantonController {
    @Autowired
    private CantonImp cantonImp;

    @PostMapping("/add")
    public ResponseEntity<Canton> addCanton(@RequestBody Canton canton) {
        return new ResponseEntity<Canton>(cantonImp.addCanton(canton),  HttpStatus.CREATED);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Canton>> getAllCanton(){
        return new ResponseEntity<List<Canton>>(cantonImp.getCanton(), HttpStatus.OK);
    }

    @GetMapping("/search/{cantonId}")
    public Canton getCanton(@PathVariable("cantonId") Long cantonId) {
        return cantonImp.getCantonById(cantonId);
    }


    @PutMapping("/update/{cantonId}")
    public ResponseEntity<Canton> updateCanton(@PathVariable("cantonId") Long cantonId, @RequestBody Canton canton) {
        //canton.setId(cantonId);
        return new ResponseEntity<Canton>(cantonImp.updateCanton(cantonId,canton), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{cantonId}")
    public ResponseEntity<Canton> deleteCanton(@PathVariable Long cantonId) {
        cantonImp.deleteCanton(cantonId);
        return new ResponseEntity<>( HttpStatus.NO_CONTENT);
    }

    @GetMapping("/search/{id}/commune")
    public List<Canton> listCantonParCommune(@PathVariable Long id) {
        return cantonImp.listCommune(id);
    }


}
