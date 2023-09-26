package com.esmc.gestionAvr.tokens.controllers;

import com.esmc.gestionAvr.tokens.entities.ReferencToken;
import com.esmc.gestionAvr.tokens.model.ReferenceModel;
import com.esmc.gestionAvr.tokens.model.TokenEntity;
import com.esmc.gestionAvr.tokens.models.TokenInput;
import com.esmc.gestionAvr.tokens.services.ReferencTokenInterface;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import utiles.DataFormatter;

import java.util.List;


@RestController
@RequestMapping("api/tokenref/")
@AllArgsConstructor
public class ReferencTokenController extends DataFormatter<ReferencToken> {
    @Autowired
    private ReferencTokenInterface referencTokenInterface;



    @PostMapping("save")
    public ReferencToken encrypt(@RequestBody TokenEntity tokenEntitie) throws Exception {

        return referencTokenInterface.creatRef(tokenEntitie);

    }
    @GetMapping("getById/{id}")
    public Object finById(@PathVariable("id") Long id) {

        ReferencToken item = referencTokenInterface.getById(id);

        if(item == null) {
            return renderStringData(false, "", "Data not found");
        }
        return renderData(true, item, "Opération éffectuer avec succès");


    }
    @GetMapping("getByIdDetailAgr/{id}")
    public Object findByIdAvr(@PathVariable("id") Long id)  {

        DataFormatter<ReferencToken> affec=new DataFormatter<>();
        List<ReferencToken> item = referencTokenInterface.getByIdAVR(id);
        try {
            return  affec.renderDataArray(true, item, "Operation Successful");
        } catch  (Exception w) {
            return w.getMessage();
        }


    }


    @GetMapping("code/{code}")
    public Object getBYcode(@PathVariable("code") String code) {

        ReferencToken item = referencTokenInterface.getByCoderef(code);

        if(item == null) {
            return renderStringData(false, "", "Data not found");
        }
        return renderData(true, item, "Opération éffectuer avec succès");


    }
    @GetMapping("codeFront/{code}")
    public Object getBYReferenceFront(@PathVariable("code") String code) {

        ReferenceModel item = referencTokenInterface.getByRefTokenFrontref(code);

        if(item == null) {
            return renderStringData(false, "i", "Data not found");
        }
        return  item;

    }




    @GetMapping("codeFront/{tokenCode}/input/{submitValue}")
    public Object getAndCheckTokenAmount(@PathVariable("tokenCode") String tokenCode, @PathVariable("submitValue") Long submitValue){
        Boolean   finalData = referencTokenInterface.getByRefTokenFromFrontAndCheckAmount(tokenCode,submitValue);
        if(!finalData){
            return renderStringData(false, "", "the price enter exceeds the value of the token");
        }
        else {
            return renderStringData(true, "", "you can proceed");
        }

    }

    @GetMapping("list")
    public Object List() {
        try {
            List<ReferencToken> items = referencTokenInterface.getAll();
            return renderDataArray(true, items, "Operation Successful");
        } catch  (Exception w) {
            return "error processing";
        }
    }

    @PostMapping("fusion/idDetailAgr")
    public Object fusionnerToken(@RequestBody TokenInput input) {
        try {
            ReferencToken item = referencTokenInterface.fusionToken(input.getIdReferencToken(),input.getIdDetailAgr());
            return renderData(true, item, "Operation Successful");
        } catch  (Exception w) {
            return "error processing";
        }
    }
}
