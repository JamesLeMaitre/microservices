package com.esmc.gestionFranchise.controller;

import com.esmc.gestionFranchise.entities.BorderauRemiseCheque;
import com.esmc.gestionFranchise.servicesInterface.BorderauRemiseChequeService;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/BorderauRemiseCheque")
public class BorderauRemiseChequeController {


    @Autowired
    private BorderauRemiseChequeService BorderauRemiseChequeService;

    @PostMapping("/save")
    public ResponseEntity<BorderauRemiseCheque> saveBorderauRemiseCheque(@RequestBody BorderauRemiseCheque BorderauRemiseCheque){
        return new ResponseEntity<BorderauRemiseCheque>(BorderauRemiseChequeService.ajouterBorderauRemiseCheque(BorderauRemiseCheque),HttpStatus.OK);
    }

    @GetMapping("/listAll")
    public ResponseEntity<List<BorderauRemiseCheque>>getBorderauRemiseCheque(){
        return new ResponseEntity<List<BorderauRemiseCheque>>(BorderauRemiseChequeService.getBorderauRemiseCheque(),HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<BorderauRemiseCheque> BorderauRemiseCheque(@PathVariable("id") Long id){
        return new ResponseEntity<BorderauRemiseCheque>( BorderauRemiseChequeService.getBorderauRemiseChequebyId(id),HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<BorderauRemiseCheque> updateBorderauRemiseCheque(@PathVariable("id") Long id , @RequestBody BorderauRemiseCheque BorderauRemiseCheque ){
        BorderauRemiseCheque.setId(id);
        return new  ResponseEntity<BorderauRemiseCheque>(BorderauRemiseChequeService.ajouterBorderauRemiseCheque(BorderauRemiseCheque), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public  ResponseEntity<BorderauRemiseCheque> deleteBorderauRemiseCheque(@PathVariable Long id ){
        BorderauRemiseChequeService.deleteBorderauRemiseCheque(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
