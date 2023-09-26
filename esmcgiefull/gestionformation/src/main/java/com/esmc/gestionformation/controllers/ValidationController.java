package com.esmc.gestionformation.controllers;

import com.esmc.gestionformation.entities.Validation;
import com.esmc.gestionformation.inputs.ValidationInput;
import com.esmc.gestionformation.services.ValidataionService;
import org.springframework.web.bind.annotation.*;
import utiles.DataFormatter;

import java.io.PrintWriter;
import java.io.StringWriter;

@RestController
@RequestMapping("api/validation/")
public class ValidationController extends DataFormatter<Validation> {
    private final ValidataionService validataionService;

    public ValidationController(ValidataionService validataionService) {
        this.validataionService = validataionService;
    }

    @PostMapping("addCheckValidation")
    public Object addSalle(@RequestBody() Validation data) {

        try {
            Validation validation = validataionService.addValidation(data);
            if(validation == null){
                return renderStringData(false,"","Operation not done");
            }
            return renderData(true, validation, "Operation Successful");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }
    }

    @PostMapping("checkValidation")
    public Object listCodeByTypeAndSalle(@RequestBody() ValidationInput data) {

        try {
            Validation validation = validataionService.getValidation(data.getIdAgr(), data.getIdDetailAgrFranchiser(), data.getIdPoste());
            if(validation == null){
                return renderStringData(false,"","Operation not Done");
            }
            return renderData(true,validation,"Operation Done");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }
    }

}
