package com.esmc.gestionAvr.controllers;

import com.esmc.gestionAvr.entities.Panier;
import com.esmc.gestionAvr.repositories.AvrRepository;
import com.esmc.gestionAvr.servicesInterfaces.PanierServiceInterface;
import com.esmc.models.Ksu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping(path = "api/paniers/")
public class PanierController {
    @Autowired
    private PanierServiceInterface panierServiceInterface;

    @Autowired
    private AvrRepository avrRepository;

    /*
     * Function to save Panier
     * call the route save
     * */

    /*
     * Function to save Produit dans un  Panier
     * call the route save and add the id of product
     * */

    @PutMapping("modifierQte")
    public Panier modifierQuanite(@RequestBody Panier panier) {
        return panierServiceInterface.findById(panier);
    }

    @GetMapping("commande/{id}")
    public List<Ksu> validerPanier(@PathVariable Long id) {
        return panierServiceInterface.validerCommande(id);
    }

    @PostMapping(value = "save")
    public Panier ajouterPanierProduct(@RequestBody Panier panier) {
        return panierServiceInterface.addPanier(panier);
    }

    /*
     * Function to edit Panier
     * call the route edit and add the id of the object
     * */

    @GetMapping(value = "edit/{id}")
    public ResponseEntity<Panier> modifierPaniers(@PathVariable Long id, @RequestBody Panier p) {
        return new ResponseEntity<Panier>(panierServiceInterface.updatePanier(id, p), HttpStatus.OK) ;
    }

    /*
     * Function to delete Panier
     * call the route delete and add the id of the object
     *
     * */

    @DeleteMapping(value = "delete/{id}")
    public void supprimerPaniers(@PathVariable Long id) {
        panierServiceInterface.deletePanier(id);
    }

    /*
     * Function to get all Panier
     * call the route list
     * */
    @GetMapping(value = "list")
    public List<Panier> listePaniers() {
        return panierServiceInterface.getAllPanier();
    }

    @GetMapping(value = "list/{id}/ksu")
    public List<Panier> listePaniersDeKsu(@PathVariable Long id) {
        return panierServiceInterface.panierDeKsu(id);
    }

    @GetMapping(value = "total/{id}/ksu")
    public Double prixTotalPanier(@PathVariable Long id) {
        return panierServiceInterface.prixTotalPanier(id);
    }

}
