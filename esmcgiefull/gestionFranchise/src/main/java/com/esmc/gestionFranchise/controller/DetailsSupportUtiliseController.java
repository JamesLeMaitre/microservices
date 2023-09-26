package com.esmc.gestionFranchise.controller;

import com.esmc.gestionFranchise.entities.DetailsSupportUtilise;
import com.esmc.gestionFranchise.servicesInterface.DetailsSupportUtiliseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "api/DetailsSupportUtilise")
public class DetailsSupportUtiliseController {
    @Autowired
    private DetailsSupportUtiliseService DetailsSupportUtiliseService ;

    @GetMapping(value = "/listall")
    public ResponseEntity<List<DetailsSupportUtilise>> getDetailsSupportUtilise(){
        return new ResponseEntity<List<DetailsSupportUtilise>>(DetailsSupportUtiliseService.getDetailsSupportUtilise(), HttpStatus.OK);
    } @GetMapping(value = "/listallbyTdep/{id}")
    public ResponseEntity<List<DetailsSupportUtilise>> getDetailsSupportUtilisebyTdep(@PathVariable Long id){
        return new ResponseEntity<List<DetailsSupportUtilise>>(DetailsSupportUtiliseService.getDetailsSupportUtilisebyTdep(id), HttpStatus.OK);
    }
    @PostMapping(value = "/save")
    public ResponseEntity<DetailsSupportUtilise> ajouterDetailsSupportUtilise(@RequestBody DetailsSupportUtilise DetailsSupportUtilise){
        return new ResponseEntity<DetailsSupportUtilise>(DetailsSupportUtiliseService.ajouterDetailsSupportUtilise(DetailsSupportUtilise),HttpStatus.CREATED);
    }
    @GetMapping(value = "/get/{id}")
    public ResponseEntity<DetailsSupportUtilise> getDetailsSupportUtilisebyId(@PathVariable Long id){
        return new ResponseEntity<>(   DetailsSupportUtiliseService.getDetailsSupportUtilisebyId(id),HttpStatus.OK);
    }
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<DetailsSupportUtilise> deleteDetailsSupportUtilise(@PathVariable Long id){
        DetailsSupportUtiliseService.deleteDetailsSupportUtilise(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

