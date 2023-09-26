package com.esmc.gestionAchatFranchise.controllers;

import com.esmc.gestionAchatFranchise.entities.AgenceOdd;
import com.esmc.gestionAchatFranchise.entities.Centre;
import com.esmc.gestionAchatFranchise.services.CentreImp;
import com.esmc.models.Formatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utiles.DataFormatter;

import java.util.List;

@RestController
@RequestMapping(value="/api/centre")
public class CentreController extends DataFormatter<Centre> {
    @Autowired
    private CentreImp centreImp;


    @PostMapping("/add")
    public Object addCentre(@RequestBody Centre centre) {

        try{
            Centre centre1= centreImp.addCentre(centre);
            return  renderData(true,centre1,"Agent odd created successfully");
        }catch (Exception e){
            return  renderStringData(false,e.getMessage(),"error");
        }
    }

    @GetMapping("/getAll")
    public Formatter<List<Centre>> getAllCentre(){
        return renderDataArray(true, centreImp.getCentre(), "liste centre");
    }

    @GetMapping("/search/{centreId}")
    public Object getCentre(@PathVariable("centreId") Long centreId) {


        Centre centre1 = centreImp.getCentreById(centreId);
        if(centre1 == null){
            return renderStringData(false,"Error ","AgentOdd not found");
        }
        return renderData(true,centre1, "get AgentOdd");
    }

    @PutMapping("/update/{centreId}")
    public Object updateCentre(@PathVariable("centreId") Long centreId, @RequestBody Centre centre) {

        try{
            Centre centre1 = centreImp.getCentreById(centreId);
            if(centre1 == null){
                return renderStringData(false,"Error ","AgentOdd not found");
            }
            centre.setId(centreId);
            //agenceOddImp.updateAgenceOdd(agenceOddId, agenceOdd);
            return renderData(true,centreImp.updateCentre(centre), "Updated successfully");
        }catch (Exception e){
            return  renderStringData(false,e.getMessage(),"error");
        }
    }

    @DeleteMapping("/delete/{agenceOddId}")
    public Formatter<String> deleteCentre(@PathVariable Long centreId) {


        try{
            centreImp.deleteCentre(centreId);
            return renderStringData(true,"success","deleted successfully");
        }catch (Exception e){
            return  renderStringData(false,e.getMessage(),"error");
        }
    }

    @GetMapping("/search/{id}/centreFranchiseCentre")
    public Formatter<List<Centre> >listCentreParCentreFranchise(@PathVariable Long id) {
        return renderDataArray(true,centreImp.listCentreFranchiseCentre(id),"list franchise by center") ;

    }


}
