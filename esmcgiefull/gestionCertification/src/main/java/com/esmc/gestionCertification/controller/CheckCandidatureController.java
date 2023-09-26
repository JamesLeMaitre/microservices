package com.esmc.gestionCertification.controller;

import com.esmc.gestionCertification.entities.Affectation;
import com.esmc.gestionCertification.entities.CheckCandidature;
import com.esmc.gestionCertification.repository.CheckCandidatureRepository;
import com.esmc.gestionCertification.services.CheckChargementService;
import com.esmc.models.Ksu;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import utiles.DataFormatter;

import java.io.PrintWriter;
import java.io.StringWriter;

@RestController
@RequestMapping("api/checkCandidature/")
public class CheckCandidatureController extends DataFormatter<CheckCandidature> {

    @Autowired
    private CheckChargementService checkChargementService;
    @Autowired
    private CheckCandidatureRepository checkCandidatureRepository;

    @GetMapping("getKSU/{id}")
    public Object getKsu(@PathVariable Long id) {
        DataFormatter<Ksu> dataFormatter = new DataFormatter<>();
        try {
            return dataFormatter.renderData(true, checkChargementService.getKsu(id), "Operation Successful");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }
    }

    @PostMapping("checkCandidature")
    public Object postCan(@RequestBody CheckCandidature checkCandidature) {
        DataFormatter<JSONObject> dataFormatter = new DataFormatter<>();
        CheckCandidature candidature = checkChargementService.save(checkCandidature);
        Object postePostuler = checkChargementService.postePostule(checkCandidature.getIdPoste());
        Object ksuInfo = checkChargementService.getKsu(checkCandidature.getIdDetailAgr());
        try {
            if(candidature != null){
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("candidature", candidature);
                jsonObject.put("poste",postePostuler);
                jsonObject.put("ksu",ksuInfo);
                return dataFormatter.renderData(true, jsonObject, "Application status sent successfully" );
            }
            return renderStringData(false, "Appel à Candidature non publiée !", "No application sent");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }
    }
}
