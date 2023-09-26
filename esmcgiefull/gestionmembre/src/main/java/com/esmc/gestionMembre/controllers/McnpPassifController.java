package com.esmc.gestionMembre.controllers;

import com.esmc.gestionMembre.entities.*;
import com.esmc.gestionMembre.input.McnpCncs;
import com.esmc.gestionMembre.input.Result;
import com.esmc.gestionMembre.repositories.*;
import com.esmc.gestionMembre.serviceInterfaces.McnpServiceInterface;
import com.esmc.gestionMembre.services.McnpService;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utiles.DataFormatter;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/mcnp/")
public class McnpPassifController {

    @Autowired
    private McnpServiceInterface mcnpService;

    @GetMapping("mf11000/{code}/idKsu/{idKsu}")
    public Object listPassifRepMf1100(@PathVariable("code") String code, @PathVariable Long idKsu) {

        DataFormatter<RepartitionMF11000> formatter = new DataFormatter<>();
        try {
            Result<Long, List<RepartitionMF11000>> items =  mcnpService.listPassifRepMf1100(code, idKsu);

            String value = items.getKey()+"";
            switch (value){
                case "0": return formatter.renderStringData(false, "", "Ce compte Marchand n'existe pas dans MCNP");
                case "1": return formatter.renderStringData(true, "", "Ce Ksu n'existe pas");
                case "2": return formatter.renderDataArray(true, items.getValue(), "Opperation éffectuer avec Succès");
                case "3": return formatter.renderStringData(false, "", "Vous n'avez pas de Mf11000");
                default: return formatter.renderStringData(false, "", "Ce compte Marchant ne vous appartient pas");
            }

        } catch (Exception e) {
            return formatter.renderStringData(false, "", "Erreur au niveau du serveur");
        }

    }


    @GetMapping("mf107/{code}/idKsu/{idKsu}")
    public Object passifsMCNPmembreFondateurRepartitionMf107(@PathVariable("code") String code, @PathVariable Long idKsu) {

        DataFormatter<RepartitionMf107> formatter = new DataFormatter<>();

        try {

            Result<Long, List<RepartitionMf107>> items =  mcnpService.passifsMCNPmembreFondateurRepartitionMf107(code, idKsu);

            String value = items.getKey()+"";
            switch (value){
                case "0": return formatter.renderStringData(false, "", "Ce compte Marchand n'existe pas dans MCNP");
                case "1": return formatter.renderStringData(true, "", "Ce Ksu n'existe pas");
                case "2": return formatter.renderDataArray(true, items.getValue(), "Opperation éffectuer avec Succès");
                case "3": return formatter.renderStringData(false, "", "Vous ne disposez pas de MF107");
                case "4": return formatter.renderStringData(false, "", "Ce Ksu n'existe pas");
                case "5": return formatter.renderDataArray(true, items.getValue(), "Opperation éffectuer avec Succès ");
                case "6": return formatter.renderStringData(false, "", "Vous ne disposez pas de Mf107");
                default: return formatter.renderStringData(false, "", "Ce compte Marchant ne vous appartient pas");
            }
        } catch (Exception e) {
            return formatter.renderStringData(false, "", "Erreur au niveau du serveur");
        }
    }

    @GetMapping("membreFondateur/pm/{code}")
    public List<AncienDetailsSmsmoney> passifsMCNPmembreFondateurAncienDetailsSmsmoney(@PathVariable("code") String code) {
        return mcnpService.passifsMCNPmembreFondateurAncienDetailsSmsmoney(code);
    }

//    @GetMapping("passifsMCNPAncienCompteCredit/{code}")
//    public AncienCompteCredit passifsMCNPAncienCompteCredit(@PathVariable("code") String code) {
//        return mcnpService.passifsMCNPAncienCompteCredit(code);
//    }


