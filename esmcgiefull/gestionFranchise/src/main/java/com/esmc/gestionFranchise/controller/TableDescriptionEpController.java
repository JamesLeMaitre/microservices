package com.esmc.gestionFranchise.controller;

import com.esmc.gestionFranchise.entities.TableDescriptionEp;
import com.esmc.gestionFranchise.entities.organev2.Poste;
import com.esmc.gestionFranchise.servicesInterface.TableDescriptionEpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utiles.DataFormatter;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
@RestController
@RequestMapping(value = "api/TableDescriptionEp")
public class TableDescriptionEpController extends DataFormatter<TableDescriptionEp> {

    @Autowired
    private TableDescriptionEpService tableDescriptionEpService;

    @GetMapping("/listall")
    public Object getTableDescriptionEp(){
        try {
            List<TableDescriptionEp> items = tableDescriptionEpService.getTableDescriptionEp();
            return  renderDataArray(true,items,"list of element");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }
    }
/*
    @GetMapping(value = "/listbymanuel/{id}")
    public ResponseEntity<List<TableDescriptionEp>> getTableDescriptionEpbyManuel(@PathVariable Long id){
        return new ResponseEntity<List<TableDescriptionEp>>(tableDescriptionEpService.getTableDescriptionEpbyManuel(id), HttpStatus.OK);
    }*/

    @GetMapping("/listbymanuel/{id}")
    public Object getTableDescriptionEpbyManuel(@PathVariable Long id){
        try {
            List<TableDescriptionEp> items = tableDescriptionEpService.getTableDescriptionEpbyManuel(id);
            return  renderDataArray(true,items,"list of element");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }
    }


    @PostMapping("/save")
    public Object ajouterTableDescriptionEp(@RequestBody TableDescriptionEp tableDescriptionEp){
        try {
            TableDescriptionEp items = tableDescriptionEpService.ajouterTableDescriptionEp(tableDescriptionEp);
            return  renderData(true,items,"Save successfully");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }
    }

    @GetMapping(value = "/get/{id}")
    public ResponseEntity<TableDescriptionEp> getTableDescriptionEpbyId(@PathVariable Long id){
        return new ResponseEntity<>(tableDescriptionEpService.getTableDescriptionEpbyId(id),HttpStatus.OK);
    }
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<TableDescriptionEp> deleteTableDescriptionEp(@PathVariable Long id){
        tableDescriptionEpService.deleteTableDescriptionEp(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/getTableDescriptionEp/{id}")
    public List<TableDescriptionEp> listTableDescriptionEp(@PathVariable Long id) {
        return tableDescriptionEpService.listTableDescriptionEp(id);
    }

}
