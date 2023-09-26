package com.esmc.gestionFranchise.controller;


import com.esmc.gestionFranchise.entities.ConseilAdministration;
import com.esmc.gestionFranchise.servicesInterface.ConseilAdministrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/conseiladministration")
public class ConseilAdministrationController {

    @Autowired
    private ConseilAdministrationService ConseilAdministrationService;

    @GetMapping("/listall")
    public ResponseEntity<List<ConseilAdministration>>getConseilAdministration(){
        return new ResponseEntity<List<ConseilAdministration>>(ConseilAdministrationService.getallConseilAdmin(),HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<ConseilAdministration> saveConseilAdministration(@RequestBody ConseilAdministration ConseilAdministration){
        return new ResponseEntity<ConseilAdministration>(ConseilAdministrationService.ajouterConseilAdministration(ConseilAdministration),HttpStatus.OK);
    }



    @GetMapping("/listbyfranchise/{id}")
    public ResponseEntity<List<ConseilAdministration>>getConseilAdministrationbyfranchise(@PathVariable("id") Long id){
        return new ResponseEntity<List<ConseilAdministration>>(ConseilAdministrationService.getConseilAdministrationbyFranchise(id),HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ConseilAdministration> ConseilAdministration(@PathVariable("id") Long id){
        return new ResponseEntity<ConseilAdministration>( ConseilAdministrationService.getConseilAdministrationbyId(id),HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ConseilAdministration> updateConseilAdministration(@PathVariable("id") Long id , @RequestBody ConseilAdministration ConseilAdministration ){
        ConseilAdministration.setId(id);
        return new  ResponseEntity<ConseilAdministration>(ConseilAdministrationService.ajouterConseilAdministration(ConseilAdministration), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public  ResponseEntity<ConseilAdministration> deleteConseilAdministration(@PathVariable Long id ){
        ConseilAdministrationService.deleteConseilAdministration(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
