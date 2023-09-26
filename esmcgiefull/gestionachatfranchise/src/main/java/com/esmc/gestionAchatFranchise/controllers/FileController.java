package com.esmc.gestionAchatFranchise.controllers;

import com.esmc.gestionAchatFranchise.services.FileImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/fil")
public class FileController {
/*
    @Autowired
    private FileImpl fileImpl;

    @GetMapping("/continent/{id}")
    public Double nombreContinent (@PathVariable Long id) {

        return fileImpl.filContinent(id);
    }
    @GetMapping("/zone/{id}")
    public Double nombreZone (@PathVariable Long id) {

        return fileImpl.filZoneMonetaire(id);
    }

    @GetMapping("/pays/{id}")
    public Double nombrePays (@PathVariable Long id) {

        return fileImpl.filPays(id);
    }
    @GetMapping("/region/{id}")
    public Double nombreRegion (@PathVariable Long id) {

        return fileImpl.filRegion(id);
    }
    @GetMapping("/prefecture/{id}")
    public Double nombrePrefecture (@PathVariable Long id) {

        return fileImpl.filPrefecture(id);
    }
    @GetMapping("/commune/{id}")
    public Double nombreCommune (@PathVariable Long id) {

        return fileImpl.filCommune(id);
    }
    @GetMapping("/canton/{id}")
    public Double nombreCanton(@PathVariable Long id) {

        return fileImpl.filCanton(id);
    }*/

}
