package com.esmc.gestionAvr.controllers;

import com.esmc.gestionAvr.entities.Echange;
import com.esmc.gestionAvr.services.EchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/echange/")
public class EchangeController {

    @Autowired
    private EchangeService echangeService;
    @PostMapping(value = "save")
    public Echange addEchange(@RequestBody Echange echange) {
        return echangeService.addEchange(echange);
    }


    @PostMapping(value = "update/{id}")
    public Echange updateEchange(@RequestBody Echange echange, @PathVariable Long id){
        echange.setId(id);
        return echangeService.updateEchange(echange);
    }


    @DeleteMapping(value = "delete/{id}")
    public void deleteEchange(@PathVariable Long id){
        echangeService.deleteEchange(id);
    }


    @GetMapping("getEchange/{id}")
    public Echange getEchange(@PathVariable Long id){
        return echangeService.getEchange(id);
    }


    @GetMapping(value = "list")
    public List<Echange> listEchange(){
        return echangeService.listEchange();
    }


    /*@GetMapping(value = "list/{id}/typeEchange")
    public List<Echange> listEchangeByTypeEchange(@PathVariable Long id){
        return echangeService.getEchangeByTypeEchange(id);
    }*/

}
