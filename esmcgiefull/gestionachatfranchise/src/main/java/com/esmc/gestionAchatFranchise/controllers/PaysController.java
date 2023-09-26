package com.esmc.gestionAchatFranchise.controllers;

import com.esmc.gestionAchatFranchise.entities.AgenceOdd;
import com.esmc.gestionAchatFranchise.entities.Pays;
import com.esmc.gestionAchatFranchise.services.PaysImp;
import com.esmc.models.Formatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utiles.DataFormatter;

import java.util.List;

@RestController
@RequestMapping(value="/api/pays")
public class PaysController extends DataFormatter<Pays> {
    @Autowired
    private PaysImp paysImp;


    @PostMapping("/save")
    public Object addPays(@RequestBody Pays pays) {

        try{
            Pays pays1= paysImp.addPays(pays);
            return  renderData(true,pays1,"Agent odd created successfully");
        }catch (Exception e){
            return  renderStringData(false,e.getMessage(),"error");
        }
    }

    @GetMapping("/getAll")
    public Formatter<List<Pays>> getAllPays(){
        return renderDataArray(true,paysImp.getPays(), "list");
    }

    @GetMapping("/get/{paysId}")
    public Object getPays(@PathVariable("paysId") Long paysId) {
        return renderData(true,paysImp.getPaysById(paysId), "pays");
    }

    @PutMapping("/update/{paysId}")

    public Object updatePays(@PathVariable("paysId") Long paysId, @RequestBody Pays pays) {



        try{
            Pays pays1 = paysImp.getPaysById(paysId);
            if(pays1 == null){
                return renderStringData(false,"Error ","AgentOdd not found");
            }
            pays.setId(paysId);
            //agenceOddImp.updateAgenceOdd(agenceOddId, agenceOdd);
            return renderData(true,paysImp.updatePays(pays), "Updated successfully");
        }catch (Exception e){
            return  renderStringData(false,e.getMessage(),"error");
        }
    }

    @DeleteMapping("/delete/{paysId}")
    public Formatter<String> deletePays(@PathVariable Long paysId) {

        try{
            paysImp.deletePays(paysId);
            return renderStringData(true,"success","deleted successfully");
        }catch (Exception e){
            return  renderStringData(false,e.getMessage(),"error");
        }
    }

    @GetMapping("/list/{id}/zoneMonnetaire")
    public Formatter<List<Pays>> listPaysParZoneMonnetaire(@PathVariable Long id) {
        return renderDataArray(true,paysImp.listZoneMonnetaire(id),"list");
    }



}
