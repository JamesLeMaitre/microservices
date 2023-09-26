package com.esmc.gestionKsu.controllers;

import com.esmc.gestionKsu.entities.Convention;
import com.esmc.gestionKsu.serviceinterfaces.ConventionServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/convention/")
public class ConventionController {

    @Autowired
    private ConventionServiceInterface conventionServiceInterface;


    @GetMapping("ksu/{idKsu}/save")
    public void saveConvention(@PathVariable  Long idKsu) {
        conventionServiceInterface.saveConvention(idKsu);
    }


    @PutMapping("edit/{id}")
    public void updateConvention(Long id) {

    }

    @GetMapping("getById/{id}")
    public Convention getConventionById(@PathVariable Long id) {
        return conventionServiceInterface.getConventionById(id);
    }


    @GetMapping("list")
    public List<Convention> getAllConvention() {
        return conventionServiceInterface.getAllConvention();
    }

    @GetMapping("list/ksu/{id}")
    public List<Convention> listConventionParKsu(@PathVariable Long id) {
        return conventionServiceInterface.listConventionParKsu(id);
    }

}
