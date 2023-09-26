package com.esmc.gestionMembre.controllers;

import com.esmc.gestionMembre.entities.*;
import com.esmc.gestionMembre.services.EsmcSarluService;
import com.esmc.models.Settings;
import constants.SettingsConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utiles.DataFormatter;
import utiles.Fonctions;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/esmcSarlu/")
public class EsmcSarluPassifController {

    @Autowired
    private EsmcSarluService esmcSarluService;

    @GetMapping("souscriptionCmfh/{code}/idKsu/{idKsu}")
    public Object getSouscrition(@PathVariable("code") String code, @PathVariable Long idKsu) {

        DataFormatter<Souscription> formatter = new DataFormatter<>();

        try {
            List<Souscription> items = esmcSarluService.getSouscrition(code, idKsu);

            if (items == null) {
                return formatter.renderStringData(false, "", "Ce compte Marchand ne vous appartient pas");
            }

            return formatter.renderDataArray(true, items, "Opperation éffectuer avec Succès");
        } catch (Exception e) {
            return formatter.renderStringData(false, "", "Code Membre erroné");
        }


    }

    @GetMapping("sommeCmfh/{code}")
    public Double sommeSouscritionPassifys(@PathVariable("code") String code) {
        return esmcSarluService.sommeSouscritionPassifs(code);
    }

    @GetMapping("ban/{code}/idKsu/{idKsu}")
    public Object passifsESMCSARLUBonNeutre(@PathVariable("code") String code , @PathVariable Long idKsu) {



        DataFormatter<BonNeutreDetail> formatter = new DataFormatter<>();

        try {
            List<BonNeutreDetail> items = esmcSarluService.passifsESMCSARLUBonNeutre(code, idKsu);

            if (items == null) {
                return formatter.renderStringData(false, "", "Ce compte Marchand ne vous appartient pas");
            }

            return formatter.renderDataArray(true, items, "Opperation éffectuer avec Succès ");
        } catch (Exception e) {
            return formatter.renderStringData(false, "", "Code Membre erroné");
        }
    }

    @GetMapping("rpgnr/{code}/idKsu/{idKsu}")
    public Object getPassifRpgNr(@PathVariable("code") String code, @PathVariable Long idKsu){

        DataFormatter<CompteCredit> formatter = new DataFormatter<>();

        try {
            List<CompteCredit> items = esmcSarluService.passifsEsmcSarluRpgNr(code, idKsu);

            if (items == null) {
                return formatter.renderStringData(false, "", "Ce compte Marchand ne vous appartient pas");
            }

            return formatter.renderDataArray(true, items, "Opperation éffectuer avec Succès ");
        } catch (Exception e) {
            return formatter.renderStringData(false, "", "Code Membre erroné");
        }



    }

    @GetMapping("rpgr/{code}/idKsu/{idKsu}")
    public Object getPassifRpgr(@PathVariable("code") String code, @PathVariable Long idKsu){

        DataFormatter<CompteCredit> formatter = new DataFormatter<>();

        try {
            List<CompteCredit> items = esmcSarluService.passifsEsmcSarluRpgr(code, idKsu);

            if (items == null) {
                return formatter.renderStringData(false, "", "Ce compte Marchand ne vous appartient pas");
            }

            return formatter.renderDataArray(true, items, "Opperation éffectuer avec Succès ");
        } catch (Exception e) {
            return formatter.renderStringData(false, "", "Code Membre erroné");
        }

    }

    @GetMapping("inr/{code}/idKsu/{idKsu}")
    public Object getPassifInr(@PathVariable("code") String code, @PathVariable Long idKsu){

        DataFormatter<CompteCredit> formatter = new DataFormatter<>();

        List<CompteCredit> items =  esmcSarluService.passifsEsmcSarluInr(code, idKsu);

        if (items == null) {
            return formatter.renderStringData(false, "", "Ce compte Marchand ne vous appartient pas");
        }

        return formatter.renderDataArray(true, items, "Opperation éffectuer avec Succès ");

    }

    @GetMapping("gcp/{code}/idKsu/{idKsu}")
    public Object listpassifsESMCSARLUGcp(@PathVariable("code") String code, @PathVariable Long idKsu){

        DataFormatter<Gcp> formatter = new DataFormatter<>();

        try {
            List<Gcp> items =  esmcSarluService.getpassifsESMCSARLUGcp(code, idKsu);

            if (items == null) {
                return formatter.renderStringData(false, "", "Ce compte Marchand ne vous appartient pas");
            }

            return formatter.renderDataArray(true, items, "Opperation éffectuer avec Succès");

        } catch (Exception e) {
            return formatter.renderStringData(false, "", "Code Membre erroné");
        }


    }

    @GetMapping("opi/{code}/idKsu/{idKsu}")
    public Object listpassifsESMCSARLUOpi(@PathVariable("code") String code, @PathVariable Long idKsu){

        DataFormatter<Map<String,Object>> formatter = new DataFormatter<>();

        try {
            List<Map<String,Object>> items =  esmcSarluService.getAllOpiByMembre(code, idKsu);
            if (items == null) {
                return formatter.renderStringData(false, "", "Ce compte Marchand ne vous appartient pas");
            }
            return formatter.renderDataArray(true, items, "Opperation éffectuer avec Succès ");
        } catch (Exception e) {
            return formatter.renderStringData(false, "", "Code Membre erroné");
        }
    }

}
