package com.esmc.gestionAvr.controllers;

import com.esmc.gestionAvr.entities.ReferenceTokenOp;
import com.esmc.gestionAvr.inputs.AchatProduitInput;
import com.esmc.gestionAvr.services.AchatProduitPOp;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utiles.DataFormatter;

import java.io.PrintWriter;
import java.io.StringWriter;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("api/v1/")
public class AchatPreOpOnLineController{
    private final AchatProduitPOp achatProduitPOpService;

    @PostMapping(value = "achatProduitPreOperationnelleEnLigne")
    public Object ajouterAvr(@RequestBody AchatProduitInput data) throws Exception {
        DataFormatter<ReferenceTokenOp> dataFormatter = new DataFormatter<>();
        try {
            ReferenceTokenOp referencToken = achatProduitPOpService.achatProduitPreOpEnLigne(data);
            if(referencToken == null){
                return  dataFormatter.renderStringData(false, "","Your amount to buy this token is less ");
            }
            return  dataFormatter.renderData(true, referencToken,"Opération éffectuer avec succès !");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  dataFormatter.renderStringData(false,"Error while processing" ,exceptionAsString);
        }
    }
}
