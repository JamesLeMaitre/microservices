package com.esmc.gestionMembre.controllers;

import com.esmc.gestionMembre.entities.Credit;
import com.esmc.gestionMembre.entities.Place;
import com.esmc.gestionMembre.input.Result;
import com.esmc.gestionMembre.services.RedemareService;
import com.esmc.models.Formatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utiles.DataFormatter;

import java.util.List;

@RestController
@RequestMapping("api/redemare/")
public class RedemarePassifController {

    @Autowired
    private RedemareService redemareService;

    @GetMapping("creditCncs/{code}/idKsu/{idKsu}")
    public Object listCreditCncs(@PathVariable("code") String code, @PathVariable("idKsu") Long idKsu) {

        DataFormatter<Credit> formatter = new DataFormatter<>();

        try {


            Result<Long, List<Credit>> items = redemareService.getCreditCNCS(code, idKsu);

            System.out.println("========================================================");
            System.out.println(items.getValue());
            System.out.println("========================================================");

            String value = items.getKey()+"";
            switch (value){
                case "0": return formatter.renderStringData(false, "", "Ce Compte Marchand n'existe pas ");
                case "1": return formatter.renderDataArray(true, items.getValue(), "Opperation éffectuer avec Succès ");
                case "2": return formatter.renderStringData(false, "", "Vous n'avez pas de CNCS");
                case "3": return formatter.renderStringData(false, "", "Opperation éffectuer avec Succès");
                default: return formatter.renderStringData(false, "", "Ce compte ne vous appartient pas");
            }

        } catch (Exception e) {
            return formatter.renderStringData(false, "", "Code Membre erroné");
        }
    }

    @GetMapping("placeCnp/{code}/idKsu/{idKsu}")
    public Object listPlacCnp(@PathVariable("code") String code, @PathVariable("idKsu") Long idKsu) {

        DataFormatter<Place> formatter = new DataFormatter<>();

        try {


        Result<Long, List<Place>> items = redemareService.getPlaceCnp(code, idKsu);

            System.out.println("=======================================================================");

            System.out.println(items);

            System.out.println("=======================================================================");
            String value = items.getKey()+"";
            switch (value){
                case "0": return formatter.renderStringData(false, "", "Ce Compte Marchand n'existe pas ");
                case "1": return formatter.renderDataArray(true, items.getValue(), "Opperation éffectuer avec Succès");
                case "2": return formatter.renderStringData(false, "", "Vous n'avez pas de CNP");
                case "3": return formatter.renderDataArray(true, items.getValue(), "Opperation éffectuer avec Succès ");
                case "4": return formatter.renderStringData(false, "", "Vous n'avez pas de CNP ");
                case "5": return formatter.renderStringData(false, "", "Operation impossible");
                case "6": return formatter.renderStringData(false, "", "Operation impossible ");
                case "7": return formatter.renderStringData(false, "", "Ce Compte Marchand n'existe pas ");
                default: return formatter.renderStringData(false, "", "Ce compte ne vous appartient pas");
            }
        } catch (Exception e) {
            return formatter.renderStringData(false, "", "Code Membre erroné "+e);
        }
    }

}
