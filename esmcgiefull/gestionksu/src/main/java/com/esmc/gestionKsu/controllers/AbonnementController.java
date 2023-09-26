package com.esmc.gestionKsu.controllers;

import com.esmc.gestionKsu.entities.Abonnement;
import com.esmc.gestionKsu.serviceinterfaces.AbonnementServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Abonnement controller
 * @author katoh <katohdavid@gmail.com>
 */
@RestController
@RequestMapping(value = "api/abonnements/")
public class AbonnementController {

    @Autowired
    private AbonnementServiceInterface abonnementService;

    @GetMapping("list")
    public PagedModel<EntityModel<Abonnement>> getAll(Pageable page, PagedResourcesAssembler<Abonnement> pagedResourcesAssembler) {
        return abonnementService.getAll(page,pagedResourcesAssembler);
    }

    @PostMapping("save")
    public Abonnement save(@RequestBody Abonnement abonnement){
    return abonnementService.save(abonnement);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<?> update(@RequestBody @Valid Abonnement  abonnement, @PathVariable Long id){
        try {
            abonnement.setId(id);
            abonnementService.update(abonnement);
            return new ResponseEntity<>("la save est ok",HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @GetMapping("getById/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        try {
            Abonnement abonnement=abonnementService.getById(id);
            return new ResponseEntity<>(abonnement,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("getByLibelle/{libelle}")
    public ResponseEntity<?> getByLibelle(@PathVariable String libelle){
        try {
            Abonnement abonnement=abonnementService.getByLibelle(libelle);
            return new ResponseEntity<>(abonnement,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("list/{id}/typeOffre")
    public List<Abonnement> listAbonnementParTypeOffre(@PathVariable Long id) {
        return abonnementService.listAbonnementParTypeOffer(id);
    }

}
