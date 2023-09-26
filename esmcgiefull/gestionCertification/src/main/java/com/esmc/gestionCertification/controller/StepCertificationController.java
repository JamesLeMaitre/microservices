package com.esmc.gestionCertification.controller;

import com.esmc.gestionCertification.entities.StepCertification;
import com.esmc.gestionCertification.input.StepInput;
import com.esmc.gestionCertification.repository.StepCertificationRepository;
import com.esmc.gestionCertification.services.StepCertificationService;
import org.springframework.web.bind.annotation.*;
import utiles.DataFormatter;
import static constants.ControllerMessage.*;

import java.io.PrintWriter;
import java.io.StringWriter;

@RestController
@RequestMapping("api/stepCertification/")
public class StepCertificationController extends DataFormatter<StepCertification> {
    private final StepCertificationService stepCertificationService;


    public StepCertificationController(StepCertificationService stepCertificationService
                                        ) {
        this.stepCertificationService = stepCertificationService;

    }

    @PostMapping("add")
    public Object create(@RequestBody() StepCertification data){
        try {
            StepCertification step  = stepCertificationService.get3Id(data.getIdDetailAgr(),data.getIdDetailAgrFranchiser(),data.getIdPoste());

            if(step != null){
                StepCertification step01 = stepCertificationService.getById(step.getId());
                System.out.println(step01);
                return  renderData(true, stepCertificationService.update(step01.getId(),data),SUCCESS_MESSAGE);
            } else {
               /// System.out.println(stepCertificationService.save(data));
                return  renderData(true, stepCertificationService.save(data),SUCCESS_MESSAGE);

            }


        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,ERROR_PROCESSING_MESSAGE ,exceptionAsString);
        }
    }

    @PostMapping("getByIds")
    public Object create(@RequestBody() StepInput data){
        try {
            return  renderData(true, stepCertificationService.get3Id(data.getIdDetailAgr(),data.getIdDetailAgrFranchiser(),data.getIdPoste()),SUCCESS_MESSAGE);
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,ERROR_PROCESSING_MESSAGE ,exceptionAsString);
        }
    }

    @PutMapping(value = "edit/{id}")
    public Object update(@PathVariable Long id, @RequestBody StepCertification data) {
        try {
            if( stepCertificationService.getById(id)==null){
                return  renderStringData(false,ERROR_PROCESSING_MESSAGE ,ITEM_NOT_FOUND);
            }
            return  renderData(true, stepCertificationService.update(id,data),SUCCESS_MESSAGE);
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }
    }

    @DeleteMapping("delete/{id}")
    public Object delete(@PathVariable("id") Long id){
        try {
            StepCertification item = stepCertificationService.getById(id);
            if(item == null){
                return  renderStringData(false,"Error while processing" ,"item not found");
            }
            stepCertificationService.delete(id);
            return  renderStringData(true,"Delete successfully","Done");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while procssing" ,exceptionAsString);
        }
    }
}
