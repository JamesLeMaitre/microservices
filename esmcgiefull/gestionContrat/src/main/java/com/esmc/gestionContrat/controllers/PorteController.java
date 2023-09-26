package com.esmc.gestionContrat.controllers;

import com.esmc.gestionContrat.entities.Porte;
import com.esmc.gestionContrat.service.PorteService;
import com.esmc.gestionContrat.serviceInterfaces.PorteServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/porte")
public class PorteController {

    @Autowired
    private PorteService porteServiceInterface;

    @PostMapping("/save")
    public Porte ajouterPorte(@RequestBody Porte p) {
        return porteServiceInterface.addPorte(p);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Porte> updatePorte(@PathVariable("id") Long id, @RequestBody Porte porte) {
        porte.setId(id);
        return new ResponseEntity<Porte>(porteServiceInterface.updatePorte(porte), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public void supprimerPorte(@PathVariable Long id) {
        porteServiceInterface.deletePorte(id);
    }

    @GetMapping("/list")
    public List<Porte> listPorte() {
        return porteServiceInterface.listPorte();
    }
}
