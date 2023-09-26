package com.esmc.gestionAchatFranchise.controllers;

import com.esmc.gestionAchatFranchise.entities.AgenceOdd;
import com.esmc.gestionAchatFranchise.entities.Continent;
import com.esmc.gestionAchatFranchise.servicesinterfaces.ContinentInt;
import com.esmc.models.Formatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utiles.DataFormatter;

import java.util.List;

@RestController
@RequestMapping(value="/api/continent")
public class ContinentController extends DataFormatter<Continent> {
    @Autowired
    private ContinentInt continentImp;

    @PostMapping("/save")
    public Object addContinent(@RequestBody Continent continent) {

        try{
            Continent continent1= continentImp.addContinent(continent);
            return  renderData(true,continent1,"Agent odd created successfully");
        }catch (Exception e){
            return  renderStringData(false,e.getMessage(),"error");
        }

    }

    @GetMapping("/getAll")
    public Formatter<List<Continent>> getAllContinent(){
        return renderDataArray(true, continentImp.getContinent(),"liste continent") ;
    }

    @GetMapping("/get/{continentId}")
    public Object getContinent(@PathVariable("continentId") Long continentId) {


        Continent continent1 = continentImp.getContinentById(continentId);
        if(continent1 == null){
            return renderStringData(false,"Error ","AgentOdd not found");
        }
        return renderData(true,continent1, "get AgentOdd");
    }

    @PutMapping("/update/{continentId}")
    public Object updateContinent(@PathVariable("continentId") Long continentId, @RequestBody Continent continent) {



        try{
            Continent continent1 = continentImp.getContinentById(continentId);
            if(continent1 == null){
                return renderStringData(false,"Error ","AgentOdd not found");
            }
            continent.setId(continentId);
            //agenceOddImp.updateAgenceOdd(agenceOddId, agenceOdd);
            return renderData(true,continentImp.updateContinent(continent), "Updated successfully");
        }catch (Exception e) {
            return renderStringData(false, e.getMessage(), "error");
        }
    }

    @DeleteMapping("/delete/{continentId}")
    public Formatter<String> deleteContinent(@PathVariable Long continentId) {

        try{
            continentImp.deleteContinent(continentId);
            return renderStringData(true,"success","deleted successfully");
        }catch (Exception e){
            return  renderStringData(false,e.getMessage(),"error");
        }
    }


}
