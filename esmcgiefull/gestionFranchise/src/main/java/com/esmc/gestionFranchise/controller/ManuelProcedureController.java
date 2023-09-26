package com.esmc.gestionFranchise.controller;


import com.esmc.gestionFranchise.entities.ManuelProcedure;
import com.esmc.gestionFranchise.servicesInterface.ManuelProcedureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utiles.DataFormatter;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

@RestController
@RequestMapping(value = "api/ManuelProcedure")
public class ManuelProcedureController extends DataFormatter<ManuelProcedure> {
    @Autowired
    private ManuelProcedureService manuelProcedureService ;



    @GetMapping("/listall")
    public Object getmanuelProcedure(){
        try {
            List<ManuelProcedure> items = manuelProcedureService.getmanuelProcedure();
            return  renderDataArray(true,items,"list of element");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }
    }

    @GetMapping("/listbytache/{id}")
    public Object getmanuelProcedurebytache(@PathVariable Long id){
        try {
            List<ManuelProcedure> items =manuelProcedureService.getmanuelProcedurebyTache(id);
            return  renderDataArray(true,items,"list of element");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }
    }

/*    @GetMapping(value = "/listbytache/{id}")
    public ResponseEntity<List<ManuelProcedure>> getmanuelProcedurebytache(@PathVariable Long id){
        return new ResponseEntity<List<ManuelProcedure>>(manuelProcedureService.getmanuelProcedurebyTache(id), HttpStatus.OK);
    }*/


    @PostMapping("/save")
    public Object ajoutermanuelProcedure(@RequestBody ManuelProcedure manuelProcedure){
        try {
            ManuelProcedure items = manuelProcedureService.ajoutermanuelProcedure(manuelProcedure);
            return  renderData(true,items,"list of element");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }
    }

    @GetMapping(value = "/get/{id}")
    public ResponseEntity<ManuelProcedure> getmanuelProcedurebyId(@PathVariable Long id){
        return new ResponseEntity<>(manuelProcedureService.getmanuelProcedurebyId(id),HttpStatus.OK);
    }
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<ManuelProcedure> deletemanuelProcedure(@PathVariable Long id){
        manuelProcedureService.deletemanuelProcedure(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/getManuelProcedure/{id}")
    public ManuelProcedure listManuelProcedure(@PathVariable Long id) {
        return manuelProcedureService.listManuelProcedure(id);
    }


}
