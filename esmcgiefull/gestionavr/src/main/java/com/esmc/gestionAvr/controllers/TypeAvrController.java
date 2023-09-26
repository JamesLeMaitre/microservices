package com.esmc.gestionAvr.controllers;

import com.esmc.gestionAvr.entities.TypeAvr;
import com.esmc.gestionAvr.servicesInterfaces.TypeAvrInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/typeAvr/")
public class TypeAvrController {

    @Autowired
    private TypeAvrInterface typeAvrInterface;

    /**
     * Map to save an AvrType
     * @param typeAvr
     * @return
     */
    @PostMapping(value = "save")
    public ResponseEntity<TypeAvr> addTypeAvr(@RequestBody TypeAvr typeAvr) {
        return new ResponseEntity<TypeAvr>( typeAvrInterface.addTypeAvr(typeAvr),HttpStatus.CREATED);
    }



    /**
     * Map to update an AvrType
     * @param id
     * @return
     */
    @GetMapping(value = "get/{id}")
    public ResponseEntity<TypeAvr> getById(@PathVariable Long id){
        return new ResponseEntity<TypeAvr>( typeAvrInterface.getTypeAvrById(id),HttpStatus.OK);
    }

    /**
     * Map to delete an AvrType
     * @param id
     */
    @DeleteMapping(value = "delete/{id}")
    public void deleteTypeAvr(@PathVariable Long id){
        typeAvrInterface.deleteTypeAvr(id);
    }

    /**
     * Map to see the list of TypeAvrs
     * @return
     */
    @GetMapping(value = "list")
    public List<TypeAvr> listTypeAvr(){
        return typeAvrInterface.listTypeAvr();
    }

    @PutMapping(value ="update/{id}")
    public ResponseEntity<TypeAvr> updateTypeAvr(@RequestBody long id , @RequestBody TypeAvr typeAvr ){
        typeAvr.setId(id);
        return new ResponseEntity<TypeAvr>( typeAvrInterface.updateTypeAvr(id,typeAvr), HttpStatus.OK);
    }

    @GetMapping(value = "get/listByFifo")
    public List<TypeAvr> listTypeAvrOfFifo(){
        return typeAvrInterface.listTypeAvrOfFifo();
    }

}
