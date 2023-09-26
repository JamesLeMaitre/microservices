package com.esmc.demandeAchatBanKsu.controllers;


import com.esmc.demandeAchatBanKsu.entities.TypeMABanKSU;
import com.esmc.demandeAchatBanKsu.servicesInterface.TypeMABanKSUService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/TypeMABanKSU")
public class TypeMABanKSUController {

    @Autowired
    private TypeMABanKSUService typeMABanKSUService;

    @PostMapping("/save")
    public TypeMABanKSU save(@RequestBody TypeMABanKSU typeMABanKSU){
        return typeMABanKSUService.savetypeMABanKSU(typeMABanKSU);
    }

    @GetMapping("/listAll")
    public List<TypeMABanKSU> getmaBanKsu(){
        return typeMABanKSUService.getTypeMABanKSU();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<TypeMABanKSU> typeMABanKSU(@PathVariable("id") Long id){
        return new ResponseEntity<TypeMABanKSU>( typeMABanKSUService.findTypeMABanKSUById(id),HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<TypeMABanKSU> updatetypeMABanKSU(@PathVariable("id") Long id , @RequestBody TypeMABanKSU typeMABanKSU ){
        typeMABanKSU.setId(id);
        return new ResponseEntity<TypeMABanKSU>( typeMABanKSUService.updatetypeMABanKSU(typeMABanKSU),HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public  ResponseEntity<TypeMABanKSU> deletetypeMABanKSU(@PathVariable Long id ){
        typeMABanKSUService.deletetypeMABanKSU(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
