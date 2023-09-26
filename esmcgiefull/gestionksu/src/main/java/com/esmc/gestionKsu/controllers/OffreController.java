package com.esmc.gestionKsu.controllers;

import com.esmc.gestionKsu.entities.Offre;
import com.esmc.gestionKsu.services.OffreService;
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
 * Offre controller
 * @author katoh <katohdavid@gmail.com>
 */
@RestController
@RequestMapping(value = "api/offres/")
public class OffreController {

    @Autowired
    private OffreService offreService;

    @GetMapping("list")
    public PagedModel<EntityModel<Offre>> getAll(Pageable page, PagedResourcesAssembler<Offre> pagedResourcesAssembler) {
        return offreService.getAll(page,pagedResourcesAssembler);
    }

    @PostMapping("save")
    public ResponseEntity<?> save(@RequestBody @Valid Offre offre){
        try {
            offreService.save(offre);
            return new ResponseEntity<>("sauvegarde est ok", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @PutMapping("update/{id}")
    public ResponseEntity<?> update(@RequestBody @Valid Offre offre, @PathVariable Long id){
        try {
            offre.setId(id);
            offreService.update(offre);
            return new ResponseEntity<>("sauvegarde est ok",HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @GetMapping("getById/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        try {
            Offre offre=offreService.getById(id);
            return new ResponseEntity<>(offre,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("getByLibelle/{libelle}")
    public ResponseEntity<?> getByLibelle(@PathVariable String libelle){
        try {
            Offre offre=offreService.getByLibelle(libelle);
            return new ResponseEntity<>(offre,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("list/{id}/typePM/{id2}/typeOffre")
    public List<Offre> listOffreByTypePMAndTypeOffre(@PathVariable Long id, @PathVariable Long id2) {
        return offreService.listOffreParTypeOffre(id, id2);
    }

}
