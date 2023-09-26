package com.esmc.gestionFranchise.controller;

import com.esmc.gestionFranchise.entities.TableDescriptionEp;
import com.esmc.gestionFranchise.entities.Tache;
import com.esmc.gestionFranchise.entities.organev2.AgentPoste;
import com.esmc.gestionFranchise.servicesInterface.TacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utiles.DataFormatter;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

@RestController
@RequestMapping(value = "api/Tache")
public class TacheController extends DataFormatter<Tache> {
    @Autowired
    private TacheService tacheService;

    @GetMapping("/listall")
    public Object getTache(){
        try {
            List<Tache> items = tacheService.getTache();
            return  renderDataArray(true,items,"list of element");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }
    }

    @GetMapping(value = "/listbyfichedeposte/{id}")
    public ResponseEntity<List<Tache>> getTachebyficheposte(@PathVariable Long id){
        return new ResponseEntity<List<Tache>>(tacheService.getTachebyFichedePoste(id), HttpStatus.OK);
    }

    @PostMapping("/save")
    public Object ajouterTache(@RequestBody Tache tache){
        try {
            Tache items = tacheService.ajouterTache(tache);
            return  renderData(true,items,"list of element");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }
    }

/*    @GetMapping(value = "/get/{id}")
    public ResponseEntity<Tache> getTachebyId(@PathVariable Long id){
        return new ResponseEntity<>(tacheService.getTachebyId(id),HttpStatus.OK);
    }*/

    @GetMapping("/get/{id}")
    public Object getTachebyId(@PathVariable Long id){
        try {
            Tache items = tacheService.getTachebyId(id);
            return  renderData(true,items,"list of element");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }
    }
 /*   @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Tache> deleteTache(@PathVariable Long id){
        tacheService.deleteTache(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }*/

    @GetMapping("/getTache/{id}")
    public List<Tache> listTache(@PathVariable Long id) {
        return tacheService.listTache(id);
    }

    @PutMapping(value = "/edit/{id}")
    public Object update(@PathVariable Long id, @RequestBody Tache data) {
        try {
            if( tacheService.getTachebyId(id)==null){
                return  renderStringData(false,"Error while processing" ,"item not found");
            }
            return  renderData(true, tacheService.updatetache(id,data),"update done successfully");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }
    }

    @DeleteMapping("/delete/{id}")
    public Object deleteTache(@PathVariable("id") Long id){
        try {
            Tache item = tacheService.getTachebyId(id);
            if(item == null){
                return  renderStringData(false,"Error while processing" ,"item not found");
            }
            tacheService.deleteTache(id);
            return  renderStringData(true,"Delete successfully","Done");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }
    }


}
