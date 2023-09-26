package com.esmc.gestionAchatFranchise.controllers;


import com.esmc.gestionAchatFranchise.services.FonctionCantonImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/cantonfonction/")
public class FonctionCantonController {

    @Autowired
    private FonctionCantonImpl fonctionCanton;


    @GetMapping("canton/{montant}")
    public int nombreCantonParMontant(@PathVariable Double montant)  {

        return fonctionCanton.nombreCantonParMontant(montant);
    }

    @GetMapping("nbreCanton/{nombre}")
    public Double CantonParMontant(@PathVariable int nombre)  {

        return fonctionCanton.prixCantonParNbCanton(nombre);
    }
}
