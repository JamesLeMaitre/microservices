package com.esmc.gestionCertification.controller;

import com.esmc.gestionCertification.entities.TypeChargement;
import com.esmc.gestionCertification.services.TypeChargementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utiles.DataFormatter;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

@RestController
@RequestMapping("api/typeChargement/")

public class TypeChargementController extends DataFormatter<TypeChargement> {
    @Autowired
    private TypeChargementService typeChargementService;



    @PostMapping("save")
    public Object addAffectation(@RequestBody TypeChargement typeChargement) {

        try {
            return renderData(true, typeChargementService.ajouterTypeChargement(typeChargement), "Opperation Successiful");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while procssing" ,exceptionAsString);
        }
    }

    @GetMapping("listAll")
    public Object getTypeChargement() {

        try {
            return renderDataArray(true, typeChargementService.getTypeChargement(), "Opperation Successiful");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while procssing" ,exceptionAsString);
        }

    }

    @GetMapping("id/{id}")
    public Object getTypeChargementById(@PathVariable Long id) {

        try {
            return renderData(true,typeChargementService.getTypeChargementbyId(id), "Opperation Successiful");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while procssing" ,exceptionAsString);
        }
    }


    @PutMapping("update/{id}")
    public Object updateSalle(@PathVariable Long id, @RequestBody TypeChargement typeChargement ) {

        try {
            typeChargement.setId(id);
            return renderData(true,  typeChargementService.ajouterTypeChargement(typeChargement), "Opperation Successiful");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while procssing" ,exceptionAsString);
        }
    }


    @DeleteMapping("delete/{id}")
    public  ResponseEntity<TypeChargement> deleteTypeChargement(@PathVariable Long id ) {
        typeChargementService.deleteTypeChargement(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
