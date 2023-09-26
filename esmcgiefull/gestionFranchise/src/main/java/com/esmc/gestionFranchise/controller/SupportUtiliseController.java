package com.esmc.gestionFranchise.controller;

import com.esmc.gestionFranchise.entities.SupportUtilise;
import com.esmc.gestionFranchise.entities.organev2.AgentPoste;
import com.esmc.gestionFranchise.servicesInterface.SupportUtiliseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utiles.DataFormatter;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;


@RestController
@RequestMapping(value = "api/SupportUtilise")
public class SupportUtiliseController extends DataFormatter<SupportUtilise> {

    @Autowired
    private SupportUtiliseService supportUtiliseService ;


    @GetMapping("/listall")
    public Object list_intervenant_agent(){
        try {
            return  renderDataArray(true, supportUtiliseService.getSupportUtilise(),"list of support ");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }

    }

    @GetMapping("/listByTdep/idTdep/{idTep}")
    public Object listByTdep(@PathVariable Long idTep){
        try {
            return  renderDataArray(true, supportUtiliseService.getByTdep(idTep),"list of support ");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }

    }


    @PostMapping("/save")
    public Object ajouterSupportUtilise(@RequestBody() SupportUtilise data){
        try {
            return  renderData(true, supportUtiliseService.ajouterSupportUtilise(data.getTableDescriptionEp().getId(),data),"Create ");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }
    }

    @GetMapping(value = "/get/{id}")
    public ResponseEntity<SupportUtilise> getSupportUtilisebyId(@PathVariable Long id){
        return new ResponseEntity<>(   supportUtiliseService.getSupportUtilisebyId(id),HttpStatus.OK);
    }
/*    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<SupportUtilise> deleteSupportUtilise(@PathVariable Long id){
        supportUtiliseService.deleteSupportUtilise(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }*/

    @PostMapping("/delete/{id}")
    public Object deleteSupportUtilise(@PathVariable Long id){
        try {
            return  renderData(true, supportUtiliseService.getSupportUtilisebyId(id),"Delete successful ");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }
    }
}
