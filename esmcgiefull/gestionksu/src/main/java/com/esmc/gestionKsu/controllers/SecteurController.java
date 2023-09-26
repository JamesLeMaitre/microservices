package com.esmc.gestionKsu.controllers;

import com.esmc.gestionKsu.entities.Secteur;
import com.esmc.gestionKsu.services.CategorieService;
import com.esmc.gestionKsu.services.SecteurService;
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
 * Secteur controller
 *
 * @author katoh <katohdavid@gmail.com>
 */
@RestController
@RequestMapping(value = "api/secteurs/")
public class SecteurController {

    private final SecteurService secteurService;
    private final CategorieService categorieService;

    public SecteurController(SecteurService secteurService, CategorieService categorieService) {
        this.secteurService = secteurService;
        this.categorieService = categorieService;
    }

    @GetMapping("getAll")
    public PagedModel<EntityModel<Secteur>> getAll(Pageable page, PagedResourcesAssembler<Secteur> pagedResourcesAssembler) {
        return secteurService.getAll(page, pagedResourcesAssembler);
    }

    @PostMapping("save")
    public Secteur save(@RequestBody   Secteur s) {
        return secteurService.save(s);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<?> update(@RequestBody @Valid Secteur secteur, @PathVariable Long id) {
        try {
            secteur.setId(id);
            secteurService.update(secteur);
            return new ResponseEntity<>("sauvegarde est ok", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @GetMapping("getById/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try {
            Secteur secteur = secteurService.getById(id);
            return new ResponseEntity<>(secteur, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("getByLibelle/{libelle}")
    public ResponseEntity<?> getByLibelle(@PathVariable String libelle) {
        try {
            Secteur secteur = secteurService.getByLibelle(libelle);
            return new ResponseEntity<>(secteur, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("list/{id}/categorie")
    public List<Secteur> listSecteurParCategorie(@PathVariable Long id) {
        return secteurService.listSecteurParCategorie(id);
    }

}
