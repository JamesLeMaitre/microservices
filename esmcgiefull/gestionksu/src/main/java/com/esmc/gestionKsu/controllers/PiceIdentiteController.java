package com.esmc.gestionKsu.controllers;

import com.esmc.gestionKsu.entities.PieceIdentite;
import com.esmc.gestionKsu.services.PieceIdentiteService;
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
 * {@link PieceIdentite} controller
 * @author katoh <katohdavid@gmail.com>
 */
@RestController
@RequestMapping(value = "api/pieceidentites/")
public class PiceIdentiteController {

    @Autowired
    private PieceIdentiteService pieceIdentiteService;

    @GetMapping("list")
    public PagedModel<EntityModel<PieceIdentite>> getAll(Pageable page, PagedResourcesAssembler<PieceIdentite> pagedResourcesAssembler) {
        return pieceIdentiteService.getAll(page,pagedResourcesAssembler);
    }

    @PostMapping("save")
    public ResponseEntity<?> save(@RequestBody @Valid PieceIdentite pieceIdentite){
        try {
            pieceIdentiteService.save(pieceIdentite);
            return new ResponseEntity<>("sauvegarde est ok", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @PutMapping("update/{id}")
    public ResponseEntity<?> update(@RequestBody @Valid PieceIdentite  pieceIdentite, @PathVariable Long id){
        try {
            pieceIdentite.setId(id);
            pieceIdentiteService.update(pieceIdentite);
            return new ResponseEntity<>("sauvegarde est ok",HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @GetMapping("getById/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        try {
            PieceIdentite pieceIdentite=pieceIdentiteService.getById(id);
            return new ResponseEntity<>(pieceIdentite,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("getByLibelle/{libelle}")
    public ResponseEntity<?> getByLibelle(@PathVariable String libelle){
        try {
            PieceIdentite pieceIdentite=pieceIdentiteService.getByLibelle(libelle);
            return new ResponseEntity<>(pieceIdentite,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

}
