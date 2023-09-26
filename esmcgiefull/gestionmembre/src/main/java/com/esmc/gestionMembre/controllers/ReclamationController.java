package com.esmc.gestionMembre.controllers;

import com.esmc.gestionMembre.entities.Reclamation;
import com.esmc.gestionMembre.services.ReclamationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utiles.DataFormatter;

import java.util.List;

@RestController
@RequestMapping("api/reclamation/")
@AllArgsConstructor
public class ReclamationController extends DataFormatter<Reclamation> {

    @Autowired
    private ReclamationService reclamationService;

    @PostMapping("save")
    public Object saveReclamation(@RequestBody Reclamation reclamation){
        return renderData(true, reclamationService.ajouterReclamation(reclamation), "Opperation Successifully");
    }

    @GetMapping("listAll")
    public Object getReclamation(){

        List<Reclamation> items = reclamationService.getReclamation();

        if (items.size() == 0)
        {
            return renderStringData(false, "", "Not Found");
        }
        return renderDataArray(true, items, "Opperation Successifully");
    }

    @GetMapping("get/{id}")
    public Object Reclamation(@PathVariable("id") Long id){

        Reclamation item = reclamationService.getReclamationbyId(id);

        if (item == null) {
            return renderStringData(false, "", "Not found");
        }

        return renderData(true, item, "Opertation Successifully");
    }
    @PutMapping("update/{id}")
    public Object updateReclamation(@PathVariable("id") Long id, @RequestBody Reclamation reclamation){

        Reclamation item = reclamationService.updateReclamation(id, reclamation);

        if (item == null) {
            return renderStringData(false, "", "Not found");
        }
        return renderData(true, item, "Operation Successifully");
    }

    @GetMapping("delete/{id}")
    public Object deleteReclamation(@PathVariable Long id){
        reclamationService.deleteReclamation(id);
        return renderStringData(true, "", "Supprimer avec Succes");
    }

}
