package com.esmc.gestionAchatFranchise.controllers;

import com.esmc.gestionAchatFranchise.entities.AgenceOdd;
import com.esmc.gestionAchatFranchise.entities.ChaineValeur;
import com.esmc.gestionAchatFranchise.services.ChaineValeurImp;
import com.esmc.models.Formatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utiles.DataFormatter;

import java.util.List;

@RestController
@RequestMapping(value="/api/chaineValeur")
public class ChaineValeurController extends DataFormatter<ChaineValeur> {

    @Autowired
    private ChaineValeurImp chaineValeurImp;

    @PostMapping("/save")
    public Object addChaineValeur(@RequestBody ChaineValeur chaineValeur) {
        try{
            ChaineValeur chaineValeur1= chaineValeurImp.addChaineValeur(chaineValeur);
            return  renderData(true,chaineValeur1,"Agent odd created successfully");
        }catch (Exception e){
            return  renderStringData(false,e.getMessage(),"error");
        }
    }

    @GetMapping("/getAll")
    public Formatter<List<ChaineValeur>> getAllChaineValeur(){

        return renderDataArray(true,chaineValeurImp.getChaineValeur(),"liste chainedevaleur");
    }

    @GetMapping("/search/{chaineValeurId}")
    public Object getChaineValeur(@PathVariable("chaineValeurId") Long chaineValeurId) {
        ChaineValeur chaineValeur1 = chaineValeurImp.getChaineValeurById(chaineValeurId);
        if(chaineValeur1 == null){
            return renderStringData(false,"Error ","AgentOdd not found");
        }
        return renderData(true,chaineValeur1, "get AgentOdd");
    }

    @PutMapping("/update/{chaineValeurId}")

    public Object updateChaineValeur(@PathVariable("chaineValeurId") Long chaineValeurId, @RequestBody ChaineValeur chaineValeur) {

        try{
            ChaineValeur chaineValeur1 = chaineValeurImp.getChaineValeurById(chaineValeurId);
            if(chaineValeur1 == null){
                return renderStringData(false,"Error ","AgentOdd not found");
            }
            chaineValeur.setId(chaineValeurId);
            //agenceOddImp.updateAgenceOdd(agenceOddId, agenceOdd);
            return renderData(true,chaineValeurImp.updateChaineValeur(chaineValeur), "Updated successfully");
        }catch (Exception e){
            return  renderStringData(false,e.getMessage(),"error");
        }

    }

    @DeleteMapping("/delete/{chaineValeurId}")
    public Formatter<String> deleteChaineValeur(@PathVariable Long chaineValeurId) {


        try{
            chaineValeurImp.deleteChaineValeur(chaineValeurId);
            return renderStringData(true,"success","deleted successfully");
        }catch (Exception e){
            return  renderStringData(false,e.getMessage(),"error");
        }

    }

    @GetMapping("/search/{id}/detailDestination")
    public Formatter<List<ChaineValeur>> listChaineValeurParDetailDestination(@PathVariable Long id) {
        return renderDataArray(true,chaineValeurImp.listDetailDestination(id),"chaine de valeur by id destination");
    }


}
