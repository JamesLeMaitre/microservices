package com.esmc.gestionCertification.controller;

import com.esmc.gestionCertification.entities.PanierCertification;
import com.esmc.gestionCertification.input.PanierInput;
import com.esmc.gestionCertification.services.PanierCertificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import utiles.DataFormatter;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

import static constants.ControllerMessage.*;

@RestController
@RequestMapping("api/panierCertification/")
public class PanierCertificationController extends DataFormatter<PanierCertification> {

    @Autowired
    private PanierCertificationService panierCertificationService;

    @PostMapping("save")
    public Object addAffectation(@RequestBody PanierInput p) {
        try {
            boolean isLa = panierCertificationService.checkPanier(p);
            if(isLa){
                return renderData(true, panierCertificationService.ajouterPanierCertification(p), SUCCESS_MESSAGE);
            }
            return renderStringData(false, EXIST_MESSAGE, ERROR_MESSAGE);
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,ERROR_PROCESSING_MESSAGE ,exceptionAsString);
        }
    }

    @GetMapping("list/idDetailAgr/{idDetailAgr}/idPoste/{idPoste}")
    public Object listAffectationParCandidature(@PathVariable Long idDetailAgr, @PathVariable Long idPoste) {
        try {
            List<PanierCertification> items = panierCertificationService.listPanierCertification(idDetailAgr, idPoste);

            if (items.isEmpty()) {
                return renderStringData(false, "", DATA_NOT_FOUND);
            }

            return renderDataArray(true, items, SUCCESS_MESSAGE);
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,ERROR_PROCESSING_MESSAGE ,exceptionAsString);
        }
    }

    @GetMapping("by/idDetailAgr/{idDetailAgr}/idPoste/{idPoste}/{idDetailAgrFranchiser}")
    public Object getAffectationParCandidature(@PathVariable Long idDetailAgr, @PathVariable Long idPoste,@PathVariable Long idDetailAgrFranchiser) {
        try {
            PanierCertification items = panierCertificationService.getPanierCertification(idDetailAgr, idPoste,idDetailAgrFranchiser);

            if (items == null) {
                return renderStringData(false, "", DATA_NOT_FOUND);
            }

            return renderData(true, items, SUCCESS_MESSAGE);
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,ERROR_PROCESSING_MESSAGE ,exceptionAsString);
        }
    }


}
