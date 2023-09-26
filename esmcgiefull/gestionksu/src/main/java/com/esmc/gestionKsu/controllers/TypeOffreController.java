package com.esmc.gestionKsu.controllers;

import com.esmc.gestionKsu.entities.TypeOffre;
import com.esmc.gestionKsu.services.TypeOffreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * TypeOffre controller
 * @author katoh <katohdavid@gmail.com>
 */
@RestController
@RequestMapping(value = "api/typeoffres/")
public class TypeOffreController {

    @Autowired
    private TypeOffreService typeOffreService;

    @GetMapping("list")
    public PagedModel<EntityModel<TypeOffre>> getAll(Pageable page, PagedResourcesAssembler<TypeOffre> pagedResourcesAssembler) {
        return typeOffreService.getAll(page,pagedResourcesAssembler);
    }

    @PostMapping("save")
    public ResponseEntity<?> save(@RequestBody @Valid TypeOffre typeOffre){
        try {
            typeOffreService.save(typeOffre);
            return new ResponseEntity<>("save est ok", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @PutMapping("update/{id}")
    public ResponseEntity<?> update(@RequestBody @Valid TypeOffre typeOffre, @PathVariable Long id){
        try {
            typeOffre.setId(id);
            typeOffreService.update(typeOffre);
            return new ResponseEntity<>("save est ok",HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @GetMapping("getById/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        try {
            TypeOffre typeOffre=typeOffreService.getById(id);
            return new ResponseEntity<>(typeOffre,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("getByLibelle/{libelle}")
    public ResponseEntity<?> getByLibelle(@PathVariable String libelle){
        try {
            TypeOffre typeOffre=typeOffreService.getByLibelle(libelle);
            return new ResponseEntity<>(typeOffre,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

}
