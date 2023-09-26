package com.esmc.gestionCertification.controller;

import com.esmc.gestionCertification.entities.AppelCandidature;
import com.esmc.gestionCertification.entities.CheckTraitement;
import com.esmc.gestionCertification.input.StepInput;
import com.esmc.gestionCertification.services.CheckTraitementService;
import com.esmc.input.CheckTraitementInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import utiles.DataFormatter;

import java.io.PrintWriter;
import java.io.StringWriter;
import static constants.ControllerMessage.*;

@RestController
@RequestMapping("api/checkTraitement/")
public class CheckTraitementController extends DataFormatter<CheckTraitement> {
    @Autowired
    private CheckTraitementService checkTraitementService;

    @PostMapping("save")
    public Object savecheck(@RequestBody CheckTraitementInput checkTraitement) {
        try {
            return renderData(true, checkTraitementService.saveStatus(checkTraitement), SUCCESS_MESSAGE);
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,ERROR_MESSAGE ,exceptionAsString);
        }
    }

    @PostMapping("getCurrent")
    public Object getCurrent(@RequestBody StepInput data) {

        try {
            CheckTraitement traitement = checkTraitementService.getCurrent(
                    data.getIdDetailAgrFranchiser(),
                    data.getIdDetailAgr(),
                    data.getCandidature(),
                    data.getIdPoste()
            );
            if(traitement == null){
                return renderStringData(false,ITEM_NOT_FOUND , ERROR_MESSAGE);
            }
            return renderData(true,traitement,SUCCESS_MESSAGE);

        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,ERROR_PROCESSING_MESSAGE ,exceptionAsString);
        }
    }
}
