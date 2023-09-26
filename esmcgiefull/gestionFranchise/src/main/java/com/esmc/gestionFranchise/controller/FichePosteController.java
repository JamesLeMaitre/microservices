package com.esmc.gestionFranchise.controller;


import com.esmc.gestionFranchise.entities.FichePoste;

import com.esmc.gestionFranchise.servicesInterface.FichePosteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utiles.DataFormatter;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
@RestController
@RequestMapping(value = "api/FichePoste/")

public class FichePosteController extends DataFormatter<FichePoste>  {

    @Autowired
    private FichePosteService fichePosteService ;

    @GetMapping("list")
    public Object List(){
        try {
            List<FichePoste> items = fichePosteService.getFichePoste();
            return  renderDataArray(true,items,"list of element");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while procssing" ,exceptionAsString);
        }
    }


    @PostMapping("add")
    public Object create(@RequestBody FichePoste data){
        try {
            return  renderData(true, fichePosteService.ajouterFichePoste(data),"Create ");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }

    }

    @PutMapping(value = "edit/{id}")
    public Object update(@PathVariable Long id, @RequestBody FichePoste data) {
        try {
            if( fichePosteService.getById(id)==null){
                return  renderStringData(false,"Error while procssing" ,"item not found");
            }
            return  renderData(true, fichePosteService.modifierFichePoste(id,data),"update done successfully");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while procssing" ,exceptionAsString);
        }
    }

    @DeleteMapping("delete/{id}")
    public Object delete(@PathVariable("id") Long id){
        try {
            FichePoste item = fichePosteService.getById(id);
            if(item == null){
                return  renderStringData(false,"Error while processing" ,"item not found");
            }
            fichePosteService.deleteFichePoste(id);
            return  renderStringData(true,"Delete successfully","Done");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while procssing" ,exceptionAsString);
        }
    }
    @GetMapping(value = "/get/{id}")
    public ResponseEntity<FichePoste> getFichePostebyId(@PathVariable Long id){

        return new ResponseEntity<>(fichePosteService.getById(id),HttpStatus.OK);

    }

    @GetMapping("listbyagent/{id}")
    public Object List(@PathVariable Long id){
        try {
            List<FichePoste> items = fichePosteService.findFichePosteByPost(id);
            return  renderDataArray(true,items,"list of element");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }
    }






}
