package com.esmc.gestionAchatFranchise.controllers;

import com.esmc.gestionAchatFranchise.entities.AgenceOdd;
import com.esmc.gestionAchatFranchise.entities.ChaineDistribution;
import com.esmc.gestionAchatFranchise.services.ChaineDistributionImp;
import com.esmc.models.Formatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utiles.DataFormatter;

import java.util.List;

@RestController
@RequestMapping(value="/api/chaineDistribution")
public class ChaineDistributionController extends DataFormatter<ChaineDistribution> {
    @Autowired
    private ChaineDistributionImp chaineDistributionImp;

    @PostMapping("/add")
    public Object addChaineDistribution(@RequestBody ChaineDistribution chaineDistribution) {

        try{
            ChaineDistribution chaineDistribution1= chaineDistributionImp.addChaineDistribution(chaineDistribution);
            return  renderData(true,chaineDistribution1,"Agent odd created successfully");
        }catch (Exception e){
            return  renderStringData(false,e.getMessage(),"error");
        }
    }

    @GetMapping("/getAll")
    public Formatter<List<ChaineDistribution>> getAllChaineDistribution(){
        return renderDataArray(true,chaineDistributionImp.getChaineDistribution(), "list of chaineDistribution");
    }

    @GetMapping("/get/{chaineDistributionId}")
    public Object getChaineDistribution(@PathVariable("chaineDistributionId") Long chaineDistributionId) {

        ChaineDistribution chaineDistribution1 = chaineDistributionImp.getChaineDistributionById(chaineDistributionId);
        if(chaineDistribution1 == null){
            return renderStringData(false,"Error ","AgentOdd not found");
        }
        return renderData(true,chaineDistribution1, "get AgentOdd");
    }

    @PutMapping("/update/{chaineDistributionId}")

    public Object updateChaineDistribution(@PathVariable("chaineDistributionId") Long chaineDistributionId, @RequestBody ChaineDistribution chaineDistribution) {



        try{
            ChaineDistribution chaineDistribution1 = chaineDistributionImp.getChaineDistributionById(chaineDistributionId);
            if(chaineDistribution1 == null){
                return renderStringData(false,"Error ","AgentOdd not found");
            }
            chaineDistribution.setId(chaineDistributionId);
            //agenceOddImp.updateAgenceOdd(agenceOddId, agenceOdd);
            return renderData(true,chaineDistributionImp.updateChaineDistribution(chaineDistribution), "Updated successfully");
        }catch (Exception e){
            return  renderStringData(false,e.getMessage(),"error");
        }
    }

    @DeleteMapping("/delete/{chaineDistributionId}")
    public Formatter<String> deleteChaineDistribution(@PathVariable Long chaineDistributionId) {

        try{
            chaineDistributionImp.deleteChaineDistribution(chaineDistributionId);
            return renderStringData(true,"success","deleted successfully");
        }catch (Exception e){
            return  renderStringData(false,e.getMessage(),"error");
        }
    }


    @GetMapping("/search/{id}/chaineValeur")
    public Formatter<List<ChaineDistribution>> listChaineDistributionParChaineValeur(@PathVariable Long id) {
        return renderDataArray(true,chaineDistributionImp.listChaineValeur(id),"list by chainevaleur") ;

    }

}
