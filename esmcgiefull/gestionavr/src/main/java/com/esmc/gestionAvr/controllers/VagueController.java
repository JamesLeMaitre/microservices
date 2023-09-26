package com.esmc.gestionAvr.controllers;
import com.esmc.gestionAvr.entities.Vague;
import com.esmc.gestionAvr.inputs.VagueInput;
import com.esmc.gestionAvr.servicesInterfaces.VagueServiceInterface;

import com.esmc.models.Formatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import utiles.DataFormatter;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
@RestController
@RequestMapping("api/vague/request")
public class VagueController extends DataFormatter<Vague> {

    @Autowired
    private VagueServiceInterface vagueServiceInterface;

    @PostMapping("add")
    public Object createVague(@RequestBody() VagueInput data){
        try {
        if(vagueServiceInterface.getVagueByLabel(data.getLabel())==null){
            return  renderData(true, vagueServiceInterface.createVague(data),"Create vague");
        }
        return  renderStringData(false,"Error while procssing" ,"Vague label already exists");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while procssing" ,exceptionAsString);
        }
    }


    @PutMapping(value = "edit/{id}")
    public Object updateVague(@PathVariable Long id, @RequestBody VagueInput data) {
        try {
        if( vagueServiceInterface.getVagueById(id)==null){
            return  renderStringData(false,"Error while procssing" ,"Vague item not found");
        }
        if(data.getLabel() != null &&  vagueServiceInterface.getVagueByLabel(data.getLabel())!=null){
            return  renderStringData(false,"Error while procssing" ,"Vague label already exists");
        }
        return  renderData(true, vagueServiceInterface.updateVague(id,data),"update vague");
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

        List<Vague> items = vagueServiceInterface.getAll();
        return  renderDataArray(true,items,"vague list");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while procssing" ,exceptionAsString);
        }

    }

    @GetMapping("by/label/{label}")
    public Object getVagueByLabe(@PathVariable("label") String label){
        try {

        Vague item = vagueServiceInterface.getVagueByLabel(label);
        if(item == null){
            return  renderStringData(false,"Error while processing" ,"Vague item not found");
        }
        return  renderData(true,item,"vague by label");
    } catch (Exception e) {
        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw));
        String exceptionAsString = sw.toString();
        return  renderStringData(false,"Error while procssing" ,exceptionAsString);
    }
    }

    @GetMapping("by/id/{id}")
    public Object getVagueById(@PathVariable("id") Long id){

        try {
        Vague item = vagueServiceInterface.getVagueById(id);
        if(item == null){
            return  renderStringData(false,"Error while processing" ,"Vague item not found");
        }
        return  renderData(true,item,"vague by id");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while procssing" ,exceptionAsString);
        }
    }

    @GetMapping("by/promotion/{id}")
    public Object  getListByPromotion(@PathVariable("id") Long id){
        try {
        List<Vague> items = vagueServiceInterface.getListByPromotion(id);
        return  renderDataArray(true,items,"vague by id");
    } catch (Exception e) {
        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw));
        String exceptionAsString = sw.toString();
        return  renderStringData(false,"Error while procssing" ,exceptionAsString);
    }
    }

    @GetMapping("activate/byId/{id}")
    public Object activateVagueById(@PathVariable("id") Long id){
        try {
        Vague item = vagueServiceInterface.getVagueById(id);
        if(item == null){
            return  renderStringData(false,"Error while processing" ,"Vague item not found");
        }
        Vague vagueActivated = vagueServiceInterface.activateVagueById(id);
        return  renderData(true,item,"vague activate by id");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while procssing" ,exceptionAsString);
        }
    }

    @GetMapping("convert/bci-ban/{amount}")
    public Object convertBanToBci(@PathVariable("amount") Double amount){

        try {
        Vague item = vagueServiceInterface.getActiveVague();
        if(item == null){
            return  renderStringData(false,"Error while processing" ,"No Active vague found");
        }
        String result = vagueServiceInterface.convertBciBan(amount)+"";
        return  renderStringData(true,result,"data converted");
    } catch (Exception e) {
        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw));
        String exceptionAsString = sw.toString();
        return  renderStringData(false,"Error while procssing" ,exceptionAsString);
    }
    }

    @GetMapping("convert/ban-bci/{amount}")
    public Object convertBciToan(@PathVariable("amount") Double amount){
        try {
        Vague item = vagueServiceInterface.getActiveVague();
        if(item == null){
            return  renderStringData(false,"Error while processing" ,"No Active vague found");
        }
        String result = vagueServiceInterface.convertBanBci(amount)+"";
        return  renderStringData(true,result,"data converted");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while procssing" ,exceptionAsString);
        }
    }



}