package com.esmc.gestionAchatFranchise.controllers;

import com.esmc.gestionAchatFranchise.entities.PosteFranchise;
import com.esmc.gestionAchatFranchise.servicesinterfaces.PosteInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import utiles.DataFormatter;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;


@RestController
@RequestMapping(value="/api/poste")
public class PosteController  extends DataFormatter<PosteFranchise> {
    @Autowired
    private PosteInterface commonInterface;

    @PostMapping("add")
    public Object create(@RequestBody() PosteFranchise data){
        try {
            return  renderData(true, commonInterface.create(data),"Create ");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while procssing" ,exceptionAsString);
        }

    }


    @PutMapping(value = "edit/{id}")
    public Object update(@PathVariable Long id, @RequestBody PosteFranchise data) {
        try {
            if( commonInterface.getById(id)==null){
                return  renderStringData(false,"Error while procssing" ,"item not found");
            }
            return  renderData(true, commonInterface.update(id,data),"update done successfully");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while procssing" ,exceptionAsString);
        }
    }

    @GetMapping("list")
    public Object List(){
        try {
            List<PosteFranchise> items = commonInterface.getAll();
            return  renderDataArray(true,items,"list of element");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while procssing" ,exceptionAsString);
        }
    }

    @GetMapping("count")
    public Object count(){
        try {
            return  renderStringData(true,commonInterface.getCountAll()+"","list of element");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while procssing" ,exceptionAsString);
        }
    }


    @GetMapping("by/id/{id}")
    public Object getById(@PathVariable("id") Long id){
        try {
            PosteFranchise item = commonInterface.getById(id);
            if(item == null){
                return  renderStringData(false,"Error while processing" ,"item not found");
            }
            return  renderData(true,item,"Element found");
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
            PosteFranchise item = commonInterface.getById(id);
            if(item == null){
                return  renderStringData(false,"Error while processing" ,"item not found");
            }
            commonInterface.delete(id);
            return  renderStringData(true,"Delete successfully","Done");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while procssing" ,exceptionAsString);
        }
    }

}
