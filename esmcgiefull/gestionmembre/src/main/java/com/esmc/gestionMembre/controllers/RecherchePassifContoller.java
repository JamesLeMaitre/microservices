package com.esmc.gestionMembre.controllers;


import com.esmc.gestionMembre.entities.AncienCompteCredit;
import com.esmc.gestionMembre.entities.Reclamation;
import com.esmc.gestionMembre.entities.RepartitionMF11000;
import com.esmc.gestionMembre.input.Result;
import com.esmc.gestionMembre.model.RechercheInputModel;
import com.esmc.gestionMembre.serviceInterfaces.RecherchePassifService;
import com.esmc.gestionMembre.services.EsmcSarluService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import utiles.DataFormatter;

import java.util.List;

@RestController
@RequestMapping("api/passif/")
public class RecherchePassifContoller extends DataFormatter<Object>  {



    @Autowired
    private RecherchePassifService recherchePassifService;




    @PostMapping("recherchePassif")
    public Object recherchePassif(@RequestBody RechercheInputModel rechercheInputModel) {


        //String type3 = rechercheInputModel.getPrenom() != null ? rechercheInputModel.getPrenom() : "0";
        String key = rechercheInputModel.getKey() != null ? rechercheInputModel.getKey() : "defaultKey";

        DataFormatter<Object> formatter = new DataFormatter<>();

        try {

            Result<Long, List<Object>> items =  recherchePassifService.getRechercheByCodeMemebre(rechercheInputModel);



            String value = items.getKey()+"";
            switch (value){
                case "0": return formatter.renderStringData(false, "", "Ce compte Marchand n'existe pas dans MCNP");
                case "1": return formatter.renderStringData(true, "", "Ce compte  n'existe pas");
                case "2": return formatter.renderDataArray(true, items.getValue(), "Passif trouvé ");
                case "3": return formatter.renderStringData(false, "", "Vous n'avez pas de passif");
                default: return formatter.renderStringData(false, "", "Ce Compte marchand ne vous appartient pas");
            }


           // return formatter.renderDataArray(true, items.va, "Opperation éffectuer avec Succès");

        } catch (Exception e) {
            return formatter.renderStringData(false, "", "Code ou nom et prenom du Membre erroné");
        }

    }



    @PostMapping("compteMarchand")
    public Object rechercheCompteMarchand(@RequestBody RechercheInputModel rechercheInputModel) {


        //String type3 = rechercheInputModel.getPrenom() != null ? rechercheInputModel.getPrenom() : "0";
        String key = rechercheInputModel.getKey() != null ? rechercheInputModel.getKey() : "defaultKey";



        try {

            //List<Object> items =  recherchePassifService.getRechercheByCodeMemebre(rechercheInputModel);

            Result<Long, Object> items =  recherchePassifService.rechercheCompteMarchand(rechercheInputModel);



            String value = items.getKey()+"";
            switch (value){
                case "0": return renderStringData(false, "", "Ce compte Marchand  n'existe pas dans Redemare");
                case "1": return renderStringData(true, "", "Ce compte Marchand  n'existe pas dans MCNP");
                case "2": return renderDataArray(true, (List<Object>) items.getValue(), "Compte marchand trouvé ");
                case "3": return renderStringData(false, "", "Ce compte Marchand  n'existe pas dans ESMC SARLU");
                default: return renderStringData(false, "", "Ce Compte marchand ne vous appartient pas");
            }


            // return formatter.renderDataArray(true, items.va, "Opperation éffectuer avec Succès");

        } catch (Exception e) {
            return renderStringData(false, "", "Code Membre erroné");
        }

    }
}
