package com.esmc.gestionCertification.controller;


import com.esmc.gestionCertification.entities.Affectation;
import com.esmc.gestionCertification.feign.FormationRestClient;
import com.esmc.gestionCertification.input.AffectationInput;
import com.esmc.gestionCertification.services.AffectationService;
import com.esmc.models.Code;
import net.minidev.json.JSONObject;
import org.springframework.web.bind.annotation.*;
import utiles.DataFormatter;

import java.io.PrintWriter;
import java.io.StringWriter;



@RestController
@RequestMapping("api/affectation/")
public class AffectationController extends DataFormatter<Affectation> {

    private final AffectationService affectationService;
    private final FormationRestClient formationRestClient;


    
    public AffectationController(AffectationService affectationService, FormationRestClient formationRestClient) {
        this.affectationService = affectationService;
        this.formationRestClient = formationRestClient;
    }

    @PostMapping("save")
    public Object addAffectation(@RequestBody AffectationInput affectation) {

        try {
            Affectation  affectation1= affectationService.ajouterAffectation(affectation);
            if (affectation1 !=null){
                return renderData(true, affectation1, "Operation has been done");
               }

           // return  renderStringData(false,"You've already ("+ affectationService.countAlready(affectation) +")saved this affection","Operation has been done");

            return renderStringData(false, "", "Cet DetailAgr n'est pas franchiser");

        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }
    }

    @GetMapping("listAll")
    public Object listSalle() {
     try {
        return renderDataArray(true, affectationService.getAffectation(), "Operation Successful");
       } catch (Exception e) {
         StringWriter sw = new StringWriter();
           e.printStackTrace(new PrintWriter(sw));
           String exceptionAsString = sw.toString();
           return  renderStringData(false,"Error while processing" ,exceptionAsString);
       }
    }

    @GetMapping("id/{id}")
    public Object getAffectationById(@PathVariable Long id) {
        try {
            return renderData(true, affectationService.getAffectationbyId(id), "Operation Successful");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }
    }


/*    @PutMapping("update/{id}")
    public Object updateSalle(@PathVariable Long id, @RequestBody AffectaionInput Affectation ) {
        try {
          //  Affectation.setId(id);
            return renderData(true,  affectationService.ajouterAffectation(Affectation), "Operation Successful");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while procssing" ,exceptionAsString);
        }
    }*/

/*    @DeleteMapping("delete/{id}")
    public  ResponseEntity<Affectation> deleteAffectation(@PathVariable Long id ) {
        affectationService.deleteAffectation(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }*/

    @DeleteMapping("delete/{id}")
    public Object deleteAffectation(@PathVariable Long id) {
        try {
            affectationService.deleteAffectation(id);
            return renderStringData(true, "Is Zoooooo", "Operation Successful");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }
    }

    @GetMapping("list/{id}/candidature")
    public Object listAffectationParCandidature(@PathVariable Long id) {
        try {
            return renderDataArray(true, affectationService.listAffectationCandidature(id), "Operation Successful");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }
    }

    @GetMapping("list/ac/{idAc}/dAgr/{dAgr}")
    public Object listposteParCandidature(@PathVariable("idAc") Long idAc,@PathVariable("dAgr")Long idDetAgr) {

        DataFormatter<JSONObject> affec=new DataFormatter<JSONObject>();
        Object items=affectationService.listPosteByAppelCadidature(idAc,idDetAgr);
        Affectation affectation = affectationService.getLibelle(idAc);
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("poste", items);
            jsonObject.put("candidature",affectation);
            return affec.renderData(true, jsonObject, "Well done" );
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }

    }

    @GetMapping("getKSU/{idDetailAgr}")
    public Object getKSU(@PathVariable Long idDetailAgr) {
       DataFormatter<Object> dataFormatter =new DataFormatter<>();
        Object items=affectationService.getKSU(idDetailAgr);
        try {
            return dataFormatter.renderData(true, items, "Well done" );
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }

    }


}
