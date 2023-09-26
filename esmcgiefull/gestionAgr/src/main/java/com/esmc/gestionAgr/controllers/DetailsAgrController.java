package com.esmc.gestionAgr.controllers;

import com.esmc.gestionAgr.entities.DetailsAgr;
import com.esmc.gestionAgr.exceptions.Exceptions;
import com.esmc.gestionAgr.serviceinterfaces.DetailAgrServiceInterface;
import com.esmc.gestionAgr.utils.Util;
import com.esmc.models.Formatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utiles.DataFormatter;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

@RestController
@RequestMapping(path = "api/detailsagrs/")
public class DetailsAgrController extends DataFormatter<DetailsAgr> {
    @Autowired
    private DetailAgrServiceInterface detailAgrService;


    @GetMapping("list")
    public List<DetailsAgr> getAgr() {
        return detailAgrService.getAgr();
    }

    @GetMapping("getKSU/{id}")
    public List<DetailsAgr> getDetailAgrByKSU(@PathVariable("id") Long id) {

        return detailAgrService.findDetailAgrByIdkSU(id);
    }

    @GetMapping("detail_agr/{id}")
    public DetailsAgr findDetailAgrByKSU(@PathVariable("id") Long id) {
        return detailAgrService.getDetailAgrByIdkSU(id);
    }

    @GetMapping("detailAgr/{id}")
    public Object getDetailAgr(@PathVariable("id") Long id) {
        try {
            return renderData(true, detailAgrService.getDetailAgrByIdkSU(id), "Operation Successful");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }
    }

    @GetMapping("activate/franchise-mode/{id}")
    public DetailsAgr activateFRanchiseMode(@PathVariable("id") Long id) {
        return detailAgrService.activateFRanchiseMode(id);
    }

    @GetMapping("detail_agr/byId/{id}")
    public DetailsAgr findDetailAgrById(@PathVariable("id") Long id) {

        return detailAgrService.getDetailAgrById(id);
    }

    @GetMapping("save/{val2}/val1/{val1}")
    public ResponseEntity<?> registerAgr(@PathVariable("val2") int val2, @PathVariable("val1") Long val1) {

        detailAgrService.affectationAgr(val2, val1);
        return new ResponseEntity<>(Util.SUCCES_MESSAGE, HttpStatus.OK);

    }

    @DeleteMapping(path = "delete/{agrId}")
    public ResponseEntity<?> deleteAgr(@PathVariable("agrId") Long agrId) {
        try {
            detailAgrService.deleteAgr(agrId);
            return new ResponseEntity<>(Util.SUCCES_MESSAGE, HttpStatus.OK);
        } catch (Exceptions e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }

    }

    @PutMapping("update/{id}")
    public ResponseEntity<?> updateOrdre(@PathVariable("id") Long id, @RequestBody DetailsAgr detailAgr) {
        try {
            detailAgrService.updateAgr(id, detailAgr);
            return new ResponseEntity<>(Util.SUCCES_MESSAGE, HttpStatus.OK);
        } catch (Exceptions e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping("{id}")
    public DetailsAgr getById(@PathVariable("id") Long id) {
            return detailAgrService.getById(id);
    }

    @GetMapping("test/{id}")
    public ResponseEntity<?> get1(@PathVariable("val2") int val2, @PathVariable("val2") Long val1) throws Exceptions {
        /*ResponseEntity<?> responseEntity=manesJSON.getById(id);
        Ksu ksu=(Ksu) responseEntity.getBody();
        DetailAgr detailAgr=new DetailAgr();
        detailAgr.setKsu(ksu);*/
        //return  manesJSON.getById(id);

        detailAgrService.affectationAgr(val2, val1);
        return new ResponseEntity<>(Util.SUCCES_MESSAGE, HttpStatus.OK);
    }

    @GetMapping("typeMaBanksu/{idType}/ksu/{idKsu}")
    public Object affectationAgrAuKsu(@PathVariable("idType") Long idType, @PathVariable("idKsu") Long idKsu) {

        try{

            DetailsAgr item = detailAgrService.AffectationAgr(idType, idKsu);

            if (item == null){
                return renderStringData(false, "", "Data not found");
            }

            return renderData(true, item, "Opération éffectuer avec Succès");
        }catch (Exception handle){
        HttpStatus request = HttpStatus.BAD_REQUEST;
        StringWriter sw = new StringWriter();
        handle.printStackTrace(new PrintWriter(sw));
        String exceptionAsString = sw.toString();
      return "Error while processing "+exceptionAsString;
    }
    }

}
