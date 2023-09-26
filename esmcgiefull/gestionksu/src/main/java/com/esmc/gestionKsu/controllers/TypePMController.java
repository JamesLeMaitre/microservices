package com.esmc.gestionKsu.controllers;

import com.esmc.gestionKsu.entities.TypePM;
import com.esmc.gestionKsu.services.TypePMService;
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
 * {@link TypePM} controller
 * @author katoh <katohdavid@gmail.com>
 */
@RestController
@RequestMapping(value = "api/typepms/")
public class TypePMController {

    @Autowired
    private TypePMService typePMService;

    @GetMapping("list")
    public PagedModel<EntityModel<TypePM>> getAll(Pageable page, PagedResourcesAssembler<TypePM> pagedResourcesAssembler) {
        return typePMService.getAll(page,pagedResourcesAssembler);
    }

    @PostMapping("save")
    public ResponseEntity<?> save(@RequestBody @Valid TypePM typePM){
        try {
            typePMService.save(typePM);
            return new ResponseEntity<>("save est ok", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @PutMapping("update/{id}")
    public ResponseEntity<?> update(@RequestBody @Valid TypePM  typePM, @PathVariable Long id){
        try {
            typePM.setId(id);
            typePMService.update(typePM);
            return new ResponseEntity<>("save est ok",HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @GetMapping("getById/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        try {
            TypePM typePM=typePMService.getById(id);
            return new ResponseEntity<>(typePM,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("getByLibelle/{libelle}")
    public ResponseEntity<?> getByLibelle(@PathVariable String libelle){
        try {
            TypePM typePM=typePMService.getByLibelle(libelle);
            return new ResponseEntity<>(typePM,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

}
