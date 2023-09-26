package com.esmc.gestionAvr.controllers;

import com.esmc.gestionAvr.entities.Lettre;
import com.esmc.gestionAvr.servicesInterfaces.LettreServicesInterfaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/lettres/")

public class LettreController {

    @Autowired
    private LettreServicesInterfaces lettreServicesInterfaces;

    /*
     * Function to save Lettre
     * call the route save
     * */
    @GetMapping(value = "save")
    public Lettre saveLettre(@RequestBody Lettre lettre){
        return lettreServicesInterfaces.addLettre(lettre);
    }

    /*
     * Function to edit Lettre
     * call the route edit and add the id of the object
     * */
    @GetMapping(value = "edit/{id}")
    public ResponseEntity<Lettre> updateLettre(@PathVariable Long id, @RequestBody Lettre l){
        return new ResponseEntity<Lettre>(lettreServicesInterfaces.updateLettre(id, l), HttpStatus.OK);
    }

    /*
     * Function to delete Lettre
     * call the route delete and add the id of the object
     *
     * */
    @DeleteMapping(value = "delete/{id}")
    public void deleteLettre(@PathVariable Long id) {
        lettreServicesInterfaces.deleteLettre(id);
    }


    /*
     * Function to get all Lettre
     * call the route list
     * */
    @GetMapping(value = "list")
    public List<Lettre> allLetres(){
        return lettreServicesInterfaces.listLettres();
    }


}
