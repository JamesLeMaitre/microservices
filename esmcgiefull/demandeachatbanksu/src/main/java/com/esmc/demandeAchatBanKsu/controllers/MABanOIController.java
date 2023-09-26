package com.esmc.demandeAchatBanKsu.controllers;

import com.esmc.demandeAchatBanKsu.entities.MABanOI;
import com.esmc.demandeAchatBanKsu.servicesInterface.MABanOIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/MABanOI")
public class MABanOIController {
    @Autowired
    private MABanOIService maBanOIService;

    @PostMapping("/save")
    public ResponseEntity<MABanOI> savemaBanOI(@RequestBody MABanOI maBanOI){
        return new ResponseEntity<MABanOI>( maBanOIService.saveMABanOI(maBanOI), HttpStatus.CREATED);
    }

    @GetMapping("/listAll")
    public ResponseEntity<List<MABanOI>>getBanKsu(){
        return new ResponseEntity<List<MABanOI>>(maBanOIService.getMABanOI(),HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<MABanOI> maBanOI(@PathVariable("id") Long id){
        return new ResponseEntity<MABanOI>( maBanOIService.MABanOI(id),HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<MABanOI> updatemaBanOI(@PathVariable("id") Long id , @RequestBody MABanOI maBanOI ){
        maBanOI.setId(id);
        return new ResponseEntity<MABanOI>( maBanOIService.updatemaBanOI(maBanOI),HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public  ResponseEntity<MABanOI> deletemaBanOI(@PathVariable Long id ){
        maBanOIService.deletemaBanOI(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
