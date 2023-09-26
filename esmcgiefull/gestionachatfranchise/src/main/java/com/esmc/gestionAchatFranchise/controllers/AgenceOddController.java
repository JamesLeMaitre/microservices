package com.esmc.gestionAchatFranchise.controllers;

import com.esmc.gestionAchatFranchise.entities.AgenceOdd;
import com.esmc.gestionAchatFranchise.services.AgenceOddImp;
import com.esmc.models.Formatter;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
import utiles.DataFormatter;

import java.util.List;

/**
 * @author Merveil,koisonn
 * @since 18/05/2022
 * @apiNote all operation related to agenceODD table  are handle here
 */
@RestController
@RequestMapping("/api/agenceOdd")
public class AgenceOddController  extends DataFormatter<AgenceOdd> {
    @Autowired
    private AgenceOddImp agenceOddImp;

    @PostMapping("/add")
    public Object addAgenceOdd(@RequestBody AgenceOdd agenceOdd) {
        try{
            AgenceOdd agenceOdd1= agenceOddImp.addAgenceOdd(agenceOdd);
            return  renderData(true,agenceOdd1,"Agent odd created successfully");
        }catch (Exception e){
            return  renderStringData(false,e.getMessage(),"error");
        }
    }

    @GetMapping("/getAll")
    public Formatter<List<AgenceOdd>> getAllAgenceOdd(){
        // List<AgenceOdd> agenceOdds = agenceOddImp.getAgenceOdd();
        return renderDataArray(true,agenceOddImp.getAgenceOdd(), "list of Agent");
    }

    @GetMapping("/{agenceOddId}")
    public Object getAgenceOdd(@PathVariable("agenceOddId") Long agenceOddId) {
        AgenceOdd agenceOdd1 = agenceOddImp.getAgenceOddById(agenceOddId);
        if(agenceOdd1 == null){
            return renderStringData(false,"Error ","AgentOdd not found");
        }
        return renderData(true,agenceOdd1, "get AgentOdd");
    }

    @PutMapping("/update/{agenceOddId}")

    public Object updateAgenceOdd(@PathVariable("agenceOddId") Long agenceOddId, @RequestBody AgenceOdd agenceOdd) {
        try{
            AgenceOdd agenceOdd1 = agenceOddImp.getAgenceOddById(agenceOddId);
            if(agenceOdd1 == null){
                return renderStringData(false,"Error ","AgentOdd not found");
            }
            agenceOdd.setId(agenceOddId);
            //agenceOddImp.updateAgenceOdd(agenceOddId, agenceOdd);
            return renderData(true,agenceOddImp.updateAgenceOdd(agenceOdd), "Updated successfully");
        }catch (Exception e){
            return  renderStringData(false,e.getMessage(),"error");
        }

    }

    @DeleteMapping("/delete/{agenceOddId}")
    public Formatter<String> deleteAgenceOdd(@PathVariable Long agenceOddId) {
        try{
        agenceOddImp.deleteAgenceOdd(agenceOddId);
        return renderStringData(true,"success","deleted successfully");
    }catch (Exception e){
        return  renderStringData(false,e.getMessage(),"error");
    }
    }

}