package com.esmc.gestionFranchise.controller;

import com.esmc.gestionFranchise.entities.DetailOrgane;
import com.esmc.gestionFranchise.servicesInterface.DetailOrganeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/DetailOrgane")
public class DetailOrganeController {

    @Autowired
    private DetailOrganeService DetailOrganeService;

    @PostMapping("/save")
    public ResponseEntity<DetailOrgane> saveDetailOrgane(@RequestBody DetailOrgane DetailOrgane){
        return new ResponseEntity<DetailOrgane>(DetailOrganeService.ajouterDetailOrgane(DetailOrgane),HttpStatus.OK);
    }

    @GetMapping("/listAll")
    public ResponseEntity<List<DetailOrgane>>getDetailOrgane(){
        return new ResponseEntity<List<DetailOrgane>>(DetailOrganeService.getDetailOrgane(),HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<DetailOrgane> DetailOrgane(@PathVariable("id") Long id){
        return new ResponseEntity<DetailOrgane>( DetailOrganeService.getDetailOrganebyId(id),HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<DetailOrgane> updateDetailOrgane(@PathVariable("id") Long id , @RequestBody DetailOrgane DetailOrgane ){
        DetailOrgane.setId(id);
        return new  ResponseEntity<DetailOrgane>(DetailOrganeService.ajouterDetailOrgane(DetailOrgane), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public  ResponseEntity<DetailOrgane> deleteDetailOrgane(@PathVariable Long id ){
        DetailOrganeService.deleteDetailOrgane(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
