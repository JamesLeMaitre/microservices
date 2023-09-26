package com.esmc.gestionAvr.controllers;

import com.esmc.gestionAvr.entities.Extrant;
import com.esmc.gestionAvr.entities.TypeEchange;
import com.esmc.gestionAvr.servicesInterfaces.TypeEchangeServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utiles.DataFormatter;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

@RestController
@RequestMapping(path = "api/typeEchange/")
public class TypeEchangeController extends DataFormatter<TypeEchange> {
    @Autowired
    private TypeEchangeServiceInterface typeAchatServiceInterface;

    /*
     * Function to save TypeEchange
     * call the route save
     * */
    @PostMapping(value = "save")
    public TypeEchange ajouterTypeAchat(@RequestBody TypeEchange a) {
        return typeAchatServiceInterface.addTypeAchat(a);
    }

    /*
     * Function to edit TypeEchange
     * call the route edit and add the id of the object
     * */

    @GetMapping(value = "edit/{id}")
    public ResponseEntity<TypeEchange> modifierTypeAchats(@PathVariable Long id, @RequestBody TypeEchange t) {
        return new ResponseEntity<TypeEchange>(typeAchatServiceInterface.updateTypeAchat(id, t), HttpStatus.OK);
    }

    /*
     * Function to delete TypeEchange
     * call the route delete and add the id of the object
     *
     * */

    @DeleteMapping(value = "delete/{id}")
    public void supprimerTypeAchats(@PathVariable Long id) {
        typeAchatServiceInterface.deleteTypeAchat(id);
    }

    @GetMapping(value = "typeEchange/{id}")
    public TypeEchange getTypeEchange(@PathVariable Long id){
        return typeAchatServiceInterface.getTypeEchange( id);
    }

    /*
     * Function to get all TypeEchange
     * call the route list
     * */
    @GetMapping(value = "list")
    public Object listeTypeAchats() {
        try {
            List<TypeEchange> items = typeAchatServiceInterface.getAllTypeAchat();
            return  renderDataArray(true,items,"list all");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }

    }

   

}
