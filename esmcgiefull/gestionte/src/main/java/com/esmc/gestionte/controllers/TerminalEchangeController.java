package com.esmc.gestionte.controllers;


import com.esmc.gestionte.entities.TerminalEchange;
import com.esmc.gestionte.exceptions.Exceptions;
import com.esmc.gestionte.serviceinterface.TerminalEchangeService;
import com.esmc.gestionte.utils.Util;
import com.esmc.models.DetailsAgr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utiles.DataFormatter;

import java.util.List;

/**
 * @author Amemorte
 * @since 05/05/2022
 */

@RestController
@RequestMapping(value = "api/terminalEchange/")
public class TerminalEchangeController extends DataFormatter<TerminalEchange> {

    @Autowired
    private TerminalEchangeService terminalEchangeService;


    @GetMapping("detailAgr/{id}")
    public Object affectationTeParAgr(@PathVariable Long id) {
        TerminalEchange te = terminalEchangeService.creationTeByAgr(id);

        if (te == null) {
            return renderStringData(false, "", "Not Found");
        }
        return renderData(true, te, "Operation successful");
    }

    @GetMapping("detail_agrte/{id}")
    public TerminalEchange getTeByDetailAgr(@PathVariable ("id") Long id){
         return terminalEchangeService.teByDetailAgr(id);

    }

    @GetMapping("getAll")
    public List<TerminalEchange> getSTerminalEchange(){
        return terminalEchangeService.getSTerminalEchange();
    }


    @GetMapping("save")
    public ResponseEntity<?> addNewSupportMarchandEnchage(@RequestBody List<DetailsAgr> list){
        try {
            terminalEchangeService.addNewTerminalEchange(list);
            return new ResponseEntity<>(Util.SUCCES_MESSAGE, HttpStatus.OK);
        } catch (Exceptions e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }

    }

    @PutMapping("update/{id}")
    public ResponseEntity<?> updateTerminalEchange(@PathVariable("id") Long id,@RequestBody TerminalEchange terminalEchange ){
        try {
            terminalEchangeService.updateTerminalEchange(id,terminalEchange);
            return new ResponseEntity<>(Util.SUCCES_MESSAGE,HttpStatus.OK);
        }catch (Exceptions e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteTerminalEchange(@PathVariable ("id") Long id){
        try {
            terminalEchangeService.deleteTerminalEchange(id);
            return new ResponseEntity<>(Util.SUCCES_MESSAGE,HttpStatus.OK);
        }catch (Exceptions e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable ("id") Long id){
        try {
            terminalEchangeService.getById(id);
            return new ResponseEntity<>(Util.SUCCES_MESSAGE,HttpStatus.OK);
        }catch (Exceptions e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
        }
    }

//    @GetMapping("jour/montant/{montant}")
//    public ResponseEntity<?> jour(@PathVariable ("montant") Double montant){
//        try {
//            terminalEchangeService.jour(montant);
//            return new ResponseEntity<>(Util.SUCCES_MESSAGE,HttpStatus.OK);
//        }catch (Exceptions e){
//            return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
//        }
//    }
//    @GetMapping("limitee_11_2/montant/{montant}")
//    public ResponseEntity<?> limitee_11_2(@PathVariable ("montant") Double montant){
//        try {
//            terminalEchangeService.limitee_11_2(montant);
//            return new ResponseEntity<>(Util.SUCCES_MESSAGE,HttpStatus.OK);
//        }catch (Exceptions e){
//            return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
//        }
//    }
//    @GetMapping("limitee_22_4/montant/{montant}")
//    public ResponseEntity<?> limitee_22_4(@PathVariable ("montant") Double montant){
//        try {
//            terminalEchangeService.limitee_22_4(montant);
//            return new ResponseEntity<>(Util.SUCCES_MESSAGE,HttpStatus.OK);
//        }catch (Exceptions e){
//            return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
//        }
//    }
//    @GetMapping("Illimitee_22_4/montant/{montant}")
//    public ResponseEntity<?> Illimitee_22_4(@PathVariable ("montant") Double montant){
//        try {
//            terminalEchangeService.illimitee_22_4(montant);
//            return new ResponseEntity<>(HttpStatus.OK);
//        }catch (Exceptions e){
//            return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
//        }
//    }
    @GetMapping("encaissementBAn/BPS/{BPS}/PCK/{PCK}/typeBPS/{typeBPS}")
    public ResponseEntity<?> encaissementBAn(@PathVariable ("BPS") Double BPS,@PathVariable ("PCK") Double PCK,@PathVariable ("PRK") Double PRK){
        try {
            terminalEchangeService.encaissementBAn(BPS,PCK,PRK);
            return new ResponseEntity<>(Util.SUCCES_MESSAGE,HttpStatus.OK);
        }catch (Exceptions e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
        }
    }
    @GetMapping("dencaissementBAn/BPS/{BPS}/PCK/{PCK}/PRK/{PRK}/typeBPS/{typeBPS}")
    public ResponseEntity<?> dencaissementBAn(@PathVariable ("BPS") Double BPS,@PathVariable ("PCK") Double PCK,@PathVariable ("PRK") Double PRK,@PathVariable ("typeBPS") String typeBPS){
        try {
            terminalEchangeService.dencaissementBAn(BPS,PCK,PRK,typeBPS);
            return new ResponseEntity<>(Util.SUCCES_MESSAGE,HttpStatus.OK);
        }catch (Exceptions e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
        }
    }
    @GetMapping("getByDetailAgr/{id}")
    public TerminalEchange findTElokByIdDetailAgr(@PathVariable ("id") Long id) throws Exceptions {

            return  terminalEchangeService.findByIdDetailAgr(id);

    }

}
