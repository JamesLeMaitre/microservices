package com.esmc.gestionAchatFranchise.controllers;


import com.esmc.gestionAchatFranchise.entities.AgenceOdd;
import com.esmc.gestionAchatFranchise.entities.CentreFranchise;
import com.esmc.gestionAchatFranchise.services.CentreFranchiseImp;
import com.esmc.models.Formatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utiles.DataFormatter;

import java.util.List;

@RestController
@RequestMapping(value="/api/centreFranchise")
public class CentreFranchiseController extends DataFormatter<CentreFranchise> {
    @Autowired
    private CentreFranchiseImp centreFranchiseImp;


    @PostMapping("/add")
    public Object addCentreFranchise(@RequestBody CentreFranchise centreFranchise) {
        try{
            CentreFranchise centreFranchise1= centreFranchiseImp.addCentreFranchise(centreFranchise);
            return  renderData(true,centreFranchise1,"Agent odd created successfully");
        }catch (Exception e){
            return  renderStringData(false,e.getMessage(),"error");
        }
    }

    @GetMapping("/getAll")
    public Formatter<List<CentreFranchise>> getAllCentreFranchise(){
        return renderDataArray(true,centreFranchiseImp.getCentreFranchise(), "list of centre franchise");
    }

    @GetMapping("/get/{centreFranchiseId}")
    public Object getCentreFranchise(@PathVariable("centreFranchiseId") Long centreFranchiseId) {


        CentreFranchise centreFranchise1= centreFranchiseImp.getCentreFranchiseById(centreFranchiseId);
        if(centreFranchise1 == null){
            return renderStringData(false,"Error ","AgentOdd not found");
        }
        return renderData(true,centreFranchise1, "get AgentOdd");
    }

    @PutMapping("/update/{id}")

    public Object updateCentreFranchise(@PathVariable("id") Long id, @RequestBody CentreFranchise centreFranchise) {

        try{
            CentreFranchise centreFranchise1 = centreFranchiseImp.getCentreFranchiseById(id);
            if(centreFranchise1 == null){
                return renderStringData(false,"Error ","AgentOdd not found");
            }
            //centreFranchise.setId(centreFranchiseId);
            //agenceOddImp.updateAgenceOdd(agenceOddId, agenceOdd);
            return renderData(true,centreFranchiseImp.updateCentreFranchise(id,centreFranchise), "Updated successfully");
        }catch (Exception e){
            return  renderStringData(false,e.getMessage(),"error");
        }
    }

    @DeleteMapping("/delete/{centreFranchiseId}")
    public Formatter<String> deleteCentreFranchise(@PathVariable Long centreFranchiseId) {



        try{
            centreFranchiseImp.deleteCentreFranchise(centreFranchiseId);
            return renderStringData(true,"success","deleted successfully");
        }catch (Exception e){
            return  renderStringData(false,e.getMessage(),"error");
        }
    }

}

