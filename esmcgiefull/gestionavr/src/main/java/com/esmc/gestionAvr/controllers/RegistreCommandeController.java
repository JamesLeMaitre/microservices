package com.esmc.gestionAvr.controllers;

import com.esmc.gestionAvr.entities.RegistreCommande;
import com.esmc.gestionAvr.servicesInterfaces.RegistreCommandeInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/RegistreCommande/")
public class RegistreCommandeController {

    @Autowired
    private RegistreCommandeInterface registreCommandeInterface;
    /**
     * Map to save an AvrType
     * @param registreCommande
     * @return
     */
    @PostMapping(value = "save")
    public ResponseEntity<RegistreCommande> addRegistreCommande(@RequestBody RegistreCommande registreCommande) {
        return new ResponseEntity<RegistreCommande>( registreCommandeInterface.addRegistreCommande(registreCommande), HttpStatus.CREATED);
    }

    /**
     * Map to update an RegistreCommande
     *
     * @param id
     * @return
     */
    @GetMapping(value = "get/{id}")
    public ResponseEntity<RegistreCommande> getById(@PathVariable Long id){
        return new ResponseEntity<RegistreCommande>( registreCommandeInterface.getRegistreCommandeId(id),HttpStatus.OK);
    }

    /**
     * Map to delete an RegistreCommande
     * @param id
     */
    @DeleteMapping(value = "delete/{id}")
    public void deleteRegistreCommande(@PathVariable Long id){
        registreCommandeInterface.deleteRegistreCommande(id);
    }


    /**
     * Map to see the list of RegistreCommande
     * @return
     */
    @GetMapping(value = "get/all")
    public List<RegistreCommande> listRegistreCommande(){
        return registreCommandeInterface.listRegistreCommande();
    }

    @PutMapping(value ="update/{id}")
    public ResponseEntity<RegistreCommande> updateRegistreCommande(@RequestBody long id , @RequestBody RegistreCommande registreCommande ){
        registreCommande.setId(id);
        return new ResponseEntity<RegistreCommande>( registreCommandeInterface.updateRegistreCommande(id, registreCommande), HttpStatus.OK);
    }




    @GetMapping(value = "get/{id}/DetailSMAvr")
    public List<RegistreCommande> ListRegistreCommandByDetailSMAvrId(@PathVariable Long id){
        return registreCommandeInterface.ListRegistreCommandByDetailSMAvrId(id);
    }

    @GetMapping(value = "get/{reference}/DetailSMAvr")
    public List<RegistreCommande> ListRegistreCommandByDetailSMAvrreference(@PathVariable String reference){
        return registreCommandeInterface.ListRegistreCommandByDetailSMAvrreference(reference);
    }

    @GetMapping(value = "get/{id}/{reference}/DetailSMAvr")
    public List<RegistreCommande> listRegistreCommandeByDetailSMAvrIdAndType(@PathVariable Long id,@PathVariable String reference){
        return registreCommandeInterface.listRegistreCommandeByDetailSMAvrIdAndType(id, reference);
    }

    /*@GetMapping(value = "get/{article}/DetailSMAvr")
    public List<RegistreCommande> ListRegistreCommandByDetailSMAvrarticle(@PathVariable String article){
        return registreCommandeInterface.ListRegistreCommandByDetailSMAvrarticle(article);
    }*/

}
