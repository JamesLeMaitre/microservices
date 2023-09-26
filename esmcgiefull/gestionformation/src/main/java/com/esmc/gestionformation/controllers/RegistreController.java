package com.esmc.gestionformation.controllers;

import com.esmc.gestionformation.entities.Registre;
import com.esmc.gestionformation.serviceinterfaces.RegistreServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import utiles.DataFormatter;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

@RestController
@RequestMapping("api/registry/")
public class RegistreController extends DataFormatter<Registre> {
    @Autowired
    private RegistreServiceInterface registreServiceInterface;

    @PostMapping("initRegistre")
    public Object save(@RequestBody String data) {
        try {
            return renderData(true, registreServiceInterface.initRegistre(data), "Operation Successful");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }
    }

    @GetMapping("getHistorizes/idAffectionSalle/{idFormation}")
    public Object getHistorizes(@PathVariable("idFormation") Long id) {
        try {
            List<Registre> items = registreServiceInterface.allRegistreFull(id);
            if(items.isEmpty()){
                return renderStringData(false, "", "No journey ended!");
            }
            return renderDataArray(true, registreServiceInterface.allRegistreFull(id), "Operation Successful");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }
    }



}
