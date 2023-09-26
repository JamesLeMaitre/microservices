package com.esmc.gestionKsu.controllers;

import com.esmc.gestionKsu.entities.TypeOperateur;
import com.esmc.gestionKsu.services.TypeOperateurService;
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
 * TypeOperateur controller
 * @author katoh <katohdavid@gmail.com>
 */
@RestController
@RequestMapping(value = "api/typeoperateurs/")
public class TypeOperateurController {

    @Autowired
    private TypeOperateurService typeOperateurService;

    @GetMapping("list")
    public PagedModel<EntityModel<TypeOperateur>> getAll(Pageable page, PagedResourcesAssembler<TypeOperateur> pagedResourcesAssembler) {
        return typeOperateurService.getAll(page,pagedResourcesAssembler);
    }

    @PostMapping("save")
    public ResponseEntity<?> save(@RequestBody @Valid TypeOperateur typeOperateur){
        try {
            typeOperateurService.save(typeOperateur);
            return new ResponseEntity<>("save est ok", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @PutMapping("update/{id}")
    public ResponseEntity<?> update(@RequestBody @Valid TypeOperateur typeOperateur, @PathVariable Long id){
        try {
            typeOperateur.setId(id);
            typeOperateurService.update(typeOperateur);
            return new ResponseEntity<>("save est ok",HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @GetMapping("getById/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        try {
            TypeOperateur typeOperateur=typeOperateurService.getById(id);
            return new ResponseEntity<>(typeOperateur,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("getByLibelle/{libelle}")
    public ResponseEntity<?> getByLibelle(@PathVariable String libelle){
        try {
            TypeOperateur typeOperateur=typeOperateurService.getByLibelle(libelle);
            return new ResponseEntity<>(typeOperateur,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

}
