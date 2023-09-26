package com.esmc.gestionformation.controllers;

import com.esmc.gestionformation.entities.SalleFormation;
import com.esmc.gestionformation.services.SalleFormationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import utiles.DataFormatter;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

import static constants.ControllerMessage.*;

@RestController
@RequestMapping("api/salleFormation/")
public class SalleFormationController extends DataFormatter<SalleFormation> {
    @Autowired
    private SalleFormationService salleFormationService;

    @PostMapping("add")
    public Object create(@RequestBody SalleFormation data) {
        try {
            return renderData(true, salleFormationService.create(data), SUCCESS_MESSAGE);
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,ERROR_PROCESSING_MESSAGE ,exceptionAsString);
        }
    }

    @GetMapping("list")
    public Object list() {

        try {
            return renderDataArray(true, salleFormationService.getAll(), SUCCESS_MESSAGE);
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,ERROR_PROCESSING_MESSAGE ,exceptionAsString);
        }
    }

    @PostMapping("getByTypeSalle/{id}")
    public Object getById(@PathVariable Long id) {
        try {
            List<SalleFormation> byIdSalle = salleFormationService.getByIdSalle(id);
            if(byIdSalle != null){
                return renderDataArray(true, salleFormationService.getByIdSalle(id), SUCCESS_MESSAGE);
            }
            return renderStringData(true, ERROR_MESSAGE, SUCCESS_MESSAGE);
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,ERROR_PROCESSING_MESSAGE ,exceptionAsString);
        }
    }
}
