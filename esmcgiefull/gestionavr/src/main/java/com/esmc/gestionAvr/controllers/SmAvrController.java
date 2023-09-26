package com.esmc.gestionAvr.controllers;

import com.esmc.gestionAvr.entities.SMAvr;
import com.esmc.gestionAvr.servicesInterfaces.SmAvrServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/smavrs/")
public class SmAvrController {
    @Autowired
    private SmAvrServiceInterface sMAvrServiceInterface;

    /*
     * Function to save SMAvr
     * call the route save
     * */
    @PostMapping(value = "save")
    public SMAvr ajouterSMAvr(@RequestBody SMAvr a) {
        return sMAvrServiceInterface.addSMAvr(a);
    }

    /*
     * Function to edit SMAvr
     * call the route edit and add the id of the object
     * */

    @GetMapping(value = "edit/{id}")
    public ResponseEntity<SMAvr> modifierSMAvrs(@PathVariable Long id, @RequestBody SMAvr s) {
        return new ResponseEntity<SMAvr>(sMAvrServiceInterface.updateSMAvr(id, s), HttpStatus.OK);
    }

    /*
     * Function to delete SMAvr
     * call the route delete and add the id of the object
     *
     * */

    @DeleteMapping(value = "delete/{id}")
    public void supprimerSMAvrs(@PathVariable Long id) {
        sMAvrServiceInterface.deleteSMAvr(id);
    }

    /*
     * Function to get all SMAvr
     * call the route list
     * */
    @GetMapping(value = "list")
    public List<SMAvr> listeSMAvrs() {
        return sMAvrServiceInterface.getAllSMAvr();
    }

    @GetMapping(value = "list/{id}/typesmavr")
    public List<SMAvr> listSMAvrByTypeSmAvr(@PathVariable Long id) {
        return sMAvrServiceInterface.listSMAvrByTypeSmAvr(id);
    }

    /*
     * Function to get SmAvr of a detailsAgr
     * call the route agr and add Id of detailsAgr
     * */
    /*@GetMapping(value = "list/{id}/supportMarchandEnchage")
    public List<SMAvr> listSmAvrBysupportMarchandEnchageId(@PathVariable Long id){
        return sMAvrServiceInterface.listSmAvrBysupportMarchandEnchageId(id);
    }*/


}