    @GetMapping("rpgr/{code}/idKsu/{idKsu}")
    public Object listRPGr(@PathVariable("code") String code, @PathVariable Long idKsu) {

        DataFormatter<AncienCompteCredit> formatter = new DataFormatter<>();

        try {

            Result<Long, List<AncienCompteCredit>> items =  mcnpService.listRPGr(code, idKsu);

            String value = items.getKey()+"";
            switch (value){
                case "0": return formatter.renderStringData(false, "", "Ce compte Marchand n'existe pas dans MCNP");
                case "1": return formatter.renderStringData(true, "", "Ce Ksu n'existe pas");
                case "2": return formatter.renderDataArray(true, items.getValue(), "Opperation éffectuer avec Succès");
                case "3": return formatter.renderStringData(false, "", "Vous ne disposer pas RPGr");
                case "4": return formatter.renderStringData(false, "", "Ce Ksu n'existe pas");
                case "5": return formatter.renderDataArray(true, items.getValue(), "OOpperation éffectuer avec Succès ");
                case "6": return formatter.renderStringData(false, "", "Vous ne disposer pas RPGr ");
                default: return formatter.renderStringData(false, "", "Ce Compte marchand ne vous appartient pas");
            }

        } catch (Exception e) {
            return formatter.renderStringData(false, "", "Code Membre erroné");
        }

    }

    @GetMapping("rpgnr/{code}/idKsu/{idKsu}")
    public Object listRPGnr(@PathVariable("code") String code, @PathVariable Long idKsu) {

        DataFormatter<AncienCompteCredit> formatter = new DataFormatter<>();

        try {

            Result<Long, List<AncienCompteCredit>> items =  mcnpService.listRPGnr(code, idKsu);

            String value = items.getKey()+"";
            switch (value){
                case "0": return formatter.renderStringData(false, "", "Ce compte Marchand n'existe pas dans MCNP");
                case "1": return formatter.renderStringData(true, "", "Ce Ksu n'existe pas");
                case "2": return formatter.renderDataArray(true, items.getValue(), "Opperation éffectuer avec Succès ");
                case "3": return formatter.renderStringData(false, "", "Vous n'avez pas de RPGnr");
                case "4": return formatter.renderStringData(false, "", "Ce Ksu n'existe pas");
                case "5": return formatter.renderDataArray(true, items.getValue(), "Opperation éffectuer avec Succès");
                case "6": return formatter.renderStringData(false, "", "Vous n'avez pas de RPGnr ");
                default: return formatter.renderStringData(false, "", "Ce Compte marchand ne vous appartient pas");
            }

        } catch (Exception e) {
            return formatter.renderStringData(false, "", "Code Membre erroné");
        }

    }

    @GetMapping("ir/{code}/idKsu/{idKsu}")
    public Object listIr(@PathVariable("code") String code, @PathVariable Long idKsu) {


        DataFormatter<AncienCompteCredit> formatter = new DataFormatter<>();

        try {
            Result<Long, List<AncienCompteCredit>> items =  mcnpService.listIr(code, idKsu);

            String value = items.getKey()+"";
            switch (value){
                case "0": return formatter.renderStringData(false, "", "Ce compte Marchand n'existe pas dans MCNP");
                case "1": return formatter.renderStringData(true, "", "Ce Ksu n'existe pas");
                case "2": return formatter.renderDataArray(true, items.getValue(), "Opperation éffectuer avec Succès ");
                case "3": return formatter.renderStringData(false, "", "Vous n'avez pas de Ir");
                case "4": return formatter.renderStringData(false, "", "Ce Ksu n'existe pas");
                case "5": return formatter.renderDataArray(true, items.getValue(), "Opperation éffectuer avec Succès");
                case "6": return formatter.renderStringData(false, "", "Vous n'avez pas de Ir ");
                default: return formatter.renderStringData(false, "", "Ce Compte marchand ne vous appartient pas");
            }

        } catch (Exception e) {
            return formatter.renderStringData(false, "", "Code Membre erroné");
        }

    }

