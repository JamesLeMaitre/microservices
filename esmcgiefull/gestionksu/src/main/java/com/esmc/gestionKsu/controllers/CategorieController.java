package com.esmc.gestionKsu.controllers;

import com.esmc.gestionKsu.entities.Categorie;
import com.esmc.gestionKsu.services.CategorieService;
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
 * {@link Categorie} controller
 * @author katoh <katohdavid@gmail.com>
 */
@RestController
@RequestMapping(value = "api/categories/")
public class CategorieController {

    @Autowired
    private CategorieService categorieService;

    @GetMapping("list")
    public PagedModel<EntityModel<Categorie>> getAll(Pageable page, PagedResourcesAssembler<Categorie> pagedResourcesAssembler) {
        return categorieService.getAll(page,pagedResourcesAssembler);
    }

    @PostMapping("save")

    public ResponseEntity<?> save(@RequestBody @Valid Categorie categorie){
        try {
            categorieService.save(categorie);
            return new ResponseEntity<>("sauvegarde est ok", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @PutMapping("update/{id}")
    public ResponseEntity<?> update(@RequestBody @Valid Categorie  categorie, @PathVariable Long id){
        try {
            categorie.setId(id);
            categorieService.update(categorie);
            return new ResponseEntity<>("sauvegarde est ok",HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @GetMapping("getById/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        try {
            Categorie categorie=categorieService.getById(id);
            return new ResponseEntity<>(categorie,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("getByLibelle/{libelle}")
    public ResponseEntity<?> getByLibelle(@PathVariable String libelle){
        try {
            Categorie categorie=categorieService.getByLibelle(libelle);
            return new ResponseEntity<>(categorie,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

}
