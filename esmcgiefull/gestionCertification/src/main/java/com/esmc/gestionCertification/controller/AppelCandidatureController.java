package com.esmc.gestionCertification.controller;



import com.esmc.gestionCertification.entities.AppelCandidature;
import com.esmc.gestionCertification.services.AppelCandidatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utiles.DataFormatter;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;


@RestController
@RequestMapping("api/appelCandidature/")
public class AppelCandidatureController extends DataFormatter<AppelCandidature> {
    @Autowired
    private AppelCandidatureService appelCandidatureService;


    @PostMapping("save")
    public Object saveAppelCandidature(@RequestBody AppelCandidature AppelCandidature) {

        try {
            return renderData(true, appelCandidatureService.ajouterAppelCandidature(AppelCandidature), "Operation Successful");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }
    }

    @GetMapping("listAll")
    public Object getAppelCandidature() {

        try {
            return renderDataArray(true, appelCandidatureService.getAppelCandidature(), "Operation Successful");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while procssing" ,exceptionAsString);
        }

    }

    @GetMapping("id/{id}")
    public Object getAffectationById(@PathVariable Long id) {

        try {
            return renderData(true, appelCandidatureService.getAppelCandidaturebyId(id), "Opperation Successiful");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while procssing" ,exceptionAsString);
        }
    }
    @PutMapping("update/{id}")
    public Object updateAppelCandidature(@PathVariable Long id, @RequestBody AppelCandidature appelCandidature ) {

        try {
             appelCandidature.setId(id);
            return renderData(true,  appelCandidatureService.ajouterAppelCandidature(appelCandidature), "Opperation Successiful");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while procssing" ,exceptionAsString);
        }
    }

    @DeleteMapping("delete/{id}")
    public  ResponseEntity<AppelCandidature> deleteAppelCandidature(@PathVariable Long id ) {
        appelCandidatureService.deleteAppelCandidature(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("publier/ac/{idAc}")
    public Object publicationAppelCandidaure(@PathVariable Long idAc) {

        DataFormatter<Object> affec=new DataFormatter<>();
        AppelCandidature items=appelCandidatureService.publicationAppelCandidaure(idAc);
        try {
            return affec.renderData(true, items, "Operation Successful");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }

    }

    @GetMapping("list/detail_agr/{idAc}")
    public Object listAppelCandidaturebyId(@PathVariable Long idAc) {

        List<AppelCandidature> items=appelCandidatureService.listAppelCandidaturebyId(idAc);
        try {

            if (items.isEmpty()) {
                return renderStringData(false, "", "Data not found");
            }

            return renderDataArray(true, items, "Operation Successful");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }

    }


}