    @GetMapping("inr/{code}/idKsu/{idKsu}")
    public Object listInr(@PathVariable("code") String code, @PathVariable Long idKsu) {

        DataFormatter<AncienCompteCredit> formatter = new DataFormatter<>();

        try {

            Result<Long, List<AncienCompteCredit>> items =  mcnpService.listInr(code, idKsu);

            String value = items.getKey()+"";
            switch (value){
                case "0": return formatter.renderStringData(false, "", "Ce compte Marchand n'existe pas dans MCNP");
                case "1": return formatter.renderStringData(true, "", "Ce Ksu n'existe pas");
                case "2": return formatter.renderDataArray(true, items.getValue(), "Opperation éffectuer avec Succès");
                case "3": return formatter.renderStringData(false, "", "Vous n'avez pas de Inr");
                case "4": return formatter.renderStringData(false, "", "Ce Ksu n'existe pas");
                case "5": return formatter.renderDataArray(true, items.getValue(), "Opperation éffectuer avec Succès ");
                case "6": return formatter.renderStringData(false, "", "Vous n'avez pas de Inr ");
                default: return formatter.renderStringData(false, "", "Ce Compte marchand ne vous appartient pas");
            }

        } catch (Exception e) {
            return "Error processing "+e;
        }

    }

    @GetMapping("gcp/{code}")
    public List<AncienGcp> listpassifMCNPGcp(@PathVariable("code") String code) {
        return mcnpService.listpassifMCNPGcp(code);
    }

    @GetMapping("somme/gcp/{code}/idKsu/{idKsu}")
    public Object sommepassifsMCNPGcp(@PathVariable("code") String code, @PathVariable Long idKsu) {

            DataFormatter<AncienEchange> formatter = new DataFormatter<>();

        try {
            Double items =  mcnpService.sommepassifsMCNPGcp(code, idKsu);

            if (items == null) {
                return formatter.renderStringData(false, "", "Ce Compte marchand ne vous appartient pas");
            }

            return formatter.renderDoubleData(true, items, "Opperation éffectuer avec Succès ");
        } catch (Exception e) {
            return formatter.renderStringData(false, "", "Code Membre erroné");
        }


    }

    @GetMapping("somme/echange/NbNb/{code}/idKsu/{idKsu}")
    public Object sommepassifsMCNPAncienEchangeNbContreNb(@PathVariable("code") String code, @PathVariable Long idKsu) {

        DataFormatter<AncienEchange> formatter = new DataFormatter<>();

        try {
            Double items =  mcnpService.sommepassifsMCNPAncienEchangeNbContreNb(code, idKsu);

            if (items == null) {
                return formatter.renderStringData(false, "", "Ce Compte marchand ne vous appartient pas");
            }

            return formatter.renderDoubleData(true, items, "Opperation éffectuer avec Succès ");
        } catch (Exception e) {
            return formatter.renderStringData(false, "", "Code Membre erroné");
        }


    }

    @GetMapping("somme/echange/NbNn/{code}/idKsu/{idKsu}")
    public Object sommepassifsMCNPAncienEchangeNbContreNn(@PathVariable("code") String code, @PathVariable Long idKsu) {
        DataFormatter<AncienEchange> formatter = new DataFormatter<>();
        try {
           Double item = mcnpService.sommepassifsMCNPAncienEchangeNbContreNn(code, idKsu);

            if (item == null) {
                return formatter.renderStringData(false, "", "Ce compte ne vous appartient pas");
            }
            return formatter.renderDoubleData(true, item, "Opperation éffectuer avec Succès ");
        } catch (Exception e) {
            return formatter.renderStringData(false, "", "Code Membre erroné");
        }

    }

    @GetMapping("echange/NbNb/{code}")
    public List<AncienEchange> getpassifsMCNPAncienEchangeNbContreNb(@PathVariable("code") String code) {
        return mcnpService.getpassifsMCNPAncienEchangeNbContreNb(code);
    }

    @GetMapping("echange/NbNn/{code}")
    public List<AncienEchange> getpassifsMCNPAncienEchangeNbContreNn(@PathVariable("code") String code) {
        return mcnpService.getpassifsMCNPAncienEchangeNbContreNn(code);
    }



