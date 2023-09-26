package com.esmc.gestionAvr.controllers;

import com.esmc.gestionAvr.entities.TypeAchatEchange;
import com.esmc.gestionAvr.services.TypeAchatEchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/typeAchatEchange/")
public class TypeAchatEchangeController {
    @Autowired
    private TypeAchatEchangeService typeAchatEchangeService;

    @PostMapping(value = "save")
    public TypeAchatEchange addTypeAchatEchange(@RequestBody TypeAchatEchange typeAchatEchange) {
        return typeAchatEchangeService.addTypeAchatEchange(typeAchatEchange);
    }


    @PutMapping("update/{id}")
    public TypeAchatEchange updateTypeAchatEchange(@RequestBody TypeAchatEchange t) {
        return    typeAchatEchangeService.updateTypeAchatEchange(t);
    }

   @DeleteMapping("delete/{id}")
    public void deleteTypeAchatEchange(@PathVariable  Long id) {
        typeAchatEchangeService.deleteTypeAchatEchange(id);
    }

    @GetMapping("list")
    public List<TypeAchatEchange> listTypeAchatEchange() {
        return typeAchatEchangeService.listTypeAchatEchange();
    }

    @GetMapping("list/{id}/typeEchange")
    public List<TypeAchatEchange> listTypeAchatEchangeByEchange(@PathVariable Long id) {
        return typeAchatEchangeService.listTypeAchatEchangeByEchange(id);
    }
}
