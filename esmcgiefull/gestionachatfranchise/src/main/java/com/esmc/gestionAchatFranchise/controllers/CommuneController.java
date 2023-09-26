package com.esmc.gestionAchatFranchise.controllers;

import com.esmc.gestionAchatFranchise.entities.AgenceOdd;
import com.esmc.gestionAchatFranchise.entities.Commune;
import com.esmc.gestionAchatFranchise.services.CommuneImp;
import com.esmc.models.Formatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utiles.DataFormatter;

import java.util.List;

@RestController
@RequestMapping(value="/api/commune")
public class CommuneController extends DataFormatter<Commune> {

    @Autowired
    private CommuneImp communeImp;

    @PostMapping("/save")
    public Object addCommune(@RequestBody Commune commune) {


        try{
            Commune commune1= communeImp.addCommune(commune);
            return  renderData(true,commune1,"Agent odd created successfully");
        }catch (Exception e){
            return  renderStringData(false,e.getMessage(),"error");
        }
    }

    @GetMapping("/getAll")
    public Formatter<List<Commune>> getAllCommune(){
        return renderDataArray(true,communeImp.getCommune(),"list of commune");
    }

    @GetMapping("/get/{communeId}")
    public Object getCommune(@PathVariable("communeId") Long communeId) {


        Commune commune1 = communeImp.getCommuneById(communeId);
        if(commune1 == null){
            return renderStringData(false,"Error ","AgentOdd not found");
        }
        return renderData(true,commune1, "get AgentOdd");
    }


    @PutMapping("/update/{communeId}")
    public Object updateCommune(@PathVariable("communeId") Long communeId, @RequestBody Commune commune) {



        try{
            Commune commune1 = communeImp.getCommuneById(communeId);
            if(commune1 == null){
                return renderStringData(false,"Error ","AgentOdd not found");
            }
            commune.setId(communeId);
            //agenceOddImp.updateAgenceOdd(agenceOddId, agenceOdd);
            return renderData(true,communeImp.updateCommune(commune), "Updated successfully");
        }catch (Exception e){
            return   renderStringData(false,e.getMessage(),"error");
        }
    }

    @DeleteMapping("/delete/{communeId}")
    public Formatter<String> deleteCommune(@PathVariable Long communeId) {


        try{
            communeImp.deleteCommune(communeId);
            return renderStringData(true,"success","deleted successfully");
        }catch (Exception e){
            return  renderStringData(false,e.getMessage(),"error");
        }
    }

    @GetMapping("/list/{id}/prefecture")
    public Formatter<List<Commune>> listCommuneParPrefecture(@PathVariable Long id) {
        return renderDataArray(true,communeImp.listPrefecture(id),"list prefecture by id");
    }

}
