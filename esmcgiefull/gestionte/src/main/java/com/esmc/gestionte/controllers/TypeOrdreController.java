package com.esmc.gestionte.controllers;



import com.esmc.gestionte.entities.TypeOrdre;
import com.esmc.gestionte.exceptions.Exceptions;
import com.esmc.gestionte.serviceinterface.TypeOrdreService;
import com.esmc.gestionte.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Amemorte
 * @since 05/05/2022
 */

@RestController
@RequestMapping("api/typeordre/")
public class TypeOrdreController {

    @Autowired
    private TypeOrdreService typeOrdreService;

    /*    @PostMapping(value = "save")
    public TypeOrdre addNewTypeOrdre(@RequestBody TypeOrdre typeOrdre) {
        return typeOrdreService.addNew(typeOrdre);
    }*/

    @GetMapping("getAll")
    public List<TypeOrdre> getTypeOrdre(){
        return typeOrdreService.getTypeOrdre();
    }

    @PostMapping("save")
    public ResponseEntity<?> addNewSupportMarchandEnchage(@RequestBody TypeOrdre typeOrdre){
        try {
            typeOrdreService.addNewTypeOrdre(typeOrdre);
            return new ResponseEntity<>(Util.SUCCES_MESSAGE, HttpStatus.OK);
        } catch (Exceptions e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }

    }

    @PutMapping("update/{id}")
    public ResponseEntity<?> updateTerminalEchange(@PathVariable("id") Long id,@RequestBody TypeOrdre typeOrdre ){
        try {
            typeOrdreService.updateTypeOrdre(id,typeOrdre);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exceptions e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteTerminalEchange(@PathVariable ("id") Long id){
        try {
            typeOrdreService.deleteTypeOrdre(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exceptions e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable ("id") Long id){
        try {
            typeOrdreService.getById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exceptions e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);

        }
    }
}
