package com.esmc.gestionKsu.controllers;

import com.esmc.gestionKsu.entities.Banque;
import com.esmc.gestionKsu.serviceinterfaces.BanqueServiceInterface;
import com.esmc.gestionKsu.services.BanqueService;
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
 * Banque entity controller
 * @author katoh <katohdavid@gmail.com>
 */
@RestController
@RequestMapping(value = "api/banques/")
public class BanqueController {

    @Autowired
    private BanqueServiceInterface banqueService;

    @GetMapping("list")
    public List<Banque> listBanque() {
        return banqueService.listBanque();
    }


    @PostMapping("save")

    public Banque ajouterBanque(Banque b) {
        return banqueService.addBanque(b);
    }


    @PutMapping("update/{id}")
    public ResponseEntity<?> update(@RequestBody @Valid Banque banque, @PathVariable Long id){
        try {
            banque.setId(id);
            banqueService.updateBanque(banque);
            return new ResponseEntity<>("sauvegarde est ok",HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @GetMapping("getById/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        try {
            Banque banque=banqueService.getBanqueById(id);
            return new ResponseEntity<>(banque,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }



}
