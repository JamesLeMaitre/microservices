package com.esmc.gestionCertification.controller;

import com.esmc.gestionCertification.entities.Chargement;
import com.esmc.gestionCertification.input.ChargementPostInput;
import com.esmc.gestionCertification.request.ChargementRequest;
import com.esmc.gestionCertification.services.ChargementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utiles.DataFormatter;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

import static constants.ControllerMessage.*;


@RestController
@RequestMapping("api/chargement/")

public class ChargementController extends DataFormatter<Chargement> {
    @Autowired
    private ChargementService chargementService;

//    @PostMapping("save")
//    public Object saveChargementAppelCandidature(@RequestBody Chargement chargement) {
//        try {
//            boolean res = chargementService.checkExist(chargement);
//            System.out.println("CHARGEMENT "+ res);
//            if(chargement.getIdPoste() == null && chargement.getDetailAgr() == null && chargement.getLibellePoste() == null){
//                return renderStringData(false, FIELDS_MESSAGE, SUCCESS_MESSAGE);
//            }
//            if(res){
//                return renderData(true, chargementService.ajouterChargementAppelCandidature(chargement), SUCCESS_MESSAGE);
//            }
//            return renderStringData(false, EXIST_MESSAGE, ERROR_MESSAGE);
//        } catch (Exception e) {
//            StringWriter sw = new StringWriter();
//            e.printStackTrace(new PrintWriter(sw));
//            String exceptionAsString = sw.toString();
//            return  renderStringData(false,ERROR_PROCESSING_MESSAGE ,exceptionAsString);
//        }
//    }

