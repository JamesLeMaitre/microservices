package com.esmc.gestionFranchise.controller;


import com.esmc.gestionFranchise.entities.Organe;
import com.esmc.gestionFranchise.servicesInterface.OrganeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/Organe")
public class OrganeController {
    @Autowired
    private OrganeService organeService ;

    @GetMapping(value = "/listall")
    public ResponseEntity<List<Organe>> getOrgane(){
        return new ResponseEntity<List<Organe>>(organeService.getOrgane(), HttpStatus.OK);
    }
    @GetMapping(value = "/listbyConseilAdmin/{id}")
    public ResponseEntity<List<Organe>> getOrganebyCadmin(@PathVariable Long id){
        return new ResponseEntity<List<Organe>>(organeService.getOrganebyCadmin(id), HttpStatus.OK);
    }
    @PostMapping(value = "/save")
    public ResponseEntity<Organe> ajouterOrgane(@RequestBody Organe organe){
        return new ResponseEntity<Organe>(organeService.ajouterOrgane(organe),HttpStatus.CREATED);
    }
    @GetMapping(value = "/get/{id}")
    public ResponseEntity<Organe> getOrganebyId(@PathVariable Long id){
        return new ResponseEntity<>(organeService.getOrganebyId(id),HttpStatus.OK);
    }
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Organe> deleteOrgane(@PathVariable Long id){
        organeService.deleteOrgane(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