    @GetMapping("cncs/{code}/idKsu/{idKsu}")
    public Object getPassifCss(@PathVariable("code") String code, @PathVariable Long idKsu) {
        DataFormatter<JSONObject> formatter = new DataFormatter<>();
        try {
            List<Object> result = new ArrayList<>();

            List<Object> results = new ArrayList<>();

            McnpCncs mcnpCncs = new McnpCncs();

            Result<Long, List<AncienCompte>> lista1 = mcnpService.getPassifCncsMorale(code, idKsu);
            List<AncienCompte> lista = getResultValue(lista1);

            Result<Long, List<AncienCompteCredit>> listb1 = mcnpService.getPassifCncsPhysique(code, idKsu);

            List<AncienCompteCredit> listb = getResultValue(listb1);

            Result<Long, List<AncienEchange>> listc1 = mcnpService.getPassifCncsEchange(code, idKsu);
            List<AncienEchange> listc = getResultValue(listc1);

            Result<Long, List<AncienCompteCredit>> listd1 = mcnpService.getPassifCncs(code, idKsu);

            List<AncienCompteCredit> listd = getResultValue(listd1);

            List<JSONObject> list = new ArrayList<>();

            if ( listb.size() > 0 ) {
                list.addAll(formatAncienCompteCredit(listb));
            }
            if (lista.size() > 0 ) {
                list.addAll(formatAncienCompte(lista));
            }
            if ( listc.size() > 0) {
                list.addAll(formatAncienEchange(listc));
            }
            if (listd.size() > 0) {
                list.addAll(formatAncienCompteCreditEchanche(listd));
            }

            return formatter.renderDataArray(true, list, "Opperation éffectuer avec Succès");

        } catch (Exception e) {
            return formatter.renderStringData(false, "", "Ce Compte Marchand ne vous appartient pas");
        }

    }


    public <T> List<T> getResultValue(Result<Long, List<T>> data) throws Exception {

        List<T> tab = new ArrayList<>();

        if (data.getKey() == 2 || data.getKey() == 5) {
            return data.getValue();
        }
//        else {
//            System.out.println("==============================03========================");
//            throw new Exception("Your are not the owner");
//        }
          else {
            return tab;
        }
    }

    public List<JSONObject> formatAncienCompte(List<AncienCompte> lista) {
        List<JSONObject> list = new ArrayList<>();

        for (AncienCompte a : lista) {
            JSONObject o = new JSONObject();
            o.put("Produit", "CNCS");
            o.put("Montant", a.getSolde());
            o.put("Date", a.getDate_alloc());
            list.add(o);
        }
        return list;
    }

    public List<JSONObject> formatAncienCompteCredit(List<AncienCompteCredit> lista) {
        List<JSONObject> list = new ArrayList<>();

        for (AncienCompteCredit a : lista) {
            JSONObject o = new JSONObject();
            o.put("Produit", a.getCodeProduit());
            o.put("Montant", a.getMontantPlace());
            o.put("Date", a.getDateOctroi());
            list.add(o);
        }
        return list;
    }

    public List<JSONObject> formatAncienEchange(List<AncienEchange> lista) {
        List<JSONObject> list = new ArrayList<>();

        for (AncienEchange a : lista) {
            JSONObject o = new JSONObject();
            o.put("Produit", a.getCatEchange());
            o.put("Montant", a.getMontant());
            o.put("Date", a.getDateEchange());
            list.add(o);
        }
        return list;
    }

    public List<JSONObject> formatAncienCompteCreditEchanche(List<AncienCompteCredit> lista) {
        List<JSONObject> list = new ArrayList<>();

        for (AncienCompteCredit a : lista) {
            JSONObject o = new JSONObject();
            o.put("Produit", a.getCodeProduit());
            o.put("Montant", a.getMontantPlace());
            o.put("Date", a.getDateOctroi());
            list.add(o);
        }
        return list;
    }

}