    @PostMapping("save")
    public Object addChargement(@RequestBody ChargementRequest chargementRequest) {
        try {
            boolean res = chargementService.checkExistNew(chargementRequest);
            System.out.println("CHARGEMENT "+ res);
            if(chargementRequest.getIdPoste() == null && chargementRequest.getDetailAgr() == null && chargementRequest.getLibellePoste() == null){
                return renderStringData(false, FIELDS_MESSAGE, SUCCESS_MESSAGE);
            }
            if(!res){
                return renderData(true, chargementService.addChargement(chargementRequest), SUCCESS_MESSAGE);
            }
            return renderStringData(false, EXIST_MESSAGE, ERROR_MESSAGE);
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,ERROR_PROCESSING_MESSAGE ,exceptionAsString);
        }
    }

    @PostMapping("getCurrentChargement")
    public Object getChargementCurrent(@RequestBody ChargementPostInput chargement) {
        try {
            Chargement res = chargementService.checkExistGet(chargement);

            if(res == null){
                return renderStringData(false, "", ERROR_MESSAGE);
            }
            return renderData(true, res, SUCCESS_MESSAGE);
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,ERROR_PROCESSING_MESSAGE ,exceptionAsString);
        }
    }

    @PostMapping("savePoste")
    public Object saveChargementPoste(@RequestBody ChargementPostInput chargement) {
        try {
            Chargement charge = chargementService.ajouterChargementPoste(chargement);
            boolean isLa = chargementService.checkExistPoste(chargement);
            if(charge == null){
                return renderStringData(false,"","Call of Application is Null");
            }
            if(!isLa){
                return renderData(true, charge, SUCCESS_MESSAGE);
            }
            return renderStringData(false, EXIST_MESSAGE, ERROR_MESSAGE);
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,ERROR_PROCESSING_MESSAGE ,exceptionAsString);
        }
    }

    @GetMapping("listAll")
    public Object getChargement() {

        try {
            return renderDataArray(true, chargementService.getChargement(), SUCCESS_MESSAGE);
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,ERROR_PROCESSING_MESSAGE ,exceptionAsString);
        }

    }

    @GetMapping("id/{id}")
    public Object getChargementById(@PathVariable Long id) {

        try {
            return renderData(true, chargementService.getChargementbyId(id), "Operation Successfully");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }
    }


    @PutMapping("update/{id}")
    public Object updateSalle(@PathVariable("id") Long id , @RequestBody Chargement chargement ) {

        try {
            chargement.setId(id);
            return renderData(true,  chargementService.ajouterChargementAppelCandidature(chargement), "Operation Successifully");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }
    }


    @DeleteMapping("delete/{id}")
    public  ResponseEntity<Chargement> deleteChargement(@PathVariable Long id ) {
        chargementService.deleteChargement(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @GetMapping("list/{id}/typeChargement")
    public Object listChargementParTypeChargement(@PathVariable Long id) {

        DataFormatter<Object> affec=new DataFormatter<>();
        Object items=chargementService.listTypeChargement(id);

        try {

            return affec.renderData(true, chargementService.listTypeChargement(id), "Operation Successifully");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }

    }

    @GetMapping("list/idDetailAgr/{id}")
    public Object listChargementParIdDetailChargment(@PathVariable Long id) {
        try {
            List<Chargement> items = chargementService.listChargementByIdDetailAgr(id);
            if (items.isEmpty()) {
                return renderStringData(false, "", "Data not found");
            }

            return renderDataArray(true,items, "Operation Successfully");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return renderStringData(false, "Error while processing", exceptionAsString);
        }
    }


        @GetMapping("list/{id}/candidature")
    public Object listChargementParCandidature(@PathVariable Long id) {

        DataFormatter<Object> affec=new DataFormatter<>();
        Object items=chargementService.listCandidature(id);

        try {
            return affec.renderData(true, chargementService.listTypeChargement(id), "Operation Successifully");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }

    }



    @GetMapping("list/{id}/post")
    public Object listChargementParPoste(@PathVariable Long id) {

        DataFormatter<Object> affec=new DataFormatter<>();
        Object items=chargementService.listPoste(id);

        try {

            return affec.renderData(true, chargementService.listTypeChargement(id), "Operation Successifully");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }

    }


    @GetMapping("list/{tcId}/detailAgr/{dAid}/typeChargement")
    public Object listChargementParTypeChargementAndDetailAgr(@PathVariable Long tcId, @PathVariable Long dAid) {

        DataFormatter<Object> affec=new DataFormatter<>();
        Object items=chargementService.listDetailAgrAndTypeChargement(tcId,dAid);

        try {

            return affec.renderData(true, items, "Operation Successifully");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }

    }

    @GetMapping("by/{tcId}/detailAgr/{dAid}/typeChargement")
    public Object getChargementParTypeChargementAndDetailAgr(@PathVariable Long tcId, @PathVariable Long dAid) {

        DataFormatter<Object> affec=new DataFormatter<>();
        Object items=chargementService.getDetailAgrAndTypeChargement(tcId,dAid);

        try {

            return affec.renderData(true, items, "Operation Successifully");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }

    }

    @GetMapping("list/{tcId}/detailAgr/{dAid}/candidature")
    public Object listChargementByIdDetailAgrAndCanditure(@PathVariable Long tcId, @PathVariable Long dAid) {

        DataFormatter<Object> affec=new DataFormatter<>();
        Object items=chargementService.listChargementByIdDetailAgrAndCanditure(tcId,dAid);

        try {

            return affec.renderData(true, items, "Operation Successifully");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }

    }

    @GetMapping("list/{tcId}/detailAgr/{dAid}/poste")
    public Object listChargementByIdDetailAgrAndCanditurePoste(@PathVariable Long tcId, @PathVariable Long dAid) {

        DataFormatter<Object> affec=new DataFormatter<>();
        Object items=chargementService.listChargementByIdDetailAgrAndCanditurePoste(tcId,dAid);

        try {

            return affec.renderData(true, items, "Operation Successifully");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }

    }

    @GetMapping("by/{tcId}/detailAgr/{dAid}/poste")
    public Object getChargementByIdDetailAgrAndPoste(@PathVariable Long tcId, @PathVariable Long dAid) {

        DataFormatter<Object> affec=new DataFormatter<>();

        Object items=chargementService.getChargementByIdDetailAgrAndPoste(tcId,dAid);

        try {

            return affec.renderData(true, items, "Operation Successifully");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }

    }



}



