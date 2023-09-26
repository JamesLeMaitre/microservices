package com.esmc.gestionFranchise.controller;

import com.esmc.gestionFranchise.entities.Calendrier;
import com.esmc.gestionFranchise.servicesInterface.CalendrierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/Calendrier")
public class CalendrierController {

    @Autowired
    private CalendrierService CalendrierService;

    @PostMapping("/save")
    public ResponseEntity<Calendrier> saveCalendrier(@RequestBody Calendrier Calendrier){
        return new ResponseEntity<Calendrier>(CalendrierService.ajouterCalendrier(Calendrier), HttpStatus.OK);
    }

    @GetMapping("/listAll")
    public ResponseEntity<List<Calendrier>>getCalendrier(){
        return new ResponseEntity<List<Calendrier>>(CalendrierService.getCalendrier(),HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Calendrier> Calendrier(@PathVariable("id") Long id){
        return new ResponseEntity<Calendrier>( CalendrierService.getCalendrierbyId(id),HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Calendrier> updateCalendrier(@PathVariable("id") Long id , @RequestBody Calendrier Calendrier ){
        Calendrier.setId(id);
        return new  ResponseEntity<Calendrier>(CalendrierService.ajouterCalendrier(Calendrier), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public  ResponseEntity<Calendrier> deleteCalendrier(@PathVariable Long id ){
        CalendrierService.deleteCalendrier(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
